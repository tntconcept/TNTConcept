package com.autentia.tnt.manager.billing;

import com.autentia.tnt.businessobject.RectifiedBillCategory;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.hibernate.RectifiedBillCategoryDAO;
import com.autentia.tnt.util.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class RectifiedBillCategoryManager {

    private static final Log log = LogFactory.getLog(RectifiedBillCategoryManager.class);

    private RectifiedBillCategoryDAO RectifiedBillCategoryDAO;

    /**
     * Get default RectifiedBillCategoryManager as defined in Spring's configuration file.
     * @return the default singleton RectifiedBillCategoryManager
     */
    public static RectifiedBillCategoryManager getDefault()
    {
        return (RectifiedBillCategoryManager) SpringUtils.getSpringBean("managerRectifiedBillCategory");
    }

    /**
     * Empty constructor needed by CGLIB (Spring AOP)
     */
    protected RectifiedBillCategoryManager()
    {
    }

    /**
     * Default constructor
     * @deprecated do not construct managers alone: use Spring's declared beans
     */
    public RectifiedBillCategoryManager( RectifiedBillCategoryDAO RectifiedBillCategoryDAO )
    {
        this.RectifiedBillCategoryDAO = RectifiedBillCategoryDAO;
    }

    /**
     * List RectifiedBillCategorys.
     * @param sort sorting criteria
     * @return the list of all RectifiedBillCategorys sorted by requested criterion
     */
    public List<RectifiedBillCategory> getAllEntities(SortCriteria sort) {
        return RectifiedBillCategoryDAO.search(sort);
    }

    /**
     * Get RectifiedBillCategory by primary key.
     * @return RectifiedBillCategory selected by id.
     */
    public RectifiedBillCategory getEntityById(int id) {
        return RectifiedBillCategoryDAO.loadById(id);
    }

    /**
     * Insert RectifiedBillCategory.
     * @param RectifiedBillCategory to be inserted
     */
    public void insertEntity(RectifiedBillCategory RectifiedBillCategory) {
        RectifiedBillCategoryDAO.insert(RectifiedBillCategory);
    }

    /**
     * Update existing RectifiedBillCategory
     * @param RectifiedBillCategory to be updated
     */
    public void updateEntity(RectifiedBillCategory RectifiedBillCategory) {
        RectifiedBillCategoryDAO.update(RectifiedBillCategory);
    }

    /**
     * Delete existing RectifiedBillCategory
     * @param RectifiedBillCategory to be deleted
     */
    public void deleteEntity(RectifiedBillCategory RectifiedBillCategory) {
        RectifiedBillCategoryDAO.delete(RectifiedBillCategory);
    }
}
