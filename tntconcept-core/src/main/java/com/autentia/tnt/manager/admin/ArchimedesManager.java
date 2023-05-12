package com.autentia.tnt.manager.admin;

import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.dao.DataAccException;
import com.autentia.tnt.util.HibernateUtil;
import com.autentia.tnt.util.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

public class ArchimedesManager {

    private static final Log log = LogFactory.getLog(ArchimedesManager.class);

    protected ArchimedesManager() {
    }

    public static ArchimedesManager getDefault() {
        return (ArchimedesManager) SpringUtils.getSpringBean("managerArchimedes");
    }

    public void insertSubjectForUser(final User user) throws DataAccException {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            final var principalName = user.getLogin() + "@autentia.com";

            String query = "INSERT INTO archimedes_security_subject (principal_name, attributes) " +
                    "VALUES ('" + principalName + "', '{\"sub\":\"" + user.getId() + "\"}')";
           session.connection().prepareStatement(query).execute();
        } catch (Exception ex) {
            StringBuffer msg = new StringBuffer("Error insertando el objeto: ");
            msg.append(user);
            throw new DataAccException(msg.toString(), ex);
        }
    }

    public void removeSubjectForUser(final User user) {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            final var principalName = user.getLogin() + "@autentia.com";
            String query = "DELETE FROM archimedes_security_subject WHERE principal_name = '" + principalName + "';";
            session.connection().prepareStatement(query).execute();
        } catch (Exception ex) {
            StringBuffer msg = new StringBuffer("Error insertando el objeto: ");
            msg.append(user);
            throw new DataAccException(msg.toString(), ex);
        }
    }
}
