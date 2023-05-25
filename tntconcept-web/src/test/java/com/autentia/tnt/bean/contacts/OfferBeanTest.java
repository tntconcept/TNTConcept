package com.autentia.tnt.bean.contacts;

import com.autentia.tnt.bean.billing.BillBean;
import com.autentia.tnt.businessobject.BillBreakDown;
import com.autentia.tnt.businessobject.OfferCost;
import com.autentia.tnt.businessobject.OfferRole;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.manager.billing.BillManager;
import com.autentia.tnt.manager.contacts.OfferManager;
import com.autentia.tnt.manager.security.AuthenticationManager;
import com.autentia.tnt.manager.security.Principal;
import com.autentia.tnt.util.ConfigurationUtil;
import com.autentia.tnt.util.SpringUtils;
import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OfferBeanTest {

    final static ConfigurationUtil configurationUtil = mock(ConfigurationUtil.class);
    final static ApplicationContext ctx = mock(ApplicationContext.class);

    final static OfferManager offerManager = mock(OfferManager.class);
    final static BillManager billManager = mock(BillManager.class);
    final static AuthenticationManager authManager = mock(AuthenticationManager.class);
    final static User user = mock(User.class);
    final static Principal principal = mock(Principal.class);

    private final BigDecimal IVA16 = new BigDecimal("16");

    private final BigDecimal IVA18 = new BigDecimal("18");

    private final BigDecimal IVA21 = new BigDecimal("21");

    @BeforeClass
    public static void init() {
        SecurityContext securityCtx = mock(SecurityContext.class);
        Authentication authentication = mock(Authentication.class);
        when(securityCtx.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(principal);

        SecurityContextHolder.setContext(securityCtx);
        SpringUtils.configureTest(ctx);
        when(ctx.getBean("configuration")).thenReturn(configurationUtil);
        when(ctx.getBean("userDetailsService")).thenReturn(authManager);
        when(ctx.getBean("managerOffer")).thenReturn(offerManager);
        when(ctx.getBean("managerBill")).thenReturn(billManager);

        when(authManager.getCurrentPrincipal()).thenReturn(principal);
        when(principal.getUser()).thenReturn(user);
        when(configurationUtil.getIvaUntilSeptember2012()).thenReturn(18.00f);
        when(configurationUtil.getActualIva()).thenReturn(21.00f);
        when(configurationUtil.getIvaUntilJuly2010()).thenReturn(16.00f);
    }

    @Test
    public void createRolesIn2009Test() {


        final OfferBean offerBean = new OfferBean();
        offerBean.create();
        final GregorianCalendar calendar = new GregorianCalendar(2009, 8, 31);
        offerBean.setCreationDate(calendar.getTime());
        offerBean.createRoles();
        for (OfferRole offerRole : offerBean.getRoles()) {
            assertEquals(IVA16, offerRole.getIva());
        }
    }

    @Test
    public void createRolesInFirstDayOf18Test() {

        OfferBean offerBean = new OfferBean();
        offerBean.create();
        final GregorianCalendar calendar = new GregorianCalendar(2010, 6, 1);
        offerBean.setCreationDate(calendar.getTime());
        offerBean.createRoles();
        for (OfferRole offerRole : offerBean.getRoles()) {
            assertEquals(IVA18, offerRole.getIva());
        }
    }

    @Test
    public void createRolesInLastDayOf18Test() {

        OfferBean offerBean = new OfferBean();
        offerBean.create();
        final GregorianCalendar calendar = new GregorianCalendar(2012, 7, 31);
        offerBean.setCreationDate(calendar.getTime());
        offerBean.createRoles();
        for (OfferRole offerRole : offerBean.getRoles()) {
            assertEquals(IVA18, offerRole.getIva());
        }
    }

    @Test
    public void createRolesInFirstDayOf21Test() {

        BillBean billBean = new BillBean();
        billBean.create();
        final GregorianCalendar calendar = new GregorianCalendar(2012, 8, 1);
        billBean.setCreationDate(calendar.getTime());
        billBean.createBreakDown();
        for (BillBreakDown billBreakDown : billBean.getBill().getBreakDown()) {
            assertEquals(IVA21, billBreakDown.getIva());
        }
    }

    @Test
    public void createCostsIn2009Test() {

        final OfferBean offerBean = new OfferBean();
        offerBean.create();
        final GregorianCalendar calendar = new GregorianCalendar(2009, 8, 31);
        offerBean.setCreationDate(calendar.getTime());
        offerBean.createCosts();
        for (OfferCost offerCost : offerBean.getCosts()) {
            assertEquals(IVA16, offerCost.getIva());
        }
    }

    @Test
    public void createCostsInFirstDayOf18Test() {

        OfferBean offerBean = new OfferBean();
        offerBean.create();
        final GregorianCalendar calendar = new GregorianCalendar(2010, 6, 1);
        offerBean.setCreationDate(calendar.getTime());
        offerBean.createCosts();
        for (OfferCost offerCost : offerBean.getCosts()) {
            assertEquals(IVA18, offerCost.getIva());
        }
    }

    @Test
    public void createCostsInLastDayOf18Test() {

        OfferBean offerBean = new OfferBean();
        offerBean.create();
        final GregorianCalendar calendar = new GregorianCalendar(2012, 7, 31);
        offerBean.setCreationDate(calendar.getTime());
        offerBean.createCosts();
        for (OfferCost offerCost : offerBean.getCosts()) {
            assertEquals(IVA18, offerCost.getIva());
        }
    }

    @Test
    public void createCostsInFirstDayOf21Test() {

        OfferBean offerBean = new OfferBean();
        offerBean.create();
        final GregorianCalendar calendar = new GregorianCalendar(2012, 8, 1);
        offerBean.setCreationDate(calendar.getTime());
        offerBean.createCosts();
        for (OfferCost offerCost : offerBean.getCosts()) {
            assertEquals(IVA21, offerCost.getIva());
        }
    }
}