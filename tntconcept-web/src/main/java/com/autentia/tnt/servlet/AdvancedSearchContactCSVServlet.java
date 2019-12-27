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

package com.autentia.tnt.servlet;

import com.autentia.tnt.bean.contacts.AdvancedSearchContactBean;
import com.autentia.tnt.manager.contacts.advancedsearch.ContactPosition;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdvancedSearchContactCSVServlet extends HttpServlet {

    private static String CSV_SEPARATOR = ";";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // se recuperan los contactos encontrados mediante busqueda avanzada y los textos
        final List<ContactPosition> contactPositions = (List<ContactPosition>) req.getSession().getAttribute(AdvancedSearchContactBean.CONTACTS_SESSION_KEY);
        final Map<String, String> texts = (Map<String, String>) req.getSession().getAttribute(AdvancedSearchContactBean.MESSAGES_SESSION_KEY);

        // se genera el contenido del csv
        final String csvBody = this.generateCSVBody(contactPositions, texts);
        
        // se manda el csv
        final PrintWriter writer = resp.getWriter();
        resp.setContentType("text/csv");
        resp.setContentLength(csvBody.length());
        writer.append(csvBody);
        writer.close();

        // se borran de la sesion http las variables que ya no necesitan ser utilizadas
        req.getSession().removeAttribute(AdvancedSearchContactBean.CONTACTS_SESSION_KEY);

    }

    /**
     * Generates the content of the CSV file
     * @param contactPositions data about the contacts os the positions
     * @return the content of the CSV file
     */
    protected String generateCSVBody (final List<ContactPosition> contactPositions, final Map<String, String> texts) {
        final StringBuilder body = new StringBuilder();
        body.append(this.generateCSVHeader(texts));
        for (ContactPosition contactPosition: contactPositions) {
            if (contactPosition.isActive()) {
                body.append(this.generateCSVItem(contactPosition));
            }
        }
        return body.toString();
    }

    /**
     * Generates the header of the CSV document
     * @return the header of the CSV document
     */
    private String generateCSVHeader (final Map<String, String> texts) {
        final StringBuilder header = new StringBuilder();
        header.append(this.populateCell(texts.get(AdvancedSearchContactBean.MESSAGE_CONTACT_NAME)));
        header.append(this.populateCell(texts.get(AdvancedSearchContactBean.MESSAGE_CONTACT_POSITION)));
        header.append(this.populateCell(texts.get(AdvancedSearchContactBean.MESSAGE_CONTACT_ORGANIZATION)));
        header.append(this.populateCell(texts.get(AdvancedSearchContactBean.MESSAGE_CONTACT_DEPARTMENT)));
        header.append(this.populateCell(texts.get(AdvancedSearchContactBean.MESSAGE_CONTACT_EMAIL)));
        header.append(this.populateCell(texts.get(AdvancedSearchContactBean.MESSAGE_CONTACT_PHONE)));
        header.append(this.populateCell(texts.get(AdvancedSearchContactBean.MESSAGE_CONTACT_FAX)));
        header.append(this.populateCell(texts.get(AdvancedSearchContactBean.MESSAGE_CONTACT_COUNTRY)));
        header.append(this.populateCell(texts.get(AdvancedSearchContactBean.MESSAGE_CONTACT_PROVINCE)));
        header.append(this.populateCell(texts.get(AdvancedSearchContactBean.MESSAGE_CONTACT_CITY)));
        header.append(this.populateCell(texts.get(AdvancedSearchContactBean.MESSAGE_CONTACT_POSTALCODE)));
        header.append(this.populateCell(texts.get(AdvancedSearchContactBean.MESSAGE_CONTACT_ADDRESS)));
        header.append(this.returnLine());
        return header.toString();
    }

    /**
     * Generates the different lines of the CSV document
     * @param contactPosition data to be printed in the CSV
     * @return the different lines of the CSV document
     */
    private String generateCSVItem (final ContactPosition contactPosition) {
        final StringBuilder item = new StringBuilder();
        item.append(this.populateCell(contactPosition.getName()));
        item.append(this.populateCell(contactPosition.getPosition()));
        item.append(this.populateCell(contactPosition.getOrganization()));
        item.append(this.populateCell(contactPosition.getDepartment()));
        item.append(this.populateCell(contactPosition.getEmail()));
        item.append(this.populateCell(contactPosition.getPhone()));
        item.append(this.populateCell(contactPosition.getFax()));
        item.append(this.populateCell(contactPosition.getCountry()));
        item.append(this.populateCell(contactPosition.getProvince()));
        item.append(this.populateCell(contactPosition.getCity()));
        item.append(this.populateCell(contactPosition.getPostalCode()));
        item.append(this.populateCell(contactPosition.getAddress()));
        item.append(this.returnLine());
        return item.toString();
    }

    /**
     * Populates a cell of the csv
     * @param content content to appear in the csv
     * @return the content of the cell populated
     */
    private String populateCell (final String content) {
        final StringBuilder cell = new StringBuilder();
        cell.append("\"").append(content).append("\"").append(CSV_SEPARATOR);
        return cell.toString();
    }

    /**
     * Generates a new line
     * @return a new line
     */
    private String returnLine () {
        return "\r\n";
    }
}
