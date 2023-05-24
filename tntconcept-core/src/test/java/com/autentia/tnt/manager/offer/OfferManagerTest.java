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

package com.autentia.tnt.manager.offer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import com.autentia.tnt.dao.hibernate.OfferDAO;
import org.junit.Test;

import com.autentia.tnt.businessobject.Contact;
import com.autentia.tnt.businessobject.Interaction;
import com.autentia.tnt.businessobject.InteractionInterest;
import com.autentia.tnt.businessobject.InteractionType;
import com.autentia.tnt.businessobject.Offer;
import com.autentia.tnt.businessobject.OfferCost;
import com.autentia.tnt.businessobject.OfferPotential;
import com.autentia.tnt.businessobject.OfferRole;
import com.autentia.tnt.businessobject.OfferState;
import com.autentia.tnt.businessobject.Organization;
import com.autentia.tnt.businessobject.Project;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.manager.contacts.OfferManager;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class OfferManagerTest {
    private static final String OFFER_NUMBER = "1122";
    private static final String OFFER_TITLE = "oferta genérica por prestación de servicios";
    private static final String OFFER_DESCRIPTION = "descripción genérica";
    private final OfferManager offerManager = new OfferManager(Mockito.mock(OfferDAO.class));
    @Test
    public void duplicateOfferShouldNotCloneUniqueFields() {
        final Offer offer = createOffer();

        final Offer duplicatedOffer = offerManager.duplicateOffer(offer);

        assertNull(duplicatedOffer.getId());
        assertNull(duplicatedOffer.getNumber());
    }

    @Test
    public void duplicateOfferShouldCloneFieldsAndCollections() {
        final Offer offer =  createOffer();

        final Offer duplicatedOffer = offerManager.duplicateOffer(offer);

        assertEquals(offer.getMaturityDate(), duplicatedOffer.getMaturityDate());
        // Equals compares IDs, we do not have ID cause item is not stored in database
        assertEqualsContact(offer.getContact(), duplicatedOffer.getContact());
        assertEqualsCosts(offer.getCosts(), duplicatedOffer.getCosts());
        assertEqualsRoles(offer.getRoles(), duplicatedOffer.getRoles());
        assertEqualsInteractions(offer.getInteractions(), duplicatedOffer.getInteractions());
    }

    private void assertEqualsInteractions(Set<Interaction> expected, Set<Interaction> actual) {
        assertEquals(expected.size(), actual.size());
        for(Interaction interaction : expected) {
            boolean existsInActual = actual.stream().anyMatch(actualInteraction -> actualInteraction.getDescription().equals(interaction.getDescription()));
            assertTrue(existsInActual);
        }
    }

    private void assertEqualsRoles(Set<OfferRole> expected, Set<OfferRole> actual) {
        assertEquals(expected.size(), actual.size());
        for(OfferRole offerRole : expected) {
            boolean existsInActual = actual.stream().anyMatch(actualOfferRole -> actualOfferRole.getName().equals(offerRole.getName()));
            assertTrue(existsInActual);
        }
    }

    private void assertEqualsCosts(Set<OfferCost> expected, Set<OfferCost> actual) {
        assertEquals(expected.size(), actual.size());
        for(OfferCost cost : expected) {
            boolean existsInActual = actual.stream().anyMatch(actualCost -> actualCost.getCost().equals(cost.getCost()));
            assertTrue(existsInActual);
        }
    }

    private void assertEqualsContact(Contact expected, Contact actual) {
        assertEquals(expected.getName(), actual.getName());
    }

    private Offer createOffer() {
        final Contact contact = new Contact();
        contact.setName("Sergio Hermida");

        final Offer offer = new Offer();
        offer.setId(1);
        offer.setNumber(OFFER_NUMBER);
        offer.setOrganization(new Organization());
        offer.setContact(contact);
        offer.setTitle(OFFER_TITLE);
        offer.setDescription(OFFER_DESCRIPTION);
        offer.setOfferPotential(OfferPotential.MEDIUM);
        offer.setOfferState(OfferState.OPEN);
        offer.setCreationDate(new Date());
        offer.setMaturityDate(new Date());
        insertOfferCosts(offer);
        insertOfferRoles(offer);
        insertOfferInteraction(offer);
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

    private void insertOfferInteraction(Offer offer) {
        final Set<Interaction> interactions = new LinkedHashSet<>(1);

        final InteractionType interactionType = new InteractionType();
        interactionType.setName("interaccion");

        final Interaction interaction = new Interaction();
        interaction.setProject(new Project());
        interaction.setUser(new User());
        interaction.setType(interactionType);
        interaction.setOffer(offer);
        interaction.setCreationDate(new Date());
        interaction.setInterest(InteractionInterest.MEDIUM);
        interaction.setDescription("descripcion de la interaccion");

        interactions.add(interaction);
        offer.setInteractions(interactions);
    }
}
