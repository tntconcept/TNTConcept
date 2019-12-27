/**
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.
 * Copyright (C) 2007 Autentia Real Bussiness Solution S.L.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.autentia.tnt.bill.migration;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.bill.migration.support.MigratedInformationRecoverer;
import com.autentia.tnt.bill.migration.support.OriginalInformationRecoverer;
import com.autentia.tnt.businessobject.BillType;

public class BillToBillPaymentMigration {

	private static final Log log = LogFactory.getLog(BillToBillPaymentMigration.class);

	public static final String	CREATION_SCRIPT_FILE	= "com/autentia/tnt/sql/mysql/migrationBillPayment.sql";
	
	public static final String	UPGRADE_SCRIPT_FILE		= "com/autentia/tnt/sql/mysql/upgrade.sql";
	
	public static final String	DATABASE_DRIVER			= "com.mysql.jdbc.Driver";
	
	public static final String	DATABASE_HOST			= "localhost";
	
	public static final String	DATABASE_PORT			= "3306";
	
	public static final String	DATABASE_SCHEMA			= "tntconceptBillPayment";
	
	public static final String	DATABASE_USER			= "root";
	
	public static final String	DATABASE_PASS			= "";
	
	public static final String	DATABASE_CONNECTION		= "jdbc:mysql://" + DATABASE_HOST + ":" + DATABASE_PORT + "/" + DATABASE_SCHEMA;
	
	private static final String FACTURA_EMITIDA			= BillType.ISSUED.toString();
	
	private static final String FACTURA_RECIBIDA		= BillType.RECIEVED.toString();
	
	private static final int DOS_DECIMALES 				= 2;
	
	public BillToBillPaymentMigration() {}
	
	public static void main(String[] args) throws Exception {
		
		// ----------------------------------------------------------------		
		// PASO 1: recuperar los totales asi como los importes y las fechas 
		//         de las diferentes facturas
		// ----------------------------------------------------------------
		
		loadInitialDatabaseScript();
		
		double totalFacturasEmitidasOriginal = OriginalInformationRecoverer.getTotalFacturasOriginal(FACTURA_EMITIDA);
		double totalFacturasRecibidasOriginal = OriginalInformationRecoverer.getTotalFacturasOriginal(FACTURA_RECIBIDA);
		double[] importeFacturaEmitidaOriginal = OriginalInformationRecoverer.getImporteFacturaOriginal(FACTURA_EMITIDA);
		double[] importeFacturaRecibidaOriginal = OriginalInformationRecoverer.getImporteFacturaOriginal(FACTURA_RECIBIDA);
		Date[] fechaPagoFacturaEmitidaOriginal = OriginalInformationRecoverer.getFechaFacturaOriginal(FACTURA_EMITIDA);
		Date[] fechaPagoFacturaRecibidaOriginal = OriginalInformationRecoverer.getFechaFacturaOriginal(FACTURA_RECIBIDA);

		// -----------------------------------------------------
		// PASO 2: migrar y comprobar totales, importes y fechas
		// -----------------------------------------------------
	
		upgradeDatabaseModel();
		
		double totalFacturasEmitidasMigrated = MigratedInformationRecoverer.getTotalFacturasMigrated(FACTURA_EMITIDA);
		double totalFacturasRecibidasMigrated = MigratedInformationRecoverer.getTotalFacturasMigrated(FACTURA_RECIBIDA);
		double[] importeFacturaEmitidaMigrated = MigratedInformationRecoverer.getImporteFacturaMigrated(FACTURA_EMITIDA);
		double[] importeFacturaRecibidaMigrated = MigratedInformationRecoverer.getImporteFacturaMigrated(FACTURA_RECIBIDA);
		Date[] fechaPagoFacturaEmitidaMigrated = MigratedInformationRecoverer.getFechaFacturaMigrated(FACTURA_EMITIDA);
		Date[] fechaPagoFacturaRecibidaMigrated = MigratedInformationRecoverer.getFechaFacturaMigrated(FACTURA_RECIBIDA);		

		// -----------------------------------------------------------
		// PASO 3: comparar los valores nuevos y los antiguos para ver 
		//         si la migración se ha realizado o no con exito
		// -----------------------------------------------------------
		
		log.info("\n=================\nRESUMEN\n=================");
		
		// total facturas
		comprobarTotalFacturas("emitidas", totalFacturasEmitidasOriginal, totalFacturasEmitidasMigrated);
		comprobarTotalFacturas("recibidas", totalFacturasRecibidasOriginal, totalFacturasRecibidasMigrated);

		// importe facturas
		comprobarImporteFacturas("emitidas", importeFacturaEmitidaOriginal, importeFacturaEmitidaMigrated);
		comprobarImporteFacturas("recibidas", importeFacturaRecibidaOriginal, importeFacturaRecibidaMigrated);
		
		// fechas de pago o cobro de facturas
		comprobarFechasFacturas("emitidas", fechaPagoFacturaEmitidaOriginal, fechaPagoFacturaEmitidaMigrated);
		comprobarFechasFacturas("recibidas", fechaPagoFacturaRecibidaOriginal, fechaPagoFacturaRecibidaMigrated);
	}

	/**
	 * tests the value of the dates migrated and original
	 */
	private static void comprobarFechasFacturas(String tipo,
			Date[] fechaPagoFacturaOriginal,
			Date[] fechaPagoFacturaMigrated) {
		boolean distinto = false;
		if (fechaPagoFacturaOriginal.length != fechaPagoFacturaMigrated.length) 
		{
			distinto = true;
			log.error("El número de fechas de facturas " + tipo + " originales y migradas es distinto: " + fechaPagoFacturaOriginal.length + " y " + fechaPagoFacturaMigrated.length);
		} 
		else 
		{			
			for (int i = 0; i < fechaPagoFacturaOriginal.length; i++) 
			{
				if (!fechaPagoFacturaOriginal[i].equals(fechaPagoFacturaMigrated[i])) 
				{
					distinto = true;
					log.error("La fecha de pago de una factura de las " + tipo + " es diferente:" +		
						"\noriginal: " + fechaPagoFacturaOriginal[i] + 
						"\nmigrada: " + fechaPagoFacturaMigrated[i]);
				} 	
			}	
		}	
		
		if (!distinto) 
		{
			log.info("El importe de las facturas " + tipo + " originales y migradas es el mismo");
		}		
	}

	/**
	 * tests the value of the bills migrated and original
	 */
	private static void comprobarImporteFacturas(String tipo,
			double[] importeFacturaOriginal,
			double[] importeFacturaMigrated) 
	{
		boolean distinto = false;
		if (importeFacturaOriginal.length != importeFacturaMigrated.length) 
		{
			distinto = true;
			log.error("El número de facturas " + tipo + " originales y migradas es distinto: " + importeFacturaOriginal.length + " y " + importeFacturaMigrated.length);
		} 
		else 
		{			
			for (int i = 0; i < importeFacturaOriginal.length; i++) 
			{
				if (redondear(importeFacturaOriginal[i]) != redondear(importeFacturaMigrated[i])) 
				{
					distinto = true;
					log.error("El importe de una factura de las " + tipo + " es diferente:" +		
						"\noriginal: " + redondear(importeFacturaOriginal[i]) + 
						"\nmigrado: " + redondear(importeFacturaMigrated[i]));
				} 
			}	
		}	
		
		if (!distinto) 
		{
			log.info("Las fechas de pago de las facturas " + tipo + " originales y migradas son las mismas");
		}
	}

	/**
	 * tests the sum of the bills migrated and original
	 */
	private static void comprobarTotalFacturas(String tipo,
			double totalFacturasMigrated,
			double totalFacturasOriginal) 
	{
		if (redondear(totalFacturasMigrated) != redondear(totalFacturasOriginal)) 
		{
			log.error("El total de la suma de las facturas " + tipo + " es diferente:" +		
				"\noriginal: " + redondear(totalFacturasOriginal) + 
				"\nmigrado: " + redondear(totalFacturasMigrated));
		} else {
			log.info("El total de la suma de las facturas " + tipo + " es el mismo");
		}
	}

	/**
	 * generates a database with the data initializated
	 */
	private static void loadInitialDatabaseScript() throws Exception {
		log.info("CREATING DATABASE");
		executeScript(CREATION_SCRIPT_FILE);
	}

	/**
	 * performs changes in the database with the data initializated
	 */
	private static void upgradeDatabaseModel() throws Exception {
		log.info("UPGRADING DATABASE");
		executeScript(UPGRADE_SCRIPT_FILE);
	}
	
	/**
	 * executes an script received by parameter
	 * @param script the script to be launched
	 */
	private static void executeScript(String script) throws Exception 
	{
		Connection con = null;
		Statement stmt = null;
		LineNumberReader file = null;
		String delimiter = ";";

		try {
			log.debug("LOADING DATABASE SCRIPT" + script);

			// connect to database
			Class.forName(DATABASE_DRIVER);
			con = DriverManager.getConnection(DATABASE_CONNECTION, DATABASE_USER, DATABASE_PASS); 	//NOSONAR
			con.setAutoCommit(false);															  	// DATABASE_PASS es nula
			stmt = con.createStatement();

			// load file
			InputStream sqlScript = Thread.currentThread().getContextClassLoader().getResourceAsStream(script);
			if (sqlScript == null) {
				throw new FileNotFoundException(script);
			}
			file = new LineNumberReader(new InputStreamReader(
					new BufferedInputStream(sqlScript), "UTF-8"));

			// Add batched SQL sentences to statement
			StringBuilder sentence = new StringBuilder();
			String line;
			while ((line = file.readLine()) != null) {
				line = line.trim();
				if (!line.startsWith("--")) {
					// Interpret "DELIMITER" sentences
					if (line.trim().toUpperCase(Locale.ENGLISH).startsWith("DELIMITER")) {
						delimiter = line.trim().substring("DELIMITER".length()).trim();
					} else {
						// Add line to sentence
						if (line.endsWith(delimiter)) {
							// Remove delimiter
							String lastLine = line.substring(0, line.length()
									- delimiter.length());

							// Append line to sentence
							sentence.append(lastLine);

							// Execute sentence
							log.debug(" " + sentence.toString());
							stmt.addBatch(sentence.toString());

							// Prepare new sentence
							sentence = new StringBuilder();
						} else {
							// Append line to sentence
							sentence.append(line);

							// Append separator for next line
							sentence.append(" ");
						}
					}
				}
			}

			// Execute batch
			log.debug("upgradeDatabase - executing batch of commands");
			stmt.executeBatch();

			// Commit transaction
			log.debug("upgradeDatabase - commiting changes to database");
			con.commit();

			// Report end of migration
			log.debug("END LOADING DATABASE SCRIPT");
			
		} catch (Exception e) {
			log.error("FAILED: WILL BE ROLLED BACK: ", e);
			if(con!=null){
				con.rollback();
			}
			
		} finally {
			cierraFichero(file);
			liberaConexion(con, stmt, null);
		}
	}	
	
	private static double redondear(double numero) {
		return Math.round(numero*Math.pow(10,DOS_DECIMALES))/Math.pow(10,DOS_DECIMALES);
		// return numero;
	}
	
	
	private static void liberaConexion(Connection con, Statement stmt, ResultSet rs) {
		if(rs != null){
			try{
				rs.close();
			}catch(SQLException sqlex) {
				log.error("Error al liberar el ResultSet", sqlex);
			}
		}
		
		if(stmt != null){
			try{
				stmt.close();
			}catch(SQLException sqlex) {
				log.error("Error al liberar el Statement", sqlex);
			}
		}
		
		if(con != null){
			try{
				con.close();
			}catch(SQLException sqlex) {
				log.error("Error al liberar la conexión", sqlex);
			}
		}
	}
	
	private static void cierraFichero (LineNumberReader f){
		try{
			if(f!=null){
				f.close();
			}
		}catch(Exception ex){
			log.error("Error al cerrar fichero", ex);
		}
	}
	
}
