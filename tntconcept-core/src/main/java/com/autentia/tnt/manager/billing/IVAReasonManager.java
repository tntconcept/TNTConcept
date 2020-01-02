package com.autentia.tnt.manager.billing;

import com.autentia.tnt.businessobject.IVAReason;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.hibernate.IVAReasonDAO;
import com.autentia.tnt.util.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class IVAReasonManager {

    private static final Log log = LogFactory.getLog(IVAReasonManager.class);

    private IVAReasonDAO IVAReasonDAO;

    /**
     * Get default IVAReasonManager as defined in Spring's configuration file.
     * @return the default singleton IVAReasonManager
     */
    public static IVAReasonManager getDefault()
    {
        return (IVAReasonManager) SpringUtils.getSpringBean("managerIVAReason");
    }

    /**
     * Empty constructor needed by CGLIB (Spring AOP)
     */
    protected IVAReasonManager()
    {
    }

    /**
     * Default constructor
     * @deprecated do not construct managers alone: use Spring's declared beans
     */
    public IVAReasonManager( IVAReasonDAO IVAReasonDAO )
    {
        this.IVAReasonDAO = IVAReasonDAO;
    }

    /**
     * List IVAReasons.
     * @param sort sorting criteria
     * @return the list of all IVAReasons sorted by requested criterion
     */
    public List<IVAReason> getAllEntities(SortCriteria sort) {
        return IVAReasonDAO.search(sort);
    }

    /**
     * Get IVAReason by primary key.
     * @return IVAReason selected by id.
     */
    public IVAReason getEntityById(int id) {
        return IVAReasonDAO.loadById(id);
    }

    /**
     * Insert IVAReason.
     * @param IVAReason to be inserted
     */
    public void insertEntity(IVAReason IVAReason) {
        IVAReasonDAO.insert(IVAReason);
    }

    /**
     * Update existing IVAReason
     * @param IVAReason to be updated
     */
    public void updateEntity(IVAReason IVAReason) {
        IVAReasonDAO.update(IVAReason);
    }

    /**
     * Delete existing IVAReason
     * @param IVAReason to be deleted
     */
    public void deleteEntity(IVAReason IVAReason) {
        IVAReasonDAO.delete(IVAReason);
    }
}
