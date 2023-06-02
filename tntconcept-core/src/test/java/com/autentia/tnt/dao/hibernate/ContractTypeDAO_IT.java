package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.ContractType;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.ContractTypeSearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ContractTypeDAO_IT extends IntegrationTest {
    final ContractTypeDAO contractTypeDAO;

    public ContractTypeDAO_IT() {
        contractTypeDAO = (ContractTypeDAO) SpringUtils.getSpringBean("daoContractType");
    }

    @Test
    public void loadByIdShouldLoadContractType() {
        final int contractTypeId = 1;

        final ContractType contractType = contractTypeDAO.loadById(contractTypeId);

        assertEquals("Practicas", StringUtils.stripAccents(contractType.getName()));
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldGetNullContractType() {
        final int contractTypeId = Integer.MAX_VALUE;

        final ContractType contractType = contractTypeDAO.loadById(contractTypeId);

        assertNull(contractType);
    }

    @Test
    public void getByIdShouldGetContractType() {
        final int contractTypeId = 1;

        final ContractType contractType = contractTypeDAO.getById(contractTypeId);

        assertEquals("Practicas", StringUtils.stripAccents(contractType.getName()));
    }

    @Test
    public void getByIdShouldGetNullContractType() {
        final int contractTypeId = Integer.MAX_VALUE;

        final ContractType contractType = contractTypeDAO.getById(contractTypeId);

        assertNull(contractType);
    }

    @Test
    public void searchShouldReturnMoreThanTheDefaultContractType() {
        ContractType contractType = createContractType();
        contractTypeDAO.insert(contractType);

        List<ContractType> contractTypes = contractTypeDAO.search(new SortCriteria());

        assert (contractTypes.size() > 1);
    }

    @Test
    public void searchByCriteriaShouldReturnExpectedContractType() {
        ContractType contractType = createContractType();
        contractTypeDAO.insert(contractType);

        ContractTypeSearch contractTypeSearch = new ContractTypeSearch();
        contractTypeSearch.setName(contractType.getName());
        List<ContractType> contractTypes = contractTypeDAO.search(contractTypeSearch, new SortCriteria());

        assert (contractTypes.size() == 1);
    }

    @Test
    public void updateShouldChangeContractTypeName() {
        ContractType contractTypeToUpdate = contractTypeDAO.getById(1);
        contractTypeToUpdate.setName("Update");

        contractTypeDAO.update(contractTypeToUpdate);

        ContractType updatedContractType = contractTypeDAO.getById(1);
        assertEquals("Update", updatedContractType.getName());
    }

    @Test
    public void deleteShouldRemoveContractType() {
        ContractType contractTypeToDelete = contractTypeDAO.getById(1);

        contractTypeDAO.delete(contractTypeToDelete);

        assertNull(contractTypeDAO.getById(1));
    }

    private ContractType createContractType() {
        ContractType contractType = new ContractType();
        contractType.setName("Test");
        contractType.setDescription("");
        contractType.setOwnerId(1);
        contractType.setDepartmentId(1);

        return contractType;
    }
}