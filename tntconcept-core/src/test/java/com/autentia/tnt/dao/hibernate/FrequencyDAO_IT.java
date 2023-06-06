package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.Frequency;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.FrequencySearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FrequencyDAO_IT extends IntegrationTest {
    final FrequencyDAO frequencyDAO;

    public FrequencyDAO_IT() {
        frequencyDAO = (FrequencyDAO) SpringUtils.getSpringBean("daoFrequency");
    }

    @Test
    public void loadByIdShouldLoadFrequency() {
        final int frequencyId = 1;

        final Frequency frequency = frequencyDAO.loadById(frequencyId);

        assertEquals("Mensual", frequency.getName());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldGetNullFrequency() {
        final int frequencyId = Integer.MAX_VALUE;

        final Frequency frequency = frequencyDAO.loadById(frequencyId);

        assertNull(frequency);
    }

    @Test
    public void getByIdShouldGetFrequency() {
        final int frequencyId = 1;

        final Frequency frequency = frequencyDAO.getById(frequencyId);

        assertEquals("Mensual", frequency.getName());
    }

    @Test
    public void getByIdShouldGetNullFrequency() {
        final int frequencyId = Integer.MAX_VALUE;

        final Frequency frequency = frequencyDAO.getById(frequencyId);

        assertNull(frequency);
    }

    @Test
    public void searchShouldReturnMoreThanTheDefaultFrequency() {
        Frequency frequency = createFrequency();
        frequencyDAO.insert(frequency);

        List<Frequency> frequencies = frequencyDAO.search(new SortCriteria());

        assert(frequencies.size() > 1);
    }

    @Test
    public void searchByCriteriaShouldReturnExpectedFrequency() {
        Frequency frequency = createFrequency();
        frequencyDAO.insert(frequency);

        FrequencySearch frequencySearch = new FrequencySearch();
        frequencySearch.setName(frequency.getName());
        List<Frequency> frequencies = frequencyDAO.search(frequencySearch, new SortCriteria());

        assert(frequencies.size() == 1);
    }

    @Test
    public void updateShouldChangeFrequencyName() {
        Frequency frequencyToUpdate = frequencyDAO.getById(1);
        frequencyToUpdate.setName("Update");

        frequencyDAO.update(frequencyToUpdate);

        Frequency updatedFrequency = frequencyDAO.getById(1);
        assertEquals("Update", updatedFrequency.getName());
    }

    @Test
    public void deleteShouldRemoveFrequency() {
        Frequency frequencyToDelete = frequencyDAO.getById(1);

        frequencyDAO.delete(frequencyToDelete);

        assertNull(frequencyDAO.getById(1));
    }

    private Frequency createFrequency() {
        Frequency frequency = new Frequency();
        frequency.setName("Test");
        frequency.setMonths(2);
        frequency.setOwnerId(1);
        frequency.setDepartmentId(1);

        return frequency;
    }
}