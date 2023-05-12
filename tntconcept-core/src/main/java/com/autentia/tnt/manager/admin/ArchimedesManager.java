package com.autentia.tnt.manager.admin;

import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.dao.DataAccException;
import com.autentia.tnt.util.HibernateUtil;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.Session;

public class ArchimedesManager{
    protected ArchimedesManager(){}

    public static ArchimedesManager getDefault(){return (ArchimedesManager) SpringUtils.getSpringBean("managerArchimedes");}
    public void insert(User user) throws DataAccException {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            String query = "INSERT INTO archimedes_security_subject (principal_name, attributes) " +
                    "VALUES ('" + user.getLogin() + "@autentia.com', '{\"sub\":\"" + user.getId() + "\"}')";
            session.connection().prepareStatement(query);
        } catch (Exception ex) {
            StringBuffer msg = new StringBuffer("Error insertando el objeto: ");
            msg.append(user);
            throw new DataAccException(msg.toString(), ex);
        }
    }

}
