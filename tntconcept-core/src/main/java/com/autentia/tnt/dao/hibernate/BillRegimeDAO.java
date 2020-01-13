package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.BillRegime;
import com.autentia.tnt.dao.DataAccException;
import com.autentia.tnt.dao.SearchCriteria;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.util.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class BillRegimeDAO extends HibernateManagerBase<BillRegime> {

    /** Logger */
    private static final Log log = LogFactory.getLog(BillRegime.class);

    /**
     * Get default BillRegimeDAO as defined in Spring's configuration file.
     * @return the default singleton BillRegimeDAO
     */
    public static BillRegimeDAO getDefault()
    {
        return (BillRegimeDAO) SpringUtils.getSpringBean("daoBillRegime");
    }

    /**
     * Retrieve a BillRegime object from database given its id
     * @param id primary key of BillRegime object
     * @return the BillRegime object identified by the id
     * @throws DataAccException on error
     */
    @Override
    public BillRegime getById(int id) throws DataAccException {
        return super.getByPk(BillRegime.class, id);
    }

    @Override
    public BillRegime loadById(int id) throws DataAccException {
        return loadByPk(BillRegime.class, id);
    }


    /**
     * Get all BillRegime objects from database sorted by the given criteria
     * @param crit the sorting criteria
     * @return a list with all existing BillRegime objects
     * @throws DataAccException on error
     */
    @Override
    public List<BillRegime> search(SortCriteria crit) throws DataAccException {
        return super.list(BillRegime.class, crit);
    }

    /**
     * Get specified BillRegime objects from database sorted by the given criteria
     * @param search search criteria
     * @param sort the sorting criteria
     * @return a list with BillRegime objects matching the search criteria
     * @throws DataAccException on error
     */
    @Override
    public List<BillRegime> search(SearchCriteria search, SortCriteria sort) throws DataAccException {
        return super.search(BillRegime.class, search, sort);
    }

    /**
     * Insert a new BillRegime object in database
     * @param dao the BillRegime object to insert
     * @throws DataAccException on error
     */
    @Override
    public void insert(BillRegime dao) throws DataAccException{
        super.insert(dao);
    }

    /**
     * Update an existing BillRegime object in database
     * @param dao the BillRegime object to update
     * @throws DataAccException on error
     */
    @Override
    public void update(BillRegime dao) throws DataAccException {
        super.update(dao, dao.getId());
    }

    /**
     * Delete an existing BillRegime object in database
     * @param dao the BillRegime object to update
     * @throws DataAccException on error
     */
    @Override
    public void delete(BillRegime dao) throws DataAccException {
        super.delete(dao, dao.getId());
    }
}
