package com.autentia.tnt.manager.bill;

import com.autentia.tnt.businessobject.*;
import com.autentia.tnt.dao.hibernate.BillDAO;
import com.autentia.tnt.manager.billing.BillManager;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class BillManagerTest {
    private final BillManager billManager = new BillManager(Mockito.mock(BillDAO.class));

    @Test
    public void testConversionWithoutConceptsAndNoSaving() {
        final Offer offer = createOffer();

        final Bill bill = billManager.convertFromOfferToBill(offer);

        assertEquals(BillState.EMITTED, bill.getState());
        assertEquals(BillType.ISSUED, bill.getBillType());
        assertEquals(offer.getDescription(), bill.getName());
        assertEquals(0, bill.getTotal().intValue());
        assertEqualsContact(offer.getContact(), bill.getContact());
    }

    @Test
    public void testConversionWithOfferCosts() {
        final Offer offer = createOfferWithCosts();

        final Bill bill = billManager.convertFromOfferToBill(offer);

        assertEquals(5800, bill.getTotal().intValue());
    }

    @Test
    public void testConversionWithRolesCosts() {
        final Offer offer = createOfferWithRoles();

        final Bill bill = billManager.convertFromOfferToBill(offer);

        assertEquals(1218, bill.getTotal().intValue());
    }

    @Test
    public void testConversionWithOfferAndRolesCosts() {
        Offer offer = createOfferWithRolesAndCosts();

        final Bill bill = billManager.convertFromOfferToBill(offer);

        assertEquals(7018, bill.getTotal().intValue());
    }

    private void assertEqualsContact(Contact expected, Contact actual) {
        assertEquals(expected.getName(), actual.getName());
    }

    private Offer createOfferWithRolesAndCosts() {
        final Offer offer = createOffer();
        insertOfferCosts(offer);
        insertOfferRoles(offer);
        return offer;
    }

    private Offer createOfferWithRoles() {
        Offer offer = createOffer();
        insertOfferRoles(offer);
        return offer;
    }

    private void insertOfferRoles(Offer offer) {
        final Set<OfferRole> offerRoles = new LinkedHashSet<>(2);

        final OfferRole chapista = new OfferRole();
        chapista.setCostPerHour(new BigDecimal(30));
        chapista.setExpectedHours(10);
        chapista.setIva(new BigDecimal(16));
        chapista.setName("chapista");
        chapista.setOffer(offer);

        final OfferRole pintor = new OfferRole();
        pintor.setCostPerHour(new BigDecimal(25));
        pintor.setExpectedHours(30);
        pintor.setIva(new BigDecimal(16));
        pintor.setName("pintor");
        pintor.setOffer(offer);

        offerRoles.add(chapista);
        offerRoles.add(pintor);
        offer.setRoles(offerRoles);
    }


    private Offer createOfferWithCosts() {
        Offer offer = createOffer();
        insertOfferCosts(offer);
        return offer;
    }

    private void insertOfferCosts(Offer offer) {
        final Set<OfferCost> offerCosts = new LinkedHashSet<>(2);

        final OfferCost paneles = new OfferCost();
        paneles.setBillable(true);
        paneles.setCost(new BigDecimal(1000));
        paneles.setIva(new BigDecimal(16));
        paneles.setName("paneles");
        paneles.setUnits(new BigDecimal(5));
        paneles.setOffer(offer);

        final OfferCost cristales = new OfferCost();
        cristales.setBillable(false);
        cristales.setCost(new BigDecimal(10));
        cristales.setIva(new BigDecimal(16));
        cristales.setName("paneles");
        cristales.setUnits(new BigDecimal(1));
        cristales.setOffer(offer);

        offerCosts.add(paneles);
        offerCosts.add(cristales);
        offer.setCosts(offerCosts);
    }

    private Offer createOffer() {
        Offer offer = new Offer();
        offer.setNumber("1122");
        offer.setOrganization(new Organization());
        offer.setContact(new Contact());
        offer.setTitle("oferta genérica por prestación de servicios");
        offer.setDescription("descripción genérica");
        offer.setOfferPotential(OfferPotential.MEDIUM);
        offer.setOfferState(OfferState.OPEN);
        offer.setCreationDate(new Date());
        return offer;
    }
}
