package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.BillCategory;
import com.autentia.tnt.dao.DataAccException;
import com.autentia.tnt.dao.SearchCriteria;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.util.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class BillCategoryDAO extends HibernateManagerBase<BillCategory> {

    /** Logger */
    private static final Log log = LogFactory.getLog(BillCategory.class);

    /**
     * Get default BillCategoryDAO as defined in Spring's configuration file.
     * @return the default singleton BillCategoryDAO
     */
    public static BillCategoryDAO getDefault()
    {
        return (BillCategoryDAO) SpringUtils.getSpringBean("daoBillCategory");
    }

    /**
     * Retrieve a BillCategory object from database given its id
     * @param id primary key of BillCategory object
     * @return the BillCategory object identified by the id
     * @throws DataAccException on error
     */
    @Override
    public BillCategory getById(int id) throws DataAccException {
        return super.getByPk(BillCategory.class, id);
    }

    @Override
    public BillCategory loadById(int id) throws DataAccException {
        return loadByPk(BillCategory.class, id);
    }


    /**
     * Get all BillCategory objects from database sorted by the given criteria
     * @param crit the sorting criteria
     * @return a list with all existing BillCategory objects
     * @throws DataAccException on error
     */
    @Override
    public List<BillCategory> search(SortCriteria crit) throws DataAccException {
        return super.list(BillCategory.class, crit);
    }

    /**
     * Get specified BillCategory objects from database sorted by the given criteria
     * @param search search criteria
     * @param sort the sorting criteria
     * @return a list with BillCategory objects matching the search criteria
     * @throws DataAccException on error
     */
    @Override
    public List<BillCategory> search(SearchCriteria search, SortCriteria sort) throws DataAccException {
        return super.search(BillCategory.class, search, sort);
    }

    /**
     * Insert a new BillCategory object in database
     * @param dao the BillCategory object to insert
     * @throws DataAccException on error
     */
    @Override
    public void insert(BillCategory dao) throws DataAccException{
        super.insert(dao);
    }

    /**
     * Update an existing BillCategory object in database
     * @param dao the BillCategory object to update
     * @throws DataAccException on error
     */
    @Override
    public void update(BillCategory dao) throws DataAccException {
        super.update(dao, dao.getId());
    }

    /**
     * Delete an existing BillCategory object in database
     * @param dao the BillCategory object to update
     * @throws DataAccException on error
     */
    @Override
    public void delete(BillCategory dao) throws DataAccException {
        super.delete(dao, dao.getId());
    }
}
