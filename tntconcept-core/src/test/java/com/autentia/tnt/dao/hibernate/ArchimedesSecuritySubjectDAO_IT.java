package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.ArchimedesSecuritySubject;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArchimedesSecuritySubjectDAO_IT extends IntegrationTest {

    final ArchimedesSecuritySubjectDAO archimedesSecuritySubjectDAO;

    public ArchimedesSecuritySubjectDAO_IT() {
        archimedesSecuritySubjectDAO = (ArchimedesSecuritySubjectDAO) SpringUtils.getSpringBean("daoArchimedesSecuritySubject");
    }

    @Test
    public void updateShouldChangeAttributesToSecuritySubject() {
        final String principalName = "admin@autentia.com";
        final String attributes = "{\"sub\":\"3\"}";

        ArchimedesSecuritySubject archimedesSecuritySubject = archimedesSecuritySubjectDAO.getByPrincipalName(principalName);
        archimedesSecuritySubject.setAttributes(attributes);

        archimedesSecuritySubjectDAO.update(archimedesSecuritySubject);

        ArchimedesSecuritySubject archimedesSecuritySubjectUpdated = archimedesSecuritySubjectDAO.getByPrincipalName(principalName);
        assertEquals(attributes, archimedesSecuritySubjectUpdated.getAttributes());
    }

    @Test
    public void getByPrincipalNameShouldReturnExpectedSecuritySubject() {
        ArchimedesSecuritySubject archimedesSecuritySubjectToInsert = createArchimedesSecuritySubject();
        archimedesSecuritySubjectDAO.insert(archimedesSecuritySubjectToInsert);

        ArchimedesSecuritySubject archimedesSecuritySubject = archimedesSecuritySubjectDAO.getByPrincipalName(archimedesSecuritySubjectToInsert.getPrincipalName());

        assertEquals(archimedesSecuritySubjectToInsert.getPrincipalName(), archimedesSecuritySubject.getPrincipalName());
    }

    private ArchimedesSecuritySubject createArchimedesSecuritySubject() {
        ArchimedesSecuritySubject archimedesSecuritySubject = new ArchimedesSecuritySubject();

        archimedesSecuritySubject.setPrincipalName("test@autentia.com");
        archimedesSecuritySubject.setAttributes("{\"sub\":\"3\"}");
        archimedesSecuritySubject.setDepartmentId(1);
        archimedesSecuritySubject.setOwnerId(1);

        return archimedesSecuritySubject;
    }
}