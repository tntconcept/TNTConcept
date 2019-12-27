package com.autentia.tnt.manager.billing;

import com.autentia.tnt.businessobject.IVAType;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.util.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.dao.hibernate.IVATypeDAO;

import java.util.List;

public class IVATypeManager {

    private static final Log log = LogFactory.getLog(IVATypeManager.class);

    private IVATypeDAO ivaTypeDAO;

    /**
     * Get default IVATypeManager as defined in Spring's configuration file.
     * @return the default singleton IVATypeManager
     */
    public static IVATypeManager getDefault()
    {
        return (IVATypeManager) SpringUtils.getSpringBean("managerIVAType");
    }

    /**
     * Empty constructor needed by CGLIB (Spring AOP)
     */
    protected IVATypeManager()
    {
    }

    /**
     * Default constructor
     * @deprecated do not construct managers alone: use Spring's declared beans
     */
    public IVATypeManager( IVATypeDAO ivaTypeDAO )
    {
        this.ivaTypeDAO = ivaTypeDAO;
    }

    /**
     * List Countries.
     * @param sort sorting criteria
     * @return the list of all ivatypes sorted by requested criterion
     */
    public List<IVAType> getAllEntities(SortCriteria sort) {
        return ivaTypeDAO.search(sort);
    }

    /**
     * Get IVAType by primary key.
     * @return ivaType selected by id.
     */
    public IVAType getEntityById(int id){
        return ivaTypeDAO.loadById(id);
    }

    /**
     * Insert ivaType.
     * @param ivaType to be inserted
     */
    public void insertEntity(IVAType ivaType) {
        ivaTypeDAO.insert(ivaType);
    }

    /**
     * Update existing ivaType
     * @param ivaType to be updated
     */
    public void updateEntity(IVAType ivaType) {
        ivaTypeDAO.update(ivaType);
    }

    /**
     * Delete existing ivaType
     * @param ivaType to be deleted
     */
    public void deleteEntity(IVAType ivaType) {
        ivaTypeDAO.delete(ivaType);
    }
}
