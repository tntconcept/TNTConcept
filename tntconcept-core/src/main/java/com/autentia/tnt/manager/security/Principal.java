/**
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.
 * Copyright (C) 2007 Autentia Real Bussiness Solution S.L.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.autentia.tnt.manager.security;

import com.autentia.tnt.businessobject.User;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.userdetails.UserDetails;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class acts as a bridge between our User DTO and ACEGI representations of
 * a user.
 * 
 * @author Ivan Zaera Avellon
 */
public class Principal implements UserDetails {
	private static final Log				log				= LogFactory.getLog(Principal.class);

	private User							dto;
	private int								id;
	private int								departmentId;
	private String							username;
	private String							password;
	private boolean							enabled;
	private String							realName;
	private int								roleId;
	private GrantedAuthority[]				authorities;
	private Map<GrantedAuthority, Boolean>	hasAuthority	= new HashMap<GrantedAuthority, Boolean>();
	private Locale							locale			= Locale.getDefault();

	public Principal(User dto, GrantedAuthority[] authorities) {
		this(dto.getId(), dto.getDepartmentId(), dto.getLogin(), dto.getPassword(), dto.isActive(),
				dto.getName(), dto.getRole().getId(), authorities);
		this.dto = dto;
	}

	public Principal(int id, int departmentId, String username, String password, boolean enabled,
			String realName, int roleId, GrantedAuthority[] authorities) {
		
		if (log.isDebugEnabled()){
			log.debug("Principal - id=" + id + " username ='" + username + "' roleId=" + roleId + " enabled=" + enabled);
		}

		this.id = id;
		this.departmentId = departmentId;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.realName = realName;
		this.roleId = roleId;
		this.authorities = authorities;

		for (GrantedAuthority authority : authorities) {
			hasAuthority.put(authority, true);
		}
	}

	public boolean hasAuthority(GrantedAuthority testAuthority) {
		Boolean ret = hasAuthority.get(testAuthority);
		return ret == null ? false : ret;
	}

	public int getId() {
		return id;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public int getRoleId() {
		return roleId;
	}

	public GrantedAuthority[] getAuthorities() {
		return authorities;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getRealName() {
		return realName;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	/**
	 * <p>
	 * Get the underlying User object if it is loaded. Please note that this
	 * method can return null if the user has been read using JDBC instead of
	 * Hibernate. This normally happens when database needs to be migrated and
	 * the application is locked.
	 * </p>
	 * <p>
	 * You should only access underlying DTO when trying to get a user attribute
	 * that is not provided by getters of this class.
	 * </p>
	 * 
	 * @return the User DTO read from the database or null if Principal was
	 *         loaded using JDBC
	 */
	public User getUser() {
		return dto;
	}
}
