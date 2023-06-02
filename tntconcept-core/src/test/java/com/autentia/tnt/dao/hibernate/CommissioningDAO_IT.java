package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.Commissioning;
import com.autentia.tnt.businessobject.CommissioningStatus;
import com.autentia.tnt.businessobject.Project;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.CommissioningSearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class CommissioningDAO_IT extends IntegrationTest {
    final CommissioningDAO commissioningDAO;

    public CommissioningDAO_IT() {
        commissioningDAO = (CommissioningDAO) SpringUtils.getSpringBean("daoCommissioning");
    }

    @Test
    public void loadByIdShouldLoadCommissioning() {
        final int commissioningId = 1;

        final Commissioning commissioning = commissioningDAO.loadById(commissioningId);

        assertEquals("Test", commissioning.getName());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldGetNullCommissioning() {
        final int commissioningId = Integer.MAX_VALUE;

        final Commissioning commissioning = commissioningDAO.loadById(commissioningId);

        assertNull(commissioning);
    }

    @Test
    public void getByIdShouldGetCommissioning() {
        final int commissioningId = 1;

        final Commissioning commissioning = commissioningDAO.getById(commissioningId);

        assertEquals("Test", commissioning.getName());
    }

    @Test
    public void getByIdShouldGetNullCommissioning() {
        final int commissioningId = Integer.MAX_VALUE;

        final Commissioning commissioning = commissioningDAO.getById(commissioningId);

        assertNull(commissioning);
    }

    @Test
    public void searchShouldReturnMoreThanTheDefaultCommissioning() {
        Commissioning commissioning = createCommissioning();
        commissioningDAO.insert(commissioning);

        List<Commissioning> commissionings = commissioningDAO.search(new SortCriteria());

        assert (commissionings.size() > 1);
    }

    @Test
    public void searchByCriteriaShouldReturnExpectedCommissioning() {
        Commissioning commissioning = createCommissioning();
        commissioningDAO.insert(commissioning);

        CommissioningSearch commissioningSearch = new CommissioningSearch();
        commissioningSearch.setName(commissioning.getName());
        List<Commissioning> commissionings = commissioningDAO.search(commissioningSearch, new SortCriteria());

        assert (commissionings.size() == 1);
    }

    @Test
    public void updateShouldChangeCommissioningName() {
        Commissioning commissioningToUpdate = commissioningDAO.getById(1);
        commissioningToUpdate.setName("Update");

        commissioningDAO.update(commissioningToUpdate);

        Commissioning updatedCommissioning = commissioningDAO.getById(1);
        assertEquals("Update", updatedCommissioning.getName());
    }

    @Test
    public void deleteShouldRemoveCommissioning() {
        Commissioning commissioningToDelete = commissioningDAO.getById(1);

        commissioningDAO.delete(commissioningToDelete);

        assertNull(commissioningDAO.getById(1));
    }

    private Commissioning createCommissioning() {
        Project project = new Project();
        project.setId(1);

        Commissioning commissioning = new Commissioning();
        commissioning.setRequestDate(new Date());
        commissioning.setName("Commissioning");
        commissioning.setScope("");
        commissioning.setContent("");
        commissioning.setProducts("");
        commissioning.setDeliveryDate(new Date());
        commissioning.setBudget(BigDecimal.ONE);
        commissioning.setNotes("");
        commissioning.setAuthorSignature(false);
        commissioning.setReviserSignature(false);
        commissioning.setAdminSignature(false);
        commissioning.setJustifyInformation(false);
        commissioning.setDevelopedActivities("");
        commissioning.setDifficultiesAppeared("");
        commissioning.setResults("");
        commissioning.setConclusions("");
        commissioning.setEvaluation("");
        commissioning.setStatus(CommissioningStatus.CREATED);
        commissioning.setOwnerId(1);
        commissioning.setDepartmentId(1);

        return commissioning;
    }
}