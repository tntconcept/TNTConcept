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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.autentia.tnt.test.utils;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.springframework.context.ApplicationContext;

import com.autentia.tnt.businessobject.Department;
import com.autentia.tnt.businessobject.Role;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.businessobject.UserCategory;
import com.autentia.tnt.businessobject.WorkingAgreement;
import com.autentia.tnt.dao.hibernate.DepartmentDAO;
import com.autentia.tnt.dao.hibernate.RoleDAO;
import com.autentia.tnt.dao.hibernate.UserCategoryDAO;
import com.autentia.tnt.dao.hibernate.UserDAO;
import com.autentia.tnt.dao.hibernate.WorkingAgreementDAO;
import com.autentia.tnt.manager.security.Principal;
import com.autentia.tnt.util.SpringUtils;

public class SpringUtilsForTesting {

    private static ApplicationContext appCtx;

	public synchronized static void configure( ApplicationContext ctx )
	{
		// Do not let configure more than once
		if( appCtx == null )
		{
			appCtx = ctx;
		}

		SpringUtils.configureTest( ctx);

	}

    public static Object getSpringBean( String name )
	{
		return appCtx.getBean(name);
	}

    public static User createUserInContextWithRoleAndDepartment() {

    	// all hibernate DAOs required a principal when inserting in the database so we create a temporary principal
    	setUserForTestingInContext();

    	final User user = new User();
        user.setLogin("admin");
        user.setRole(createRoleInContext());
        user.setDepartment(createDepartmentInContext());
        user.setCategory(createUserCategoryInContext());
        user.setAgreement(createAgreementInContext());
        final UserDAO userDao = (UserDAO) appCtx.getBean("daoUser");
        userDao.insert(user);
        user.setActive(true);

        // after inserting the temporary principal in the database now we can assign the database user to our principal
        setUserForTestingInContext(user);

        return user;
	}

	private static WorkingAgreement createAgreementInContext() {

		final WorkingAgreement agreement = new WorkingAgreement();
		final WorkingAgreementDAO workingAgreementDAO = (WorkingAgreementDAO) appCtx.getBean("daoWorkingAgreement");
		workingAgreementDAO.insert(agreement);
		return agreement;
	}

	private static UserCategory createUserCategoryInContext() {

		final UserCategory category = new UserCategory();
		final UserCategoryDAO userCategoryDAO = (UserCategoryDAO) appCtx.getBean("daoUserCategory");
		userCategoryDAO.insert(category);
		return category;
	}

	private static UserForTesting setUserForTestingInContext() {

		final GrantedAuthority[] authorities = new GrantedAuthority[] {
	            new GrantedAuthorityImpl("User"),
	            new GrantedAuthorityImpl("Administrator") };
		final UserForTesting user = new UserForTesting();
	    user.setLogin("admin");
	    user.setId(1);
	    final DepartmentForTesting departmentForTesting = new DepartmentForTesting();
	    departmentForTesting.setId(1);
	    user.setDepartment(departmentForTesting);
	    final RoleForTesting roleForTesting = new RoleForTesting();
	    roleForTesting.setId(1);
	    user.setRole(roleForTesting);
	    final Principal principal = new Principal(user, authorities);
	    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(principal,"admin"));
		return user;
	}

	private static void setUserForTestingInContext(User user) {
		final GrantedAuthority[] authorities = new GrantedAuthority[] {
	            new GrantedAuthorityImpl("User"),
	            new GrantedAuthorityImpl("Administrator") };
	    final Principal principal = new Principal(user, authorities);
	    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(principal, "admin"));
	}

	private static Department createDepartmentInContext() {

		final Department department = new Department();
		final DepartmentDAO departmentDao = (DepartmentDAO) appCtx.getBean("daoDepartment");
		departmentDao.insert(department);
		return department;
	}

	private static Role createRoleInContext() {

		final Role role = new Role();
	    final RoleDAO roleDao = (RoleDAO) appCtx.getBean("daoRole");
	    roleDao.insert(role);
	    return role;
	}

	public static void removeUserFromContext() {

		SecurityContextHolder.getContext().setAuthentication(null);
	}

	public static void deleteUserInContext(User userInContext) {

		final UserDAO userDao = (UserDAO) appCtx.getBean("daoUser");
		userDao.delete(userInContext);

		final UserCategoryDAO userCategoryDAO = (UserCategoryDAO) appCtx.getBean("daoUserCategory");
		userCategoryDAO.delete(userInContext.getCategory());

		final WorkingAgreementDAO workingAgreementDAO = (WorkingAgreementDAO) appCtx.getBean("daoWorkingAgreement");
		workingAgreementDAO.delete(userInContext.getAgreement());

		final RoleDAO roleDao = (RoleDAO) appCtx.getBean("daoRole");
		roleDao.delete(userInContext.getRole());

		final DepartmentDAO departmentDao = (DepartmentDAO) appCtx.getBean("daoDepartment");
		departmentDao.delete(userInContext.getDepartment());
	}
}
