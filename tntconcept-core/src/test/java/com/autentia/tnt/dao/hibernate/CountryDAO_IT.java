package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.Country;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CountryDAO_IT extends IntegrationTest {
    final CountryDAO countryDAO;

    public CountryDAO_IT() {
        countryDAO = (CountryDAO) SpringUtils.getSpringBean("daoCountry");
    }

    @Test
    public void loadByIdShouldLoadCountry() {
        final int countryId = 2;

        final Country country = countryDAO.loadById(countryId);

        assertEquals("Islas Gland", country.getName());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldGetNullCountry() {
        final int countryId = Integer.MAX_VALUE;

        final Country country = countryDAO.loadById(countryId);

        assertNull(country);
    }

    @Test
    public void getByIdShouldGetCountry() {
        final int countryId = 2;

        final Country country = countryDAO.getById(countryId);

        assertEquals("Islas Gland", country.getName());
    }

    @Test
    public void getByIdShouldGetNullCountry() {
        final int countryId = Integer.MAX_VALUE;

        final Country country = countryDAO.getById(countryId);

        assertNull(country);
    }

    @Test
    public void searchShouldReturnMoreThanTheDefaultCountry() {
        Country country = createCountry();
        countryDAO.insert(country);

        List<Country> countries = countryDAO.search(new SortCriteria());

        assert(countries.size() > 1);
    }

    @Test
    public void updateShouldChangeCountryName() {
        Country countryToUpdate = countryDAO.getById(1);
        countryToUpdate.setName("Update");

        countryDAO.update(countryToUpdate);

        Country updatedCountry = countryDAO.getById(1);
        assertEquals("Update", updatedCountry.getName());
    }

    @Test
    public void deleteShouldRemoveCountry() {
        Country countryToDelete = countryDAO.getById(1);

        countryDAO.delete(countryToDelete);

        assertNull(countryDAO.getById(1));
    }

    private Country createCountry() {
        Country country = new Country();
        country.setId(Integer.MAX_VALUE);
        country.setCode(1);
        country.setIso3166a1("AA");
        country.setIso3166a2("BB");
        country.setName("Country");
        country.setOwnerId(1);
        country.setDepartmentId(1);

        return country;
    }
}