package com.autentia.tnt.manager.billing;

import com.autentia.tnt.businessobject.BillRegime;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.hibernate.BillRegimeDAO;
import com.autentia.tnt.util.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class BillRegimeManager {

    private static final Log log = LogFactory.getLog(BillRegimeManager.class);

    private BillRegimeDAO BillRegimeDAO;

    /**
     * Get default BillRegimeManager as defined in Spring's configuration file.
     * @return the default singleton BillRegimeManager
     */
    public static BillRegimeManager getDefault()
    {
        return (BillRegimeManager) SpringUtils.getSpringBean("managerBillRegime");
    }

    /**
     * Empty constructor needed by CGLIB (Spring AOP)
     */
    protected BillRegimeManager()
    {
    }

    /**
     * Default constructor
     * @deprecated do not construct managers alone: use Spring's declared beans
     */
    public BillRegimeManager(BillRegimeDAO BillRegimeDAO)
    {
        this.BillRegimeDAO = BillRegimeDAO;
    }

    /**
     * List BillRegimes.
     * @param sort sorting criteria
     * @return the list of all BillRegimes sorted by requested criterion
     */
    public List<BillRegime> getAllEntities(SortCriteria sort) {
        return BillRegimeDAO.search(sort);
    }

    /**
     * Get BillRegime by primary key.
     * @return BillRegime selected by id.
     */
    public BillRegime getEntityById(int id) {
        return BillRegimeDAO.loadById(id);
    }

    /**
     * Insert BillRegime.
     * @param BillRegime to be inserted
     */
    public void insertEntity(BillRegime BillRegime) {
        BillRegimeDAO.insert(BillRegime);
    }

    /**
     * Update existing BillRegime
     * @param BillRegime to be updated
     */
    public void updateEntity(BillRegime BillRegime) {
        BillRegimeDAO.update(BillRegime);
    }

    /**
     * Delete existing BillRegime
     * @param BillRegime to be deleted
     */
    public void deleteEntity(BillRegime BillRegime) {
        BillRegimeDAO.delete(BillRegime);
    }
}

