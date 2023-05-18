package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.ArchimedesSecuritySubject;
import com.autentia.tnt.dao.DataAccException;
import com.autentia.tnt.dao.SearchCriteria;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.util.HibernateUtil;
import com.autentia.tnt.util.SpringUtils;
import org.apache.commons.lang.NotImplementedException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Optional;

public class ArchimedesSecuritySubjectDAO extends HibernateManagerBase<ArchimedesSecuritySubject> {


    public static ArchimedesSecuritySubjectDAO getDefault() {
        return (ArchimedesSecuritySubjectDAO) SpringUtils.getSpringBean("daoArchimedes");
    }

    public ArchimedesSecuritySubjectDAO() {

    }


    public void insert(ArchimedesSecuritySubject archimedesSecuritySubject) {
        super.insert(archimedesSecuritySubject);
    }

    @Override
    public void update(ArchimedesSecuritySubject to) throws DataAccException {
        super.update(to, to.getId());
    }

    @Override
    public ArchimedesSecuritySubject getById(final int id) throws DataAccException {
        throw new NotImplementedException();
    }

    @Override
    public ArchimedesSecuritySubject loadById(final int id) throws DataAccException {
        throw new NotImplementedException();
    }

    @Override
    public List<ArchimedesSecuritySubject> search(final SortCriteria crit) throws DataAccException {
        throw new NotImplementedException();
    }

    @Override
    public List<ArchimedesSecuritySubject> search(final SearchCriteria search,
                                                  final SortCriteria sort) throws DataAccException {
        throw new NotImplementedException();
    }

    @Override
    public void delete(ArchimedesSecuritySubject to) throws DataAccException {
        throw new NotImplementedException();
    }


    public Optional<ArchimedesSecuritySubject> findByPrincipalName(final String principalName) {
        String columName = "principal_name";
        String tableName = "ArchimedesSecuritySubject";
        Session session = HibernateUtil.getSessionFactory().openSession();
        // where principal_name = principalName
        ArchimedesSecuritySubject archimedesSecuritySubject = (ArchimedesSecuritySubject) session.createQuery("from " + tableName + " where " + columName + " = :principalName")
                .setParameter("principalName", principalName)
                .uniqueResult();
        session.close();
        return Optional.ofNullable(archimedesSecuritySubject);
    }
}
