package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.Country;
import com.autentia.tnt.dao.DataAccException;
import com.autentia.tnt.dao.SearchCriteria;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.util.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class CountryDAO extends HibernateManagerBase<Country>{

    /** Logger */
    private static final Log log = LogFactory.getLog(CountryDAO.class);

    /**
     * Get default CountryDAO as defined in Spring's configuration file.
     * @return the default singleton CountryDAO
     */
    public static CountryDAO getDefault()
    {
        return (CountryDAO) SpringUtils.getSpringBean("daoCountry");
    }


    /**
     * Retrieve a Country object from database given its id
     * @param id primary key of Country object
     * @return the Country object identified by the id
     * @throws DataAccException on error
     */
    @Override
    public Country getById(int id) throws DataAccException {
        return super.getByPk(Country.class, id);
    }

    @Override
    public Country loadById(int id) throws DataAccException {
        return loadByPk(Country.class, id);
    }

    /**
     * Get all Country objects from database sorted by the given criteria
     * @param crit the sorting criteria
     * @return a list with all existing Country objects
     * @throws DataAccException on error
     */
    @Override
    public List<Country> search(SortCriteria crit) throws DataAccException {
        return super.list(Country.class, crit);
    }

    /**
     * Get specified Country objects from database sorted by the given criteria
     * @param search search criteria
     * @param sort the sorting criteria
     * @return a list with Country objects matching the search criteria
     * @throws DataAccException on error
     */
    @Override
    public List<Country> search(SearchCriteria search, SortCriteria sort) throws DataAccException {
        return super.search(Country.class, search, sort);
    }

    /**
     * Insert a new Country object in database
     * @param dao the Country object to insert
     * @throws DataAccException on error
     */
    @Override
    public void insert(Country dao) throws DataAccException{
        super.insert(dao);
    }

    /**
     * Update an existing Country object in database
     * @param dao the Country object to update
     * @throws DataAccException on error
     */
    @Override
    public void update(Country dao) throws DataAccException {
        super.update(dao, dao.getId());
    }

    /**
     * Delete an existing Country object in database
     * @param dao the Country object to update
     * @throws DataAccException on error
     */
    @Override
    public void delete(Country dao) throws DataAccException {
        super.delete(dao, dao.getId());
    }
}
