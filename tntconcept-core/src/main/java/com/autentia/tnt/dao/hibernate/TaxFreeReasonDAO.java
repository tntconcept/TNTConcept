package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.TaxFreeReason;
import com.autentia.tnt.dao.DataAccException;
import com.autentia.tnt.dao.SearchCriteria;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.util.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class TaxFreeReasonDAO extends HibernateManagerBase<TaxFreeReason> {

    /** Logger */
    private static final Log log = LogFactory.getLog(TaxFreeReason.class);

    /**
     * Get default TaxFreeReasonDAO as defined in Spring's configuration file.
     * @return the default singleton TaxFreeReasonDAO
     */
    public static CountryDAO getDefault()
    {
        return (CountryDAO) SpringUtils.getSpringBean("daoTaxFreeReason");
    }

    /**
     * Retrieve a TaxFreeReason object from database given its id
     * @param id primary key of TaxFreeReason object
     * @return the TaxFreeReason object identified by the id
     * @throws DataAccException on error
     */
    @Override
    public TaxFreeReason getById(int id) throws DataAccException {
        return super.getByPk(TaxFreeReason.class, id);
    }

    @Override
    public TaxFreeReason loadById(int id) throws DataAccException {
        return loadByPk(TaxFreeReason.class, id);
    }


    /**
     * Get all TaxFreeReason objects from database sorted by the given criteria
     * @param crit the sorting criteria
     * @return a list with all existing TaxFreeReason objects
     * @throws DataAccException on error
     */
    @Override
    public List<TaxFreeReason> search(SortCriteria crit) throws DataAccException {
        return super.list(TaxFreeReason.class, crit);
    }

    /**
     * Get specified TaxFreeReason objects from database sorted by the given criteria
     * @param search search criteria
     * @param sort the sorting criteria
     * @return a list with TaxFreeReason objects matching the search criteria
     * @throws DataAccException on error
     */
    @Override
    public List<TaxFreeReason> search(SearchCriteria search, SortCriteria sort) throws DataAccException {
        return super.search(TaxFreeReason.class, search, sort);
    }

    /**
     * Insert a new TaxFreeReason object in database
     * @param dao the TaxFreeReason object to insert
     * @throws DataAccException on error
     */
    @Override
    public void insert(TaxFreeReason dao) throws DataAccException{
        super.insert(dao);
    }

    /**
     * Update an existing TaxFreeReason object in database
     * @param dao the TaxFreeReason object to update
     * @throws DataAccException on error
     */
    @Override
    public void update(TaxFreeReason dao) throws DataAccException {
        super.update(dao, dao.getId());
    }

    /**
     * Delete an existing TaxFreeReason object in database
     * @param dao the TaxFreeReason object to update
     * @throws DataAccException on error
     */
    @Override
    public void delete(TaxFreeReason dao) throws DataAccException {
        super.delete(dao, dao.getId());
    }
}
