/**
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.
 * Copyright (C) 2007 Autentia Real Bussiness Solution S.L.
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.autentia.tnt.bean.billing;

import com.autentia.tnt.bean.BaseBean;
import com.autentia.tnt.businessobject.Bill;
import com.autentia.tnt.businessobject.BillType;
import com.autentia.tnt.businessobject.Organization;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.BillSearch;
import com.autentia.tnt.mail.DefaultMailService;
import com.autentia.tnt.mail.MailService;
import com.autentia.tnt.manager.admin.SettingManager;
import com.autentia.tnt.manager.billing.BillManager;
import com.autentia.tnt.util.ConfigurationUtil;
import com.autentia.tnt.util.FacesUtils;
import com.autentia.tnt.util.SettingPath;
import com.autentia.tnt.util.SpringUtils;
import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;
import org.apache.commons.lang.StringUtils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * UI bean for Bill objects.
 *
 * @author stajanov code generator
 */
public class SiiBean extends BaseBean {

    private Date startDate;

    private Date endDate;

    private Date today;

    private ConfigurationUtil configurationUtil;
    private FacesContext context;

    private String to;

    private List<Bill> bills;

    private BillType selectedType;

    /** Manager */
    private static BillManager manager = BillManager.getDefault();

    public void sendReportWebDav() {
        String report = getReport();

        if( report != null) {
            String url = configurationUtil.getWebdavHost();
            String username = configurationUtil.getWebdavUser();
            String password = configurationUtil.getWebdavPassword();
            Sardine sardine = SardineFactory.begin( username, password );
            InputStream file =  getStreamReport( report );
            try{
                sardine.put( url + reportName(), file , "text/csv");

                if( bills != null) {
                    for (Bill bill : bills) {
                        bill.setSubmitted( 1 );
                        manager.updateEntity( bill );
                    }
                }

                context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informe enviado correctamente",null));
                
            }
            catch (IOException ioe) {
                context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Se ha producido un error al enviar el informe",null));
                
            }
        }
    }

    public void sendReport() {
        String report = getReport();

        if( report != null) {
            Map<InputStream, String> attachments = new ConcurrentHashMap<>();
            String filename = reportName();

            attachments.put( getStreamReport( report ), filename );

            String subject = "SII_" + filename.split(".csv")[0];

            ExecutorService executor = Executors.newFixedThreadPool(1);
            // Runnable, return void, submit and run the task async
            executor.submit(() -> {
                try{
                    String[] recipients = to.trim().split("[;,]");

                    MailService mailService = (DefaultMailService) SpringUtils.getSpringBean("mailService");
                    mailService.sendOutputStreams(recipients, subject, "", attachments);

                    context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Email enviado correctamente",null));

                }
                catch ( MessagingException e ) {
                    System.err.println( e.getMessage() );
                }
            });

            executor.shutdown();
        }
    }

    public void downloadReport() throws IOException {
        String report = getReport();

        if( report != null) {
            context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Informe descargado correctamente",null));
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

            response.reset();
            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + reportName() + "\"");

            OutputStream os = response.getOutputStream();

            final PrintStream printStream = new PrintStream(os);
            printStream.print(getReport());
            printStream.close();
            context.responseComplete();
        }
    }

    private InputStream getStreamReport( String report ) {
        return new ByteArrayInputStream( report.getBytes( StandardCharsets.UTF_8 ) );
    }

    private String getReport() {
        BillSearch search = new BillSearch();
        context = FacesContext.getCurrentInstance();

        search.setBillType(selectedType);

        search.setStartStartBillDate( startDate );
        search.setEndStartBillDate( endDate );
        search.setSubmitted( 0 );

        if ( selectedType.compareTo(BillType.RECIEVED) == 0) { // compras
                search.setStartInsertDate( startDate );
                search.setEndInsertDate( endDate );
        }

        bills = manager.getAllEntities(search, new SortCriteria("startBillDate", true));
        
        if (bills.isEmpty()) {
                            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"No hay facturas para enviar",null));
            return null;
        }

        // Comprobamos la calidad del dato de cada factura antes de generar el informe
        StringBuilder error = new StringBuilder();
        for(Bill bill: bills){
            String msg = areRequiredFieldsNull(bill);
            if(msg != null){
                error.append(msg).append('\n');
            }
        }

        if( !StringUtils.isBlank(error.toString())) {
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

            response.reset();
            response.setContentType("text/txt");
            response.setHeader("Content-Disposition", "attachment; filename=Fallo_en_facturas.txt");

            try {
                final PrintStream printStream = new PrintStream(response.getOutputStream());
                printStream.print(error.toString());
                printStream.close();
                context.responseComplete();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                return null;
            }
        }

        // se genera el contenido del csv
        return generateCSVBody(bills);
    }

    private String reportName() {
        StringBuilder stringBuilder = new StringBuilder();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");

        String billType = ( selectedType.compareTo(BillType.ISSUED) == 0 ) ? "VENTAS" : "COMPRAS";

        stringBuilder.append( billType ).append( "_AUTENTIA_" ).append( dateFormat.format( today ) ).append( ".csv" );

        return  stringBuilder.toString();
    }

    private String generateCSVBody (final List<Bill> bills) {
        final StringBuilder body = new StringBuilder();
        body.append(this.generateCSVHeader());
        for (Bill bill: bills) {
            body.append(this.generateCSVItem(bill));
        }

        return body.toString();
    }

    /**
     * Generates the header of the CSV document
     * @return the header of the CSV document
     */
    private String generateCSVHeader () {
        final StringBuilder header = new StringBuilder();
        header.append(this.populateCell(""));
        header.append(this.populateCell("NIF"));
        header.append(this.populateCell("RAZON SOCIAL"));
        header.append(this.populateCell("TIPO DOCUMENTO"));
        header.append(this.populateCell("DOCUMENTO"));
        header.append(this.populateCell("PAIS"));
        header.append(this.populateCell("FACTURA"));
        header.append(this.populateCell("FACTURA HASTA"));
        header.append(this.populateCell("FECHA FACTURA"));
        header.append(this.populateCell("FECHA EXPEDICION"));
        header.append(this.populateCell("AÑO"));
        header.append(this.populateCell("PERIODO"));

        if ( selectedType.compareTo(BillType.ISSUED) == 0 )
            generateCSVHeaderIssue( header );
        else
            generateCSVHeaderRecieved( header );



        header.append(this.returnLine());
        return header.toString();
    }

    private void generateCSVHeaderRecieved( StringBuilder header ) {
        header.append(this.populateCell("IVA 1"));
        header.append(this.populateCell("BASE IVA 1"));
        header.append(this.populateCell("CUOTA IVA 1"));
        header.append(this.populateCell("% COMPENSACION REAGYP 1"));
        header.append(this.populateCell("COMPENSACION REAGYP 1"));
        header.append(this.populateCell("DEDUCIBLE 1"));
        header.append(this.populateCell("IVA 2"));
        header.append(this.populateCell("BASE IVA 2"));
        header.append(this.populateCell("CUOTA IVA 2"));
        header.append(this.populateCell("% COMPENSACION REAGYP 2"));
        header.append(this.populateCell("COMPENSACION REAGYP 2"));
        header.append(this.populateCell("DEDUCIBLE 2"));
        header.append(this.populateCell("IVA 3"));
        header.append(this.populateCell("BASE IVA 3"));
        header.append(this.populateCell("CUOTA IVA 3"));
        header.append(this.populateCell("% COMPENSACION REAGYP 3"));
        header.append(this.populateCell("COMPENSACION REAGYP 3"));
        header.append(this.populateCell("DEDUCIBLE 3"));
        header.append(this.populateCell("EXENTO"));
        header.append(this.populateCell("BASE EXENTO"));
        header.append(this.populateCell("CUOTA EXENTO"));
        header.append(this.populateCell("DEDUCIBLE EXENTO"));
        header.append(this.populateCell("RECARGO 1"));
        header.append(this.populateCell("BASE RECARGO 1"));
        header.append(this.populateCell("CUOTA RECARGO 1"));
        header.append(this.populateCell("RECARGO 2"));
        header.append(this.populateCell("BASE RECARGO 2"));
        header.append(this.populateCell("CUOTA RECARGO 2"));
        header.append(this.populateCell("RECARGO 3"));
        header.append(this.populateCell("BASE RECARGO 3"));
        header.append(this.populateCell("CUOTA RECARGO 3"));
        header.append(this.populateCell("IRPF 1"));
        header.append(this.populateCell("BASE IRPF 1"));
        header.append(this.populateCell("CUOTA IRPF 1"));
        header.append(this.populateCell("IRPF 2"));
        header.append(this.populateCell("BASE IRPF 2"));
        header.append(this.populateCell("CUOTA IRPF 2"));
        header.append(this.populateCell("TOTAL"));
        header.append(this.populateCell("IMPORTE BASE A COSTE"));
        header.append(this.populateCell("DESCRIPCION"));
        header.append(this.populateCell("TIPO FACTURA"));
        header.append(this.populateCell("TIPO RECTIFICATIVA"));
        header.append(this.populateCell("REGIMEN"));
        header.append(this.populateCell("REGIMEN2"));
        header.append(this.populateCell("REGIMEN3"));
        header.append(this.populateCell("DUA"));
        header.append(this.populateCell("FECHA DUA"));
        header.append(this.populateCell("NUMRECTIFICATIVA"));
        header.append(this.populateCell("FECHA RECTIFICATIVA"));
        header.append(this.populateCell("SUJETO PASIVO"));
        header.append(this.populateCell("REFERENCIA EXTERNA"));
        header.append(this.populateCell("SIMPLIFICADA POR ARTICULO 7.2"));
        header.append(this.populateCell("NIF SUCEDIDA"));
        header.append(this.populateCell("RAZSOC SUCEDIDA"));
        header.append(this.populateCell("REDEME"));
    }

    private void generateCSVHeaderIssue( StringBuilder header ) {
        header.append(this.populateCell("SITUACION INMUEBLE"));
        header.append(this.populateCell("REF CATASTRAL"));
        header.append(this.populateCell("IVA 1"));
        header.append(this.populateCell("BASE IVA 1"));
        header.append(this.populateCell("CUOTA IVA 1"));
        header.append(this.populateCell("MOTIVO 1"));
        header.append(this.populateCell("SUJETO 1"));
        header.append(this.populateCell("PRESTACION SERVICIO 1"));
        header.append(this.populateCell("IMPORTE 1 SEGUN ART 7,14"));
        header.append(this.populateCell("IMPORTE 1 TAI"));
        header.append(this.populateCell("IVA 2"));
        header.append(this.populateCell("BASE IVA 2"));
        header.append(this.populateCell("CUOTA IVA 2"));
        header.append(this.populateCell("MOTIVO 2"));
        header.append(this.populateCell("SUJETO 2"));
        header.append(this.populateCell("PRESTACION SERVICIO 2"));
        header.append(this.populateCell("IMPORTE 2 SEGUN ART 7,14"));
        header.append(this.populateCell("IMPORTE 2 TAI"));
        header.append(this.populateCell("IVA 3"));
        header.append(this.populateCell("BASE IVA 3"));
        header.append(this.populateCell("CUOTA IVA 3"));
        header.append(this.populateCell("MOTIVO 3"));
        header.append(this.populateCell("SUJETO 3"));
        header.append(this.populateCell("PRESTACION SERVICIO 3"));
        header.append(this.populateCell("IMPORTE 3 "));
        header.append(this.populateCell("SEGUN ART 7,14"));
        header.append(this.populateCell("IMPORTE 3 TAI"));
        header.append(this.populateCell("EXENTO"));
        header.append(this.populateCell("BASE EXENTO")); // ?
        header.append(this.populateCell("CUOTA EXENTO"));
        header.append(this.populateCell("SUJETO 4"));
        header.append(this.populateCell("MOTIVO EXENTO"));
        header.append(this.populateCell("RECARGO 1"));
        header.append(this.populateCell("BASE RECARGO 1"));
        header.append(this.populateCell("CUOTA RECARGO 1"));
        header.append(this.populateCell("RECARGO 2"));
        header.append(this.populateCell("BASE RECARGO 2"));
        header.append(this.populateCell("CUOTA RECARGO 2"));
        header.append(this.populateCell("RECARGO 3"));
        header.append(this.populateCell("BASE RECARGO 3"));
        header.append(this.populateCell("CUOTA RECARGO 3"));
        header.append(this.populateCell("IRPF 1"));
        header.append(this.populateCell("BASE IRPF 1"));
        header.append(this.populateCell("CUOTA IRPF 1"));
        header.append(this.populateCell("IRPF 2"));
        header.append(this.populateCell("BASE IRPF 2"));
        header.append(this.populateCell("CUOTA IRPF 2"));
        header.append(this.populateCell("TOTAL"));
        header.append(this.populateCell("IMPORTE BASE A COSTE"));
        header.append(this.populateCell("DESCRIPCION"));
        header.append(this.populateCell("TIPO FACTURA"));
        header.append(this.populateCell("TIPO RECTIFICATIVA"));
        header.append(this.populateCell("REGIMEN"));
        header.append(this.populateCell("REGIMEN2"));
        header.append(this.populateCell("REGIMEN3"));
        header.append(this.populateCell("TERCEROS"));
        header.append(this.populateCell("NUMRECTIFICATIVA"));
        header.append(this.populateCell("FECHA RECTIFICATIVA"));
        header.append(this.populateCell("PRESTACION SERVICIO EXENTO"));
        header.append(this.populateCell("IMPORTE TRANSMISION INMUEBLE"));
        header.append(this.populateCell("SIMPLIFICADA POR ARTICULO 7.2"));
        header.append(this.populateCell("NIF SUCEDIDA"));
        header.append(this.populateCell("RAZSOC SUCEDIDA"));
        header.append(this.populateCell("REDEME"));
        header.append(this.populateCell("DISPOSICION TERCERA Y SEXTA"));
        header.append(this.populateCell("SIN IDENTIFICACION ARTICULO 6.1"));
        header.append(this.populateCell("REFERENCIA EXTERNA"));
    }

    /**
     * Generates the different lines of the CSV document
     * @param bill data to be printed in the CSV
     * @return the different lines of the CSV document
     */
    private String generateCSVItem (final Bill bill) {

        final StringBuilder item = new StringBuilder();
        Map<String, BigDecimal> costData = new HashMap<>();

        Calendar calendar = Calendar.getInstance();

        Date expirationDate = null;
        Organization organization = null;

        switch (selectedType) {
            case ISSUED:  // ventas
                calendar.setTime( bill.getCreationDate() );
                expirationDate = bill.getCreationDate();
                organization = bill.getProject().getClient();
                break;
            case RECIEVED:  // compras
                calendar.setTime( bill.getInsertDate() );
                expirationDate = bill.getInsertDate();
                organization = bill.getProvider();
        }

        boolean nationalOrganitation = Pattern.matches("(ES)?([ABCDEFGHJKLMNPQRSUVW])(\\d{7})([0-9A-J])", organization.getCif());
        nationalOrganitation = nationalOrganitation || organization.getCountry().trim().toLowerCase().equals("españa");

        String cif = ( nationalOrganitation ) ? organization.getCif()  : "";
        String providerName = organization.getName();
        String documentType = ( !nationalOrganitation ) ? "02 - NIF-IVA" : "";  // Cuando la empresa sea extranjera
        String europeCif = ( !nationalOrganitation ) ? organization.getCif()  : ""; // Cuando la empresa sea extranjera
        String country = ( !nationalOrganitation ) ? organization.getCountry() : "";
        String orderNumber = bill.getNumber();
        Date creacionDate = bill.getCreationDate();
        String year = Integer.toString(calendar.get(Calendar.YEAR));

        int monthNumber = calendar.get(Calendar.MONTH) + 1;
        String aux = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        String monthName =  aux.substring(0, 1).toUpperCase() + aux.substring(1);
        String period = monthNumber + " - " + monthName ;

        costData.put("total", bill.getTotal());
        costData.put("iva", bill.getBreakDown().iterator().next().getIva());
        costData.put("basePrice", bill.getTotalNoTaxes());
        costData.put("ivaTotal", bill.getTotal().subtract(bill.getTotalNoTaxes()));

        item.append( this.populateCell("") );
        item.append( this.populateCell( cif ) );
        item.append( this.populateCell( providerName ));
        item.append( this.populateCell( documentType ));
        item.append( this.populateCell( europeCif ));
        item.append( this.populateCell( country ));
        item.append( this.populateCell( orderNumber ));
        item.append( this.populateCell(""));
        item.append( this.populateCell( creacionDate ));
        item.append( this.populateCell( expirationDate ));
        item.append( this.populateCell("=\"" + year + "\"")); //La gestora quiere que el año sea texto
        item.append( this.populateCell("=\"" + period + "\""));

        if ( selectedType.compareTo(BillType.RECIEVED) == 0 )
            //generateCSVItemIssue
            generateCSVItemReceive( costData, item, bill.getName() );
        else
            //generateCSVItemReceive
            generateCSVItemIssue( costData, item );

        item.append( this.returnLine());

        return item.toString();
    }

    private String areRequiredFieldsNull(Bill bill) {

        Organization organization = (selectedType.compareTo(BillType.ISSUED) == 0) ?
                bill.getProject().getClient() :
                bill.getProvider();

        String organizationName = organization.getName();
        StringBuilder error = new StringBuilder();
        Map<String, String> fields = new HashMap<>();

        evaluateObject(bill.getCreationDate(), fields, "Fallo en la fecha de creación");
        //evaluateObject(bill.getInsertDate(), fields, "Fallo en la fecha de inserción");
        //evaluateObject(organization, fields, "Fallo en la organización");
        //evaluateObject(organization.getName(), fields, "Nombre de la organización ("+organizationName+") vacío");
        evaluateObject(organization.getCif(), fields, "CIF de la organización ("+organizationName+") vacío");
        evaluateObject(organization.getCountry(), fields, "País de la organización ("+organizationName+") vacío");
        //evaluateObject(bill.getNumber(), fields, "Número de factura vacío");
        evaluateObject(bill.getTotal(), fields, "Fallo en el desglose de la factura");
        evaluateObject(bill.getBreakDown().iterator().next().getIva(), fields, "Fallo en el desglose de la factura");
        evaluateObject(bill.getTotalNoTaxes(), fields, "Fallo en el desglose de la factura");


        fields.forEach((k, v) -> error.append(v).append('\n'));

        if(error.toString().trim().isEmpty()){
            return null;
        }

        error.insert(0, "Fallo en la factura con numero "+bill.getNumber()+"\n");

        return error.toString();
    }

    private void evaluateObject (Object object, Map<String, String> fields, String msg) {
        if(object == null || (object instanceof String && StringUtils.isBlank((String) object))){
            fields.put("FAILURE " + msg.hashCode(), msg);
        }
    }

    private void generateCSVItemReceive (Map<String, BigDecimal> costData, StringBuilder item, String description) {

        item.append( this.populateCell( costData.get("iva") ) );
        item.append( this.populateCell( costData.get("basePrice") ));
        item.append( this.populateCell( costData.get("ivaTotal") ));
        item.append( this.populateCell(""));
        item.append( this.populateCell(""));
        item.append( this.populateCell( costData.get("ivaTotal") ));
        item.append( this.populateCell(""));
        item.append( this.populateCell(""));
        item.append( this.populateCell(""));
        item.append( this.populateCell(""));
        item.append( this.populateCell(""));
        item.append( this.populateCell(""));
        item.append( this.populateCell(""));
        item.append( this.populateCell(""));
        item.append( this.populateCell(""));
        item.append( this.populateCell(""));
        item.append( this.populateCell(""));
        item.append( this.populateCell(""));
        item.append( this.populateCell(""));
        item.append( this.populateCell(""));
        item.append( this.populateCell(""));
        item.append( this.populateCell(""));
        item.append( this.populateCell(""));
        item.append( this.populateCell(""));
        item.append( this.populateCell(""));
        item.append( this.populateCell(""));
        item.append( this.populateCell(""));
        item.append( this.populateCell(""));
        item.append( this.populateCell(""));
        item.append( this.populateCell(""));
        item.append( this.populateCell(""));
        item.append( this.populateCell(""));
        item.append( this.populateCell(""));
        item.append( this.populateCell(""));
        item.append( this.populateCell(""));
        item.append( this.populateCell(""));
        item.append( this.populateCell(""));
        item.append( this.populateCell( costData.get("total") ));
        item.append( this.populateCell("0"));
        item.append( this.populateCell( description ));
        item.append( this.populateCell("F1 - Factura"));
        item.append( this.populateCell(""));
        item.append( this.populateCell("01 - Operación de régimen común"));
        item.append( this.populateCell(""));
        item.append( this.populateCell(""));
        item.append( this.populateCell(""));
        item.append( this.populateCell(""));
        item.append( this.populateCell(""));
        item.append( this.populateCell(""));
        item.append( this.populateCell("NO"));
        item.append( this.populateCell(""));
        item.append( this.populateCell("N - No"));
        item.append( this.populateCell(""));
        item.append( this.populateCell(""));
        item.append( this.populateCell("N - No"));

    }

    private void generateCSVItemIssue (Map<String, BigDecimal> costData, StringBuilder item) {

        item.append(this.populateCell(""));
        item.append(this.populateCell(""));
        item.append( this.populateCell( costData.get("iva") ) );
        item.append(this.populateCell( costData.get("basePrice") ));
        item.append(this.populateCell( costData.get("ivaTotal") ));
        item.append(this.populateCell("S1 - No exenta- Sin inversion sujeto pasivo"));
        item.append(this.populateCell(""));
        item.append(this.populateCell("SI"));
        item.append(this.populateCell(""));
        item.append(this.populateCell(""));
        item.append(this.populateCell(""));
        item.append(this.populateCell(""));
        item.append(this.populateCell(""));
        item.append(this.populateCell(""));
        item.append(this.populateCell(""));
        item.append(this.populateCell(""));
        item.append(this.populateCell(""));
        item.append(this.populateCell(""));
        item.append(this.populateCell(""));
        item.append(this.populateCell(""));
        item.append(this.populateCell(""));
        item.append(this.populateCell(""));
        item.append(this.populateCell(""));
        item.append(this.populateCell(""));
        item.append(this.populateCell(""));
        item.append(this.populateCell(""));
        item.append(this.populateCell(""));
        item.append(this.populateCell("SI"));
        item.append(this.populateCell("")); // Que va en este campo?
        item.append(this.populateCell("0"));
        item.append(this.populateCell(""));
        item.append(this.populateCell("E5 - Exenta por el artículo 25"));
        item.append(this.populateCell(""));
        item.append(this.populateCell(""));
        item.append(this.populateCell(""));
        item.append(this.populateCell(""));
        item.append(this.populateCell(""));
        item.append(this.populateCell(""));
        item.append(this.populateCell(""));
        item.append(this.populateCell(""));
        item.append(this.populateCell(""));
        item.append(this.populateCell(""));
        item.append(this.populateCell(""));
        item.append(this.populateCell(""));
        item.append(this.populateCell(""));
        item.append(this.populateCell(""));
        item.append(this.populateCell(""));
        item.append(this.populateCell( costData.get("total") ));
        item.append(this.populateCell("0"));
        item.append(this.populateCell("Prestación de servicios"));
        item.append(this.populateCell("F1 - Factura"));
        item.append(this.populateCell(""));
        item.append(this.populateCell("01 - Operación de régimen común"));
        item.append(this.populateCell(""));
        item.append(this.populateCell(""));
        item.append(this.populateCell(""));
        item.append(this.populateCell(""));
        item.append(this.populateCell(""));
        item.append(this.populateCell("NO"));
        item.append(this.populateCell("0"));
        item.append(this.populateCell("N - No"));
        item.append(this.populateCell(""));
        item.append(this.populateCell(""));
        item.append(this.populateCell("N - No"));
        item.append(this.populateCell("N - No"));
        item.append(this.populateCell("N - No"));
        item.append(this.populateCell(""));

    }

    /**
     * Populates a cell of the csv
     * @param content content to appear in the csv
     * @return the content of the cell populated
     */
    private String populateCell (final String content) {
        final StringBuilder cell = new StringBuilder();
        cell.append("\"").append(content).append("\"").append(";");
        return cell.toString();
    }

    private String populateCell (final Date content) {
        final StringBuilder cell = new StringBuilder();
        String format =  SettingManager.getString(
                SettingManager.getDefault().get(SettingPath.BITACORE_PREFERRED_HEADER_FORMAT, false),
                "dd/MM/yy");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

        cell.append(simpleDateFormat.format(content)).append(";");
        return cell.toString();
    }

    private String populateCell (final BigDecimal content) {
        final StringBuilder cell = new StringBuilder();
        NumberFormat nf = NumberFormat.getNumberInstance(new Locale(FacesUtils.getViewLocale().getLanguage()));
        cell.append(nf.format(content)).append(";");
        return cell.toString();
    }

    /**
     * Generates a new line
     * @return a new line
     */
    private String returnLine () {
        return "\r\n";
    }

    public SiiBean() {

        today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.YEAR, -3);

        startDate = calendar.getTime();
        endDate = today;
        selectedType = BillType.ISSUED;
        configurationUtil = ConfigurationUtil.getDefault();
        to = configurationUtil.getSiiRecipients();
        
    }
    
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Get the list of all BillType values
     *
     * @return the list of all BillType values
     */
    public List<SelectItem> getBillTypes() {
        ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
        BillType[] vals = BillType.values();
        for (BillType val : vals) {
            ret.add(new SelectItem(val, FacesUtils.formatMessage("BillType."
                    + val.name())));
        }
        return ret;
    }

    public BillType getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(BillType selectedType) {
        this.selectedType = selectedType;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
