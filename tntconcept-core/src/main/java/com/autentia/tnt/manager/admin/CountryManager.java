package com.autentia.tnt.manager.admin;

import com.autentia.tnt.businessobject.Country;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.util.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.dao.hibernate.CountryDAO;

import java.util.List;

public class CountryManager {

    private static final Log log = LogFactory.getLog(CountryManager.class);

    private CountryDAO countryDAO;

    /**
     * Get default CountryManager as defined in Spring's configuration file.
     * @return the default singleton CountryManager
     */
    public static CountryManager getDefault()
    {
        return (CountryManager) SpringUtils.getSpringBean("managerCountry");
    }

    /**
     * Empty constructor needed by CGLIB (Spring AOP)
     */
    protected CountryManager()
    {
    }

    /**
     * Default constructor
     * @deprecated do not construct managers alone: use Spring's declared beans
     */
    public CountryManager( CountryDAO countryDAO )
    {
        this.countryDAO = countryDAO;
    }

    /**
     * List Countries.
     * @param sort sorting criteria
     * @return the list of all countries sorted by requested criterion
     */
    public List<Country> getAllEntities(SortCriteria sort) {
        return countryDAO.search(sort);
    }

    /**
     * Get Country by primary key.
     * @return country selected by id.
     */
    public Country getEntityById(int id){
        return countryDAO.loadById(id);
    }

    /**
     * Insert country.
     * @param country to be inserted
     */
    public void insertEntity(Country country) {
        countryDAO.insert(country);
    }

    /**
     * Update existing country
     * @param country to be updated
     */
    public void updateEntity(Country country) {
        countryDAO.update(country);
    }

    /**
     * Delete existing country
     * @param country to be deleted
     */
    public void deleteEntity(Country country) {
        countryDAO.delete(country);
    }
}
