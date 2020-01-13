package com.autentia.tnt.manager.billing;

import com.autentia.tnt.businessobject.BillCategory;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.hibernate.BillCategoryDAO;
import com.autentia.tnt.util.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class BillCategoryManager {

    private static final Log log = LogFactory.getLog(BillCategoryManager.class);

    private BillCategoryDAO BillCategoryDAO;

    /**
     * Get default BillCategoryManager as defined in Spring's configuration file.
     * @return the default singleton BillCategoryManager
     */
    public static BillCategoryManager getDefault()
    {
        return (BillCategoryManager) SpringUtils.getSpringBean("managerBillCategory");
    }

    /**
     * Empty constructor needed by CGLIB (Spring AOP)
     */
    protected BillCategoryManager()
    {
    }

    /**
     * Default constructor
     * @deprecated do not construct managers alone: use Spring's declared beans
     */
    public BillCategoryManager( BillCategoryDAO BillCategoryDAO )
    {
        this.BillCategoryDAO = BillCategoryDAO;
    }

    /**
     * List BillCategorys.
     * @param sort sorting criteria
     * @return the list of all BillCategorys sorted by requested criterion
     */
    public List<BillCategory> getAllEntities(SortCriteria sort) {
        return BillCategoryDAO.search(sort);
    }

    /**
     * Get BillCategory by primary key.
     * @return BillCategory selected by id.
     */
    public BillCategory getEntityById(int id) {
        return BillCategoryDAO.loadById(id);
    }

    /**
     * Insert BillCategory.
     * @param BillCategory to be inserted
     */
    public void insertEntity(BillCategory BillCategory) {
        BillCategoryDAO.insert(BillCategory);
    }

    /**
     * Update existing BillCategory
     * @param BillCategory to be updated
     */
    public void updateEntity(BillCategory BillCategory) {
        BillCategoryDAO.update(BillCategory);
    }

    /**
     * Delete existing BillCategory
     * @param BillCategory to be deleted
     */
    public void deleteEntity(BillCategory BillCategory) {
        BillCategoryDAO.delete(BillCategory);
    }
}

