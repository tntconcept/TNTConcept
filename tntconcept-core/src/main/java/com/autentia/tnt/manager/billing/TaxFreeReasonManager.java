package com.autentia.tnt.manager.billing;

import com.autentia.tnt.businessobject.Country;
import com.autentia.tnt.businessobject.TaxFreeReason;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.hibernate.CountryDAO;
import com.autentia.tnt.dao.hibernate.TaxFreeReasonDAO;
import com.autentia.tnt.manager.admin.CountryManager;
import com.autentia.tnt.util.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class TaxFreeReasonManager {

    private static final Log log = LogFactory.getLog(TaxFreeReasonManager.class);

    private TaxFreeReasonDAO taxFreeReasonDAO;

    /**
     * Get default TaxFreeReasonManager as defined in Spring's configuration file.
     * @return the default singleton TaxFreeReasonManager
     */
    public static TaxFreeReasonManager getDefault()
    {
        return (TaxFreeReasonManager) SpringUtils.getSpringBean("managerTaxFreeReason");
    }

    /**
     * Empty constructor needed by CGLIB (Spring AOP)
     */
    protected TaxFreeReasonManager()
    {
    }

    /**
     * Default constructor
     * @deprecated do not construct managers alone: use Spring's declared beans
     */
    public TaxFreeReasonManager( TaxFreeReasonDAO taxFreeReasonDAO )
    {
        this.taxFreeReasonDAO = taxFreeReasonDAO;
    }

    /**
     * List TaxFreeReasons.
     * @param sort sorting criteria
     * @return the list of all taxFreeReasons sorted by requested criterion
     */
    public List<TaxFreeReason> getAllEntities(SortCriteria sort) {
        return taxFreeReasonDAO.search(sort);
    }

    /**
     * Get TaxFreeReason by primary key.
     * @return TaxFreeReason selected by id.
     */
    public TaxFreeReason getEntityById(int id) {
        return taxFreeReasonDAO.loadById(id);
    }

    public void insertEntity(TaxFreeReason taxFreeReason) {
        taxFreeReasonDAO.insert(taxFreeReason);
    }
}
