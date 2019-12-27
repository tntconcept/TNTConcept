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

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.autentia.tnt.businessobject.Contact;
import com.autentia.tnt.businessobject.Department;
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
import com.autentia.tnt.businessobject.Role;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.businessobject.UserCategory;
import com.autentia.tnt.businessobject.WorkingAgreement;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.hibernate.ContactDAO;
import com.autentia.tnt.dao.hibernate.DepartmentDAO;
import com.autentia.tnt.dao.hibernate.InteractionTypeDAO;
import com.autentia.tnt.dao.hibernate.OfferDAO;
import com.autentia.tnt.dao.hibernate.OrganizationDAO;
import com.autentia.tnt.dao.hibernate.ProjectDAO;
import com.autentia.tnt.dao.hibernate.RoleDAO;
import com.autentia.tnt.dao.hibernate.UserCategoryDAO;
import com.autentia.tnt.dao.hibernate.UserDAO;
import com.autentia.tnt.dao.hibernate.WorkingAgreementDAO;
import com.autentia.tnt.dao.search.OfferSearch;
import com.autentia.tnt.manager.contacts.OfferManager;
import com.autentia.tnt.util.HibernateUtil;
import com.autentia.tnt.util.testing.SpringUtilsForTesting;

public class DuplicateOfferTest {

	private static SessionFactory sessionFactory;
	
	private static final String OFFER_NUMBER = "1122";
	private static final String OFFER_TITLE = "oferta genérica por prestación de servicios";
	private static final String OFFER_DESCRIPTION = "descripción genérica";
	
	private Offer offer = new Offer();
	private Contact contact = new Contact();
	private Project project = new Project();
	private Organization organization = new Organization();
	private User user = new User();
	
	@BeforeClass
	public static void init() throws Exception {
		SpringUtilsForTesting.configure(new ClassPathXmlApplicationContext("applicationContext-test.xml"));
		sessionFactory = HibernateUtil.getSessionFactory();
		sessionFactory.openSession();
	}

	@Before
	public void beginTransaction() throws Exception {
		sessionFactory.getCurrentSession().beginTransaction();
	}

	@After
	public void rollbackTransaction() throws Exception {
		if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		sessionFactory.getCurrentSession().close();
	}
	
	/**
	 * Comprobacion de que se duplique la oferta sin relaciones 1:N
	 */
	@Test
	public void testDuplicateOfferWithSimpleDataOnly() {
		final OfferManager offerManager = (OfferManager) SpringUtilsForTesting.getSpringBean("managerOffer");
		final OfferDAO offerDAO = (OfferDAO) SpringUtilsForTesting.getSpringBean("daoOffer");
		insertInitialData();
		
		final Offer duplicated = offerManager.duplicateOffer(offer);
		if (duplicated.getId() != null) {
			fail("el id debe ser 'null' en vez de '" + duplicated.getId() + "'");
		}	
		if (duplicated.getNumber() != null) {
			fail("el numero debe ser '" + offer.getNumber() + "' en vez de '" + duplicated.getNumber() + "'");
		}	
		if (!duplicated.getContact().equals(offer.getContact())) {
			fail("el contacto debe ser '" + offer.getContact().getName() + "' en vez de '" + duplicated.getContact().getName() + "'");
		}	
		if (!duplicated.getMaturityDate().equals(offer.getMaturityDate())) {
			fail("la fecha de madurez debe ser '" + offer.getMaturityDate() + "' en vez de '" + duplicated.getMaturityDate() + "'");
		}	

		duplicated.setNumber("123456");
		offerDAO.insert(duplicated);
		
		if (offerManager.getAllEntities(new OfferSearch(), new SortCriteria()).size() != 2) {
			fail("deberia haber 2 ofertas en vez de '" + offerManager.getAllEntities(new OfferSearch(), new SortCriteria()).size() + "'");
		}		
	}	
	
	/**
	 * Comprobacion de que se duplique la oferta con relaciones con costes, personal e interacciones
	 */
	@Test
	public void testDuplicateOfferWithRolesAndCosts() {
		final OfferManager offerManager = (OfferManager) SpringUtilsForTesting.getSpringBean("managerOffer");
		final OfferDAO offerDAO = (OfferDAO) SpringUtilsForTesting.getSpringBean("daoOffer");
		insertInitialData();
		insertOfferCosts();
		insertOfferRoles();
		insertOfferInteraction();
		
		final Offer duplicated = offerManager.duplicateOffer(offer);
		duplicated.setNumber("123456");
		offerDAO.insert(duplicated);
		
		if (duplicated.getCosts().size() != 2) {
			fail("deberia haber 2 costes por materiales en el duplicado en vez de '" + duplicated.getCosts().size() + "'");
		}		

		if (duplicated.getRoles().size() != 2) {
			fail("deberia haber 2 costes por roles en el duplicado en vez de '" + duplicated.getRoles().size() + "'");
		}		
		
		if (duplicated.getInteractions().size() != 1) {
			fail("deberia haber 1 interacciones en el duplicado en vez de '" + duplicated.getInteractions().size() + "'");
		}	
	}
	
    /**
     * Metodo encargado de insertar datos muy generales pero que son necesarios
     * para generar facturas en base a ofertas
     */
	private void insertInitialData() {
		
		final OfferDAO offerDAO = (OfferDAO) SpringUtilsForTesting.getSpringBean("daoOffer");
		final ProjectDAO projectDAO = (ProjectDAO) SpringUtilsForTesting.getSpringBean("daoProject");
		final OrganizationDAO organizationDAO = (OrganizationDAO) SpringUtilsForTesting.getSpringBean("daoOrganization");
		final ContactDAO contactDAO = (ContactDAO) SpringUtilsForTesting.getSpringBean("daoContact");
		final UserDAO userDAO = (UserDAO) SpringUtilsForTesting.getSpringBean("daoUser");
		final RoleDAO roleDAO = (RoleDAO) SpringUtilsForTesting.getSpringBean("daoRole");
		final UserCategoryDAO categoryDAO = (UserCategoryDAO) SpringUtilsForTesting.getSpringBean("daoUserCategory");
		final DepartmentDAO departmentDAO = (DepartmentDAO) SpringUtilsForTesting.getSpringBean("daoDepartment");
		final WorkingAgreementDAO workingAgreementDAO = (WorkingAgreementDAO) SpringUtilsForTesting.getSpringBean("daoWorkingAgreement");
		
		final Role role = new Role();
		roleDAO.insert(role);
		
		final Department department = new Department();
		department.setName("departamento");
		departmentDAO.insert(department);
		
		final UserCategory category = new UserCategory();
		categoryDAO.insert(category);
		
		final WorkingAgreement workingAgreement = new WorkingAgreement();
		workingAgreementDAO.insert(workingAgreement);
		
		contact.setName("Sergio Hermida");
		contactDAO.insert(contact);
		projectDAO.insert(project);
		organizationDAO.insert(organization);
		user.setRole(role);
		user.setCategory(category);
		user.setDepartment(department);
		user.setAgreement(workingAgreement);
		userDAO.insert(user);
		
		offer.setNumber(OFFER_NUMBER);
		offer.setOrganization(organization);
		offer.setContact(contact);
		offer.setTitle(OFFER_TITLE);
		offer.setDescription(OFFER_DESCRIPTION);
		offer.setOfferPotential(OfferPotential.MEDIUM);
		offer.setOfferState(OfferState.OPEN);
		offer.setCreationDate(new Date());
		offer.setMaturityDate(new Date());
		offerDAO.insert(offer);
	}	
	
	/**
	 * Inserta costes materiales en la ofertas
	 */
	private void insertOfferCosts() {
		final Set<OfferCost> offerCosts = new LinkedHashSet<OfferCost>(2);
		
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
	
	/**
	 * Inserta costes imputables a personal en la ofertas
	 */
	private void insertOfferRoles() {
		final Set<OfferRole> offerRoles = new LinkedHashSet<OfferRole>(2);
		
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
	
	/**
 	 * Inserta una interaccion en la oferta
	 */
	private void insertOfferInteraction() {
		final Set<Interaction> interactions = new LinkedHashSet<Interaction>(1);
		
		final InteractionTypeDAO interactionTypeDAO = (InteractionTypeDAO) SpringUtilsForTesting.getSpringBean("daoInteractionType");
		final InteractionType interactionType = new InteractionType();
		interactionType.setName("interaccion");
		interactionTypeDAO.insert(interactionType);		
		
		final Interaction interaction = new Interaction();		
		interaction.setProject(project);
		interaction.setUser(user);
		interaction.setType(interactionType);
		interaction.setOffer(offer);
		interaction.setCreationDate(new Date());
		interaction.setInterest(InteractionInterest.MEDIUM);
		interaction.setDescription("descripcion de la interaccion");
		
		interactions.add(interaction);
		offer.setInteractions(interactions);		
	}
}
