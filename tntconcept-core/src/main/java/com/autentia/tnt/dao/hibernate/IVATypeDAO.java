package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.IVAType;
import com.autentia.tnt.dao.DataAccException;
import com.autentia.tnt.dao.SearchCriteria;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.util.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class IVATypeDAO extends HibernateManagerBase<IVAType>{

    /** Logger */
    private static final Log log = LogFactory.getLog(IVATypeDAO.class);

    /**
     * Get default IVATypeDAO as defined in Spring's configuration file.
     * @return the default singleton IVATypeDAO
     */
    public static IVATypeDAO getDefault()
    {
        return (IVATypeDAO) SpringUtils.getSpringBean("daoIVAType");
    }


    /**
     * Retrieve a IVAType object from database given its id
     * @param id primary key of IVAType object
     * @return the IVAType object identified by the id
     * @throws DataAccException on error
     */
    @Override
    public IVAType getById(int id) throws DataAccException {
        return super.getByPk(IVAType.class, id);
    }

    @Override
    public IVAType loadById(int id) throws DataAccException {
        return loadByPk(IVAType.class, id);
    }

    /**
     * Get all IVAType objects from database sorted by the given criteria
     * @param crit the sorting criteria
     * @return a list with all existing IVAType objects
     * @throws DataAccException on error
     */
    @Override
    public List<IVAType> search(SortCriteria crit) throws DataAccException {
        return super.list(IVAType.class, crit);
    }

    /**
     * Get specified IVAType objects from database sorted by the given criteria
     * @param search search criteria
     * @param sort the sorting criteria
     * @return a list with IVAType objects matching the search criteria
     * @throws DataAccException on error
     */
    @Override
    public List<IVAType> search(SearchCriteria search, SortCriteria sort) throws DataAccException {
        return super.search(IVAType.class, search, sort);
    }

    /**
     * Insert a new IVAType object in database
     * @param dao the IVAType object to insert
     * @throws DataAccException on error
     */
    @Override
    public void insert(IVAType dao) throws DataAccException{
        super.insert(dao);
    }

    /**
     * Update an existing IVAType object in database
     * @param dao the IVAType object to update
     * @throws DataAccException on error
     */
    @Override
    public void update(IVAType dao) throws DataAccException {
        super.update(dao, dao.getId());
    }

    /**
     * Delete an existing IVAType object in database
     * @param dao the IVAType object to update
     * @throws DataAccException on error
     */
    @Override
    public void delete(IVAType dao) throws DataAccException {
        super.delete(dao, dao.getId());
    }
}
