package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.*;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.BillSearch;
import com.autentia.tnt.test.utils.AccountForTesting;
import com.autentia.tnt.test.utils.ContactForTesting;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class BillDAO_IT extends IntegrationTest {
    final BillDAO billDAO;

    public BillDAO_IT() {
        billDAO = (BillDAO) SpringUtils.getSpringBean("daoBill");
    }

    @Test
    public void loadByIdShouldLoadBill() {
        final int billId = 1;

        final Bill bill = billDAO.loadById(billId);

        assertEquals("Test", bill.getName());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldGetNullBill() {
        final int billId = Integer.MAX_VALUE;

        final Bill bill = billDAO.loadById(billId);

        assertNull(bill);
    }

    @Test
    public void getByIdShouldGetBill() {
        final int billId = 1;

        final Bill bill = billDAO.getById(billId);

        assertEquals("Test", bill.getName());
    }

    @Test
    public void getByIdShouldGetNullBill() {
        final int billId = Integer.MAX_VALUE;

        final Bill bill = billDAO.getById(billId);

        assertNull(bill);
    }

    @Test
    public void searchShouldReturnMoreThanTheDefaultBill() {
        Bill bill = createBill();
        billDAO.insert(bill);

        List<Bill> bills = billDAO.search(new SortCriteria());

        assert(bills.size() > 1);
    }

    @Test
    public void searchByCriteriaShouldReturnExpectedBill() {
        Bill bill = createBill();
        billDAO.insert(bill);

        BillSearch billSearch = new BillSearch();
        billSearch.setName(bill.getName());
        List<Bill> bills = billDAO.search(billSearch, new SortCriteria());

        assert(bills.size() == 1);
    }

    @Test
    public void updateShouldChangeBillName() {
        Bill billToUpdate = billDAO.getById(1);
        billToUpdate.setName("Update");

        billDAO.update(billToUpdate);

        Bill updatedBill = billDAO.getById(1);
        assertEquals("Update", updatedBill.getName());
    }

    @Test
    public void deleteShouldRemoveBill() {
        Bill billToDelete = billDAO.getById(1);

        billDAO.delete(billToDelete);

        assertNull(billDAO.getById(1));
    }

    private Bill createBill() {
        Project project = new Project();
        project.setId(1);

        ContactForTesting contact = new ContactForTesting();
        contact.setId(1);

        Organization organization = new Organization();
        organization.setId(1);

        AccountForTesting account = new AccountForTesting();
        account.setId(1);

        BillCategory billCategory = new BillCategory();
        billCategory.setId(1);

        BillRegime billRegime = new BillRegime();
        billRegime.setId(1);

        Bill bill = new Bill();
        bill.setCreationDate(new Date());
        bill.setPaymentMode(BillPaymentMode.MONEY);
        bill.setState(BillState.EMITTED);
        bill.setNumber("Bill-001");
        bill.setName("Bill");
        bill.setObservations("");
        bill.setProject(project);
        bill.setStartBillDate(new Date());
        bill.setEndBillDate(new Date());
        bill.setBillType(BillType.ISSUED);
        bill.setOrderNumber("Bill-Test-001");
        bill.setContact(contact);
        bill.setProvider(organization);
        bill.setAccount(account);
        bill.setSubmitted(1);
        bill.setBillCategory(billCategory);
        bill.setProvideService(true);
        bill.setBillRegime(billRegime);
        bill.setDeductibleIVAPercentage(0);
        bill.setFreelanceIRPFPercentage(BigDecimal.TEN);
        bill.setOwnerId(1);
        bill.setDepartmentId(1);

        return bill;
    }
}