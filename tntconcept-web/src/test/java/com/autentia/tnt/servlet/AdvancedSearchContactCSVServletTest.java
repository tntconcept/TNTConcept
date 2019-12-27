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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

public class AdvancedSearchContactCSVServletTest {

    private static final String CSV_CONTENT = 
            "\"Nombre\";\"Puesto\";\"Organización\";\"Departamento\";\"Correo-e\";\"Teléfono\";\"Fax\";\"Pais\";\"Province\";\"Ciudad\";\"Código postal\";\"Dirección\";\r\n\"Ana Sánchez \";\"Responsable de dirección \";\"A.T.R.I.L CB\";\"Indefinido\";\"-\";\"-\";\"-\";\"-\";\"-\";\"-\";\"-\";\"-\";\r\n";

    @Test
    public void testGenerateCSVBody() throws Exception
    {
        final ContactPosition contactPosition = new ContactPosition();
        contactPosition.setActive(true);
        contactPosition.setName("Ana Sánchez ");
        contactPosition.setPosition("Responsable de dirección ");
        contactPosition.setOrganization("A.T.R.I.L CB");
        contactPosition.setDepartment("Indefinido");

        final List<ContactPosition> contactPositions = new ArrayList<ContactPosition>();
        contactPositions.add(contactPosition);
        
        final Map<String, String> texts = new HashMap<String, String>();
        texts.put(AdvancedSearchContactBean.MESSAGE_CONTACT_NAME, "Nombre");
        texts.put(AdvancedSearchContactBean.MESSAGE_CONTACT_POSITION, "Puesto");
        texts.put(AdvancedSearchContactBean.MESSAGE_CONTACT_ORGANIZATION, "Organización");
        texts.put(AdvancedSearchContactBean.MESSAGE_CONTACT_DEPARTMENT, "Departamento");
        texts.put(AdvancedSearchContactBean.MESSAGE_CONTACT_EMAIL, "Correo-e");
        texts.put(AdvancedSearchContactBean.MESSAGE_CONTACT_PHONE, "Teléfono");
        texts.put(AdvancedSearchContactBean.MESSAGE_CONTACT_FAX, "Fax");
        texts.put(AdvancedSearchContactBean.MESSAGE_CONTACT_COUNTRY, "Pais");
        texts.put(AdvancedSearchContactBean.MESSAGE_CONTACT_PROVINCE, "Province");
        texts.put(AdvancedSearchContactBean.MESSAGE_CONTACT_CITY, "Ciudad");
        texts.put(AdvancedSearchContactBean.MESSAGE_CONTACT_POSTALCODE, "Código postal");
        texts.put(AdvancedSearchContactBean.MESSAGE_CONTACT_ADDRESS, "Dirección");
        final AdvancedSearchContactCSVServlet instance = new AdvancedSearchContactCSVServlet();
        final String content = instance.generateCSVBody(contactPositions, texts);
        
        if (!content.equals(CSV_CONTENT)) {
            fail("\n\nThe result must be:\n\n>>>" + CSV_CONTENT + "<<<\n\ninstead of:\n\n>>>" + content + "<<<");
        }
    }
}