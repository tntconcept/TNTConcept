package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.RectifiedBillCategory;
import com.autentia.tnt.dao.DataAccException;
import com.autentia.tnt.dao.SearchCriteria;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.util.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class RectifiedBillCategoryDAO extends HibernateManagerBase<RectifiedBillCategory> {

    /** Logger */
    private static final Log log = LogFactory.getLog(RectifiedBillCategory.class);

    /**
     * Get default RectifiedBillCategoryDAO as defined in Spring's configuration file.
     * @return the default singleton RectifiedBillCategoryDAO
     */
    public static RectifiedBillCategoryDAO getDefault()
    {
        return (RectifiedBillCategoryDAO) SpringUtils.getSpringBean("daoRectifiedBillCategory");
    }

    /**
     * Retrieve a RectifiedBillCategory object from database given its id
     * @param id primary key of RectifiedBillCategory object
     * @return the RectifiedBillCategory object identified by the id
     * @throws DataAccException on error
     */
    @Override
    public RectifiedBillCategory getById(int id) throws DataAccException {
        return super.getByPk(RectifiedBillCategory.class, id);
    }

    @Override
    public RectifiedBillCategory loadById(int id) throws DataAccException {
        return loadByPk(RectifiedBillCategory.class, id);
    }


    /**
     * Get all RectifiedBillCategory objects from database sorted by the given criteria
     * @param crit the sorting criteria
     * @return a list with all existing RectifiedBillCategory objects
     * @throws DataAccException on error
     */
    @Override
    public List<RectifiedBillCategory> search(SortCriteria crit) throws DataAccException {
        return super.list(RectifiedBillCategory.class, crit);
    }

    /**
     * Get specified RectifiedBillCategory objects from database sorted by the given criteria
     * @param search search criteria
     * @param sort the sorting criteria
     * @return a list with RectifiedBillCategory objects matching the search criteria
     * @throws DataAccException on error
     */
    @Override
    public List<RectifiedBillCategory> search(SearchCriteria search, SortCriteria sort) throws DataAccException {
        return super.search(RectifiedBillCategory.class, search, sort);
    }

    /**
     * Insert a new RectifiedBillCategory object in database
     * @param dao the RectifiedBillCategory object to insert
     * @throws DataAccException on error
     */
    @Override
    public void insert(RectifiedBillCategory dao) throws DataAccException{
        super.insert(dao);
    }

    /**
     * Update an existing RectifiedBillCategory object in database
     * @param dao the RectifiedBillCategory object to update
     * @throws DataAccException on error
     */
    @Override
    public void update(RectifiedBillCategory dao) throws DataAccException {
        super.update(dao, dao.getId());
    }

    /**
     * Delete an existing RectifiedBillCategory object in database
     * @param dao the RectifiedBillCategory object to update
     * @throws DataAccException on error
     */
    @Override
    public void delete(RectifiedBillCategory dao) throws DataAccException {
        super.delete(dao, dao.getId());
    }
}
