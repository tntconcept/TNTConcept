/**
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L. Copyright (C) 2007 Autentia Real Bussiness
 * Solution S.L. This program is free software: you can redistribute it and/or modify it under the terms of the GNU General
 * Public License as published by the Free Software Foundation, either version 3 of the License. This program is distributed
 * in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details. You should have received a copy of
 * the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.autentia.tnt.manager.security;

import static org.acegisecurity.context.SecurityContextHolder.getContext;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;

import com.autentia.tnt.businessobject.Setting;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.dao.DataAccException;
import com.autentia.tnt.dao.DataIntegrityException;
import com.autentia.tnt.dao.DataNotFoundException;
import com.autentia.tnt.dao.hibernate.SettingDAO;
import com.autentia.tnt.dao.hibernate.UserDAO;
import com.autentia.tnt.dao.search.SettingSearch;
import com.autentia.tnt.manager.security.exception.SecException;
import com.autentia.tnt.util.ConfigurationUtil;
import com.autentia.tnt.util.HibernateUtil;
import com.autentia.tnt.util.SettingPath;
import com.autentia.tnt.util.SpringUtils;
import com.autentia.tnt.version.Version;

/**
 * @author ivan
 */
public abstract class AuthenticationManager implements UserDetailsService {

    protected static final Log log = LogFactory.getLog(AuthenticationManager.class);

    /** Algorithm used to store passwords in database */
    public static final String HASH_ALGORITHM = "SHA-1";

    /** Administrator role id */
    private int administratorRoleId;

    /** User DAO */
    private UserDAO userDAO = new UserDAO();

    /** Settings manager */
    private final SettingDAO settings = new SettingDAO();

    /** IUserRolesService implementation */
    private IUserRolesService userRolesService;

    /**
     * @deprecated the preferred way to get the default manager is using Spring injection
     */
    public static AuthenticationManager getDefault() {
        return (AuthenticationManager)SpringUtils.getSpringBean("userDetailsService");

    }

    /**
     * Constructor
     * 
     * @param cfg configuration object
     * @param userRolesService delegate for getting user's roles
     */
    public AuthenticationManager(ConfigurationUtil cfg, IUserRolesService userRolesService) {
        this.userRolesService = userRolesService;
        this.administratorRoleId = cfg.getRoleAdminId();
    }

    /**
     * Get currently logged-in principal
     * 
     * @return the active Principal
     */
    public Principal getCurrentPrincipal() {
        Object ret = getContext().getAuthentication().getPrincipal();
        return (ret instanceof Principal) ? (Principal)ret : null;
    }

    public abstract boolean checkPassword(User user, String passwd);

    public abstract void changePassword(User user, String password);

    /**
     * Load a User for ACEGI given its user name
     * 
     * @param username user name
     * @throws org.acegisecurity.userdetails.UsernameNotFoundException
     * @throws org.springframework.dao.DataAccessException
     * @return the user object description as specified by ACEGI
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        try {
            Version db = Version.getDatabaseVersion();
            Version code = Version.getApplicationVersion();

            if (db.compareTo(code, Version.MINOR) == 0) {
                log.info("loadUserByUsername - getting user " + username + " using Hibernate");
                User user = userDAO.searchByLogin(username);

                GrantedAuthority[] auths = userRolesService.getAuthorities(user);

                if (log.isDebugEnabled()) {
                    StringBuilder sb = new StringBuilder();
                    for (GrantedAuthority auth : auths) {
                        sb.append(auth);
                        sb.append(" ");
                    }
                    log.debug("loadUserByUsername - user roles: " + sb);
                }

                final Principal principal = new Principal(user, auths);

                // setting user preferred Locale
                final SettingSearch s = new SettingSearch();
                s.setName(SettingPath.GENERAL_PREFERRED_LOCALE);
                s.setOwnerId(user.getId());
                final List<Setting> vals = settings.search(s, null);

                final Setting val = (vals != null && vals.size() > 0) ? vals.get(0) : null;

                if (val != null) {
                    final Locale local = new Locale(val.getValue());
                    principal.setLocale(local);
                }

                return principal;
            } else {
                log.info("loadUserByUsername - getting user " + username + " using JDBC");
                return jdbcSearchByLogin(username);
            }
        } catch (SecException e) {
            log.warn("loadUserByUsername - exception", e);
            throw new DataRetrievalFailureException("Error getting roles for user: " + username, e);
        } catch (DataIntegrityException e) {
            log.warn("loadUserByUsername - exception", e);
            throw new DataIntegrityViolationException("Inconsistent user name: " + username, e);
        } catch (DataNotFoundException e) {
            log.warn("loadUserByUsername - exception", e);
            throw new UsernameNotFoundException("User not found: " + username, e);
        } catch (DataAccException e) {
            log.warn("loadUserByUsername - exception", e);
            throw new DataRetrievalFailureException("Error getting user: " + username, e);
        } catch (SQLException e) {
            log.warn("loadUserByUsername - exception", e);
            throw new DataRetrievalFailureException("Error getting user: " + username, e);
        }
    }

    /**
     * Reset user password.
     *
     * @param user the user
     * @return the new password
     */
    public abstract String resetPassword(User user, String[] rnd0, String[] rnd1, String[] rnd2, String[] rnd3,
            String[] rnd4);
    
    /**
     * Reset user password from an external context.
     *
     * @param user the user
     * @return the new password
     */
    public abstract String resetPasswordExternal(User user, String[] rnd0, String[] rnd1, String[] rnd2, String[] rnd3,
            String[] rnd4);


    /**
     * Generate a new random password
     * 
     * @return a new random password
     */
    protected String generateRandomPassword(String[] rnd0, String[] rnd1, String[] rnd2, String[] rnd3, String[] rnd4) {
        StringBuilder ret = new StringBuilder();

        // Get lists of random words. We could cache these, but this method is
        // rarely called and caching would
        // depend on user locale, so we prefer to waste CPU better than memory.

        // Get a true random number generator
        SecureRandom rnd;
        try {
            rnd = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException ex) {
            rnd = new SecureRandom();
        }

        // Generate random numbers
        int i0 = rnd.nextInt(rnd0.length);
        int i1 = rnd.nextInt(rnd1.length);
        int i2 = rnd.nextInt(rnd2.length);
        int i3 = rnd.nextInt(rnd3.length);
        int i4 = rnd.nextInt(rnd4.length);

        // Compose password
        ret.append(rnd0[i0]);
        ret.append(rnd1[i1]);
        ret.append(rnd2[i2]);
        ret.append(rnd3[i3]);
        ret.append(rnd4[i4]);

        return ret.toString();
    }

    /**
     * <p>
     * Get user details given her login. This method accesses tables using JDBC. Thus, is should only be used if we need to
     * log into the single-user console to migrate tables (in that case, we cannot rely on Hibernate to get the users until
     * we have really finished migrating tables and data).
     * </p>
     * 
     * @return a MiniUser object (minimal representation of a user)
     * @param login user login
     * @throws com.autentia.tnt.dao.DataNotFoundException if user does not exist
     * @throws com.autentia.tnt.dao.DataIntegrityException if user is duplicated in DB
     * @throws com.autentia.tnt.dao.DataAccException if something fails accessing DB
     */
    private Principal jdbcSearchByLogin(String login)
            throws DataAccException, DataNotFoundException, DataIntegrityException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Connection con = ses.connection();
        Principal ret;

        try {
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                stmt = con.prepareStatement("select u.id,u.login,u.password,u.active,u.name,r.id roleId "
                        + "from User u,Role r " + "where u.roleId=r.id " + "and u.login=?");
                stmt.setString(1, login);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    int roleId = rs.getInt("roleId");

                    ret = new Principal(rs.getInt("id"), 0, // In console mode we won't need departmentId so
                                                            // we leave it blank. We can't get
                                                            // it from the database because in versions prior to
                                                            // 0.4 the field didn't exist
                            rs.getString("login"), rs.getString("password"), rs.getBoolean("active"),
                            rs.getString("name"), roleId, (roleId == administratorRoleId)
                                    ? new GrantedAuthority[] { Permission.Action_Console } : new GrantedAuthority[] {});

                    if (rs.next()) {
                        throw new DataIntegrityException(User.class, "Duplicated user login: " + login);
                    }
                } else {
                    throw new DataNotFoundException(User.class, login);
                }
            } catch (SQLException e) {
                throw new DataAccException("Error loading user: " + login, e);
            } finally {
                if (rs != null) try {
                    rs.close();
                } catch (SQLException e) {
                    log.error("jdbcSearchByLogin - Error al liberar el resultset", e);
                }
                ;
                if (stmt != null) try {
                    stmt.close();
                } catch (SQLException e) {
                    log.error("jdbcSearchByLogin - Error al liberar el statement", e);
                }
                ;
            }
        } finally {
            ses.close();
        }

        return ret;
    }
    
}
