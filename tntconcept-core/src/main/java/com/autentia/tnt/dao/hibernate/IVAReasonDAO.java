package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.IVAReason;
import com.autentia.tnt.dao.DataAccException;
import com.autentia.tnt.dao.SearchCriteria;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.util.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class IVAReasonDAO extends HibernateManagerBase<IVAReason> {

    /** Logger */
    private static final Log log = LogFactory.getLog(IVAReason.class);

    /**
     * Get default IVAReasonDAO as defined in Spring's configuration file.
     * @return the default singleton IVAReasonDAO
     */
    public static IVAReasonDAO getDefault()
    {
        return (IVAReasonDAO) SpringUtils.getSpringBean("daoIVAReason");
    }

    /**
     * Retrieve a IVAReason object from database given its id
     * @param id primary key of IVAReason object
     * @return the IVAReason object identified by the id
     * @throws DataAccException on error
     */
    @Override
    public IVAReason getById(int id) throws DataAccException {
        return super.getByPk(IVAReason.class, id);
    }

    @Override
    public IVAReason loadById(int id) throws DataAccException {
        return loadByPk(IVAReason.class, id);
    }


    /**
     * Get all IVAReason objects from database sorted by the given criteria
     * @param crit the sorting criteria
     * @return a list with all existing IVAReason objects
     * @throws DataAccException on error
     */
    @Override
    public List<IVAReason> search(SortCriteria crit) throws DataAccException {
        return super.list(IVAReason.class, crit);
    }

    /**
     * Get specified IVAReason objects from database sorted by the given criteria
     * @param search search criteria
     * @param sort the sorting criteria
     * @return a list with IVAReason objects matching the search criteria
     * @throws DataAccException on error
     */
    @Override
    public List<IVAReason> search(SearchCriteria search, SortCriteria sort) throws DataAccException {
        return super.search(IVAReason.class, search, sort);
    }

    /**
     * Insert a new IVAReason object in database
     * @param dao the IVAReason object to insert
     * @throws DataAccException on error
     */
    @Override
    public void insert(IVAReason dao) throws DataAccException{
        super.insert(dao);
    }

    /**
     * Update an existing IVAReason object in database
     * @param dao the IVAReason object to update
     * @throws DataAccException on error
     */
    @Override
    public void update(IVAReason dao) throws DataAccException {
        super.update(dao, dao.getId());
    }

    /**
     * Delete an existing IVAReason object in database
     * @param dao the IVAReason object to update
     * @throws DataAccException on error
     */
    @Override
    public void delete(IVAReason dao) throws DataAccException {
        super.delete(dao, dao.getId());
    }
}
