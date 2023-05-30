/**
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.
 * Copyright (C) 2007 Autentia Real Bussiness Solution S.L.
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.autentia.tnt.test.utils;

import com.autentia.tnt.businessobject.*;
import com.autentia.tnt.dao.hibernate.DepartmentDAO;
import com.autentia.tnt.dao.hibernate.RoleDAO;
import com.autentia.tnt.dao.hibernate.UserCategoryDAO;
import com.autentia.tnt.manager.security.AuthenticationManager;
import com.autentia.tnt.manager.security.Principal;
import com.autentia.tnt.util.SpringUtils;
import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

public class SpringUtilsForTesting {

    private static ApplicationContext appCtx;

    public synchronized static void configure(ApplicationContext ctx) {
        // Do not let configure more than once
        if (appCtx == null) {
            appCtx = ctx;
        }

        SpringUtils.configureTest(ctx);

    }

    public static Object getSpringBean(String name) {
        return appCtx.getBean(name);
    }

    public static void loadPrincipalInSecurityContext(String username) {
        AuthenticationManager authManager = (AuthenticationManager) getSpringBean("userDetailsService");
        Principal principal = (Principal) authManager.loadUserByUsername(username);
        Authentication auth = new UsernamePasswordAuthenticationToken(principal, principal.getUser().getPassword(),
                principal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    public static User createUser(String login) {
        final User user = new User();
        user.setLogin(login);
        user.setPassword(login);
        user.setName(login);
        user.setRole(createRoleInContext());
        user.setDepartment(createDepartmentInContext());
        user.setCategory(createUserCategoryInContext());
        user.setAgreement(createAgreementInContext());
        user.setActive(true);
        user.setStartDate(new java.util.Date());
        user.setNif("");
        user.setAcademicQualification("");
        user.setPhone("");
        user.setMobile("");
        user.setStreet("");
        user.setCity("");
        user.setPostalCode("");
        user.setMarried(true);
        user.setChildrenNumber(0);
        user.setDrivenLicenseType("");
        user.setLicensePlate("");
        user.setSocialSecurityNumber("");
        user.setBank("");
        user.setAccount("");
        user.setTravelAvailability("");
        user.setWorkingInClient(true);
        user.setEmail("");
        user.setGenre(UserGenre.MAN);
        user.setSalary(BigDecimal.ZERO);
        user.setSalaryExtras(BigDecimal.ZERO);
        user.setSecurityCard("");
        user.setHealthInsurance("");
        user.setNotes("");
        user.setPhoto("");
        user.setContractObservations("");
        user.setDayDuration(8);
        user.setVehicleType("");
        return user;
    }

    private static WorkingAgreement createAgreementInContext() {

        final WorkingAgreement agreement = new WorkingAgreement();
        agreement.setName("Test Agreement");
        agreement.setDescription("");
        agreement.setId(1);
        return agreement;
    }

    private static UserCategory createUserCategoryInContext() {

        final UserCategory category = new UserCategory();
        category.setName("Test Category");
        final UserCategoryDAO userCategoryDAO = (UserCategoryDAO) appCtx.getBean("daoUserCategory");
        userCategoryDAO.insert(category);
        return category;
    }

    private static Department createDepartmentInContext() {

        final Department department = new Department();
        department.setName("Test Department");
        department.setDescription("Test Department");
        final DepartmentDAO departmentDao = (DepartmentDAO) appCtx.getBean("daoDepartment");
        departmentDao.insert(department);
        return department;
    }

    private static Role createRoleInContext() {

        final Role role = new Role();
        role.setName("Test Role");
        final RoleDAO roleDao = (RoleDAO) appCtx.getBean("daoRole");
        roleDao.insert(role);
        return role;
    }

}
