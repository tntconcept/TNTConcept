package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.Province;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.ProvinceSearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ProvinceDAO_IT extends IntegrationTest {
    private final ProvinceDAO provinceDAO;
    private final String nameFirstRow = "Alava";

    public ProvinceDAO_IT() {
        provinceDAO = (ProvinceDAO) SpringUtils.getSpringBean("daoProvince");
    }

    @Test
    public void shouldLoadById() {
        final Province result = provinceDAO.loadById(1);

        assertEquals(nameFirstRow, result.getName());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldThrownAnExceptionWhenIdDoesntExists() {
        final Province result = provinceDAO.loadById(100);

        assertNull(result);
    }

    @Test
    public void shouldGetById() {
        final Province result = provinceDAO.getById(1);

        assertEquals(nameFirstRow, result.getName());
    }

    @Test
    public void getByIdShouldReturnNullWhenIdDoesntExists() {
        final Province result = provinceDAO.getById(100);

        assertNull(result);
    }

    @Test
    public void searchShouldFindProvinces() {
        final List<Province> result = provinceDAO.search(new SortCriteria());

        assert result.size() > 0;
    }

    @Test
    public void searchShouldFindProvincesByCriteria() {
        final ProvinceSearch provinceSearch = new ProvinceSearch();
        provinceSearch.setName(nameFirstRow);

        final List<Province> result = provinceDAO.search(provinceSearch, new SortCriteria());

        assert result.size() > 0;
    }

    @Test
    public void updateShouldChangeProvince() {
        final String expectedName = "change";
        final Province province = provinceDAO.getById(1);
        province.setName(expectedName);

        provinceDAO.update(province);
        final Province result = provinceDAO.getById(1);

        assertEquals(expectedName, result.getName());
    }

    @Test
    public void shouldNotFindProvinceAfterDelete() {
        final Province province = provinceDAO.getById(1);

        provinceDAO.delete(province);
        final Province result = provinceDAO.getById(1);

        assertNull(result);
    }

    @Test
    public void insertShouldPersistProvince() {
        final String expectedName = "newProvince";
        final Province province = createProvince(expectedName);

        provinceDAO.insert(province);
        final List<Province> result = provinceDAO.search(new SortCriteria());

        assertEquals(expectedName, result.get(result.size() - 1).getName());
    }

    private Province createProvince(String name) {
        final Province province = new Province();
        province.setName(name);
        province.setId(90);
        return province;
    }
}
