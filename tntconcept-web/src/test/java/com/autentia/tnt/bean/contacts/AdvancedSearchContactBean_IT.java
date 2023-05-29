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

package com.autentia.tnt.bean.contacts;

import com.autentia.tnt.businessobject.*;
import com.autentia.tnt.dao.hibernate.*;
import com.autentia.tnt.manager.contacts.advancedsearch.ContactPosition;
import com.autentia.tnt.test.utils.SpringUtilsForTesting;
import com.autentia.tnt.test.utils.TestContainer;
import com.autentia.tnt.tracking.EntityChange;
import com.autentia.tnt.tracking.hibernate.dao.EntityChangeDAO;
import com.autentia.tnt.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AdvancedSearchContactBean_IT extends TestContainer {
    private static SessionFactory sessionFactory;
    // informacion de los contactos
    private final Position becario = new Position();
    private final Position responsableDireccion = new Position();
    private final Position responsableFacturacion = new Position();
    private final Position analista = new Position();
    private final Contact antonioRuiz = new Contact();
    private final Contact pilarSancho = new Contact();
    private final OrganizationType organizationType = new OrganizationType();
    private final OrganizationISOCategory organizationISOCategory = new OrganizationISOCategory();
    private final Organization organization = new Organization();
    private final Department department = new Department();
    // datos basicos
    private Province province = new Province();

    @BeforeClass
    public static void init() {
        SpringUtilsForTesting.configure(new ClassPathXmlApplicationContext("applicationContext-test.xml"));
        SpringUtilsForTesting.loadPrincipalInSecurityContext("admin");
        sessionFactory = HibernateUtil.getSessionFactory();
        sessionFactory.openSession();
    }

    @Before
    public void beginTransaction() {
        sessionFactory.getCurrentSession().beginTransaction();
        this.insertInitialData();
        this.insertContactData();
    }

    @After
    public void rollbackTransaction() {
        if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
        }
        sessionFactory.getCurrentSession().close();
    }

    @Test
    public void testDoAdvancedSearchWithoutCriteria() {
        final AdvancedSearchContactBean advancedSearch = new AdvancedSearchContactBean();
        advancedSearch.search();
        final List<ContactPosition> contactPositions = advancedSearch.getContactPositions();

        assertEquals(4, contactPositions.size());
    }

    @Test
    public void testDoAdvancedSearchBusyPosition() {
        final List<Position> positions = new ArrayList<>();
        positions.add(becario);

        final AdvancedSearchContactBean advancedSearchContactBean = new AdvancedSearchContactBean();
        advancedSearchContactBean.setSearchPositions(positions);
        advancedSearchContactBean.search();
        final List<ContactPosition> contactPositions = advancedSearchContactBean.getContactPositions();

        assertEquals(1, contactPositions.size());
        assertEquals("Antonio Ruiz", contactPositions.get(0).getName());
    }

    @Test
    public void testDoAdvancedSearchEmptyPosition() {
        final AdvancedSearchContactBean advancedSearch = new AdvancedSearchContactBean();
        final List<Position> positions = new ArrayList<>();
        positions.add(responsableDireccion);
        advancedSearch.setSearchPositions(positions);
        advancedSearch.search();
        final List<ContactPosition> contactPositions = advancedSearch.getContactPositions();

        assertEquals(1, contactPositions.size());
        assertEquals("Responsable de Dirección", contactPositions.get(0).getPosition());
    }

    @Test
    public void testDoAdvancedSearchHistorical() {
        // añadimos que Pilar Sancho fue becaria
        addChangePosition(Contact.FIELD_POSITION, "", "becario");

        final AdvancedSearchContactBean advancedSearchContactBean = new AdvancedSearchContactBean();
        final List<Position> positions = new ArrayList<>();
        positions.add(becario);
        advancedSearchContactBean.setSearchPositions(positions);
        advancedSearchContactBean.searchInChanges();
        final List<ContactPosition> contactPositions = advancedSearchContactBean.getContactPositions();

        // debe salir dos resultados, ya que hay uno que es becario actualmente y otro que lo fue
        assertEquals(2, contactPositions.size());
    }

    private void addChangePosition(String fieldPosition, String newValue, String becario) {
        final EntityChangeDAO entityChangeDAO = (EntityChangeDAO) SpringUtilsForTesting.getSpringBean("daoEntityChange");
        final EntityChange changePosition = new EntityChange();
        changePosition.setField(fieldPosition);
        changePosition.setNewValue(newValue);
        changePosition.setOldValue(becario);
        changePosition.setEntityId(pilarSancho.getId());
        changePosition.setEntityName("com.autentia.tnt.businessobject.Contact");
        entityChangeDAO.insert(changePosition);
    }

    @Test
    public void testDoAdvancedSearchHistoricalSimpleValue() {
        // añadimos que Pilar Sancho trabajaba en Italia
        addChangePosition(Contact.FIELD_COUNTRY, "España", "Italia");

        final AdvancedSearchContactBean advancedSearchContactBean = new AdvancedSearchContactBean();
        advancedSearchContactBean.setSearchCountry("Italia");
        advancedSearchContactBean.searchInChanges();
        final List<ContactPosition> contactPositions = advancedSearchContactBean.getContactPositions();

        assertEquals(1, contactPositions.size());
    }

    /**
     * Metodo encargado de insertar datos muy generales pero que son necesarios
     * en la gestion de contactos
     */
    private void insertInitialData() {
        final OrganizationDocCategoryDAO organizationDocCategoryDAO = (OrganizationDocCategoryDAO) SpringUtilsForTesting.getSpringBean("daoOrganizationDocCategory");
        final ProvinceDAO provinceDAO = (ProvinceDAO) SpringUtilsForTesting.getSpringBean("daoProvince");
        final CountryDAO countryDao = (CountryDAO) SpringUtilsForTesting.getSpringBean("daoCountry");
        final OrganizationISOCategoryDAO organizationISOCategoryDAO = (OrganizationISOCategoryDAO) SpringUtilsForTesting.getSpringBean("daoOrganizationISOCategory");
        final OrganizationTypeDAO organizationTypeDAO = (OrganizationTypeDAO) SpringUtilsForTesting.getSpringBean("daoOrganizationType");
        final OrganizationDAO organizationDAO = (OrganizationDAO) SpringUtilsForTesting.getSpringBean("daoOrganization");
        final DepartmentDAO departmentDAO = (DepartmentDAO) SpringUtilsForTesting.getSpringBean("daoDepartment");

        // provincia
        OrganizationDocCategory organizationDocCategory = organizationDocCategoryDAO.getById(1);
        province = provinceDAO.getById(1);
        Country country = countryDao.getById(1);

        // categoria de la organizacion
        organizationType.setName("prospecto");
        organizationType.setDescription("Organization type description");
        organizationTypeDAO.insert(organizationType);

        // categoria ISO de la organizacion
        organizationISOCategory.setName("A");
        organizationISOCategory.setDescription("A");
        organizationISOCategoryDAO.insert(organizationISOCategory);

        // organizacion
        organization.setName("Tecnologías de España");
        organization.setProvince(province);
        organization.setType(organizationType);
        organization.setCategory(organizationISOCategory);
        organization.setDocumentNumber("1234");
        organization.setStreet("Calle");
        organization.setNumber("a");
        organization.setLocator("locator");
        organization.setPostalCode("41113");
        organization.setCity("city");
        organization.setState("state");
        organization.setPhone("1234567");
        organization.setEmail("test@test.com");
        organization.setCountry(country);
        organization.setFax("test");
        organization.setFtpsite("ftpsite");
        organization.setWebsite("http://google.es");
        organization.setNotes("notes");
        organization.setFreelance(false);
        organization.setOrganizationDocCategory(organizationDocCategory);
        organizationDAO.insert(organization);

        // departamento
        department.setName("comercial");
        department.setDescription("comercial");
        departmentDAO.insert(department);
    }

    /**
     * Metodo encargado de insertar los datos de los propios contactos
     */
    private void insertContactData() {
        final PositionDAO positionDAO = (PositionDAO) SpringUtilsForTesting.getSpringBean("daoPosition");
        final ContactDAO contactDAO = (ContactDAO) SpringUtilsForTesting.getSpringBean("daoContact");

        // puestos
        becario.setName("becario");
        becario.setDescription("becario");
        becario.setEmail("becario@dominio.com");
        becario.setPhone("+344343");
        becario.setFax("+341212");
        becario.setCountry("España");
        becario.setProvince(province);
        becario.setCity("barbate");
        becario.setPostalCode("10111");
        becario.setAddress("C/ Becario");
        positionDAO.insert(becario);

        responsableDireccion.setName("Responsable de Dirección");
        responsableDireccion.setDescription("Responsable de Dirección");
        responsableDireccion.setEmail("responsableDireccion@dominio.com");
        responsableDireccion.setPhone("+344343");
        responsableDireccion.setFax("+341212");
        responsableDireccion.setCountry("España");
        responsableDireccion.setProvince(province);
        responsableDireccion.setCity("barbate");
        responsableDireccion.setPostalCode("10111");
        responsableDireccion.setAddress("C/ Becario");
        positionDAO.insert(responsableDireccion);

        responsableFacturacion.setName("Responsable de Facturación");
        responsableFacturacion.setDescription("Responsable de Facturación");
        responsableFacturacion.setEmail("responsableFacturacion@dominio.com");
        responsableFacturacion.setPhone("+344343");
        responsableFacturacion.setFax("+341212");
        responsableFacturacion.setCountry("España");
        responsableFacturacion.setProvince(province);
        responsableFacturacion.setCity("barbate");
        responsableFacturacion.setPostalCode("10111");
        responsableFacturacion.setAddress("C/ Becario");
        positionDAO.insert(responsableFacturacion);

        analista.setName("Analista");
        analista.setDescription("Analista");
        analista.setEmail("analista@dominio.com");
        analista.setPhone("+344343");
        analista.setFax("+341212");
        analista.setCountry("España");
        analista.setProvince(province);
        analista.setCity("barbate");
        analista.setPostalCode("10111");
        analista.setAddress("C/ Becario");
        positionDAO.insert(analista);

        // contactos
        antonioRuiz.setName("Antonio Ruiz");
        antonioRuiz.setNotified(false);
        antonioRuiz.setEmail("antonio@dominio.com");
        antonioRuiz.setEmail2("antonio@dominio.com");
        antonioRuiz.setPhone("+341234");
        antonioRuiz.setPhone2("+341234");
        antonioRuiz.setMobile("+341234");
        antonioRuiz.setFax("+349876");
        antonioRuiz.setCountry("España");
        antonioRuiz.setProvince(province);
        antonioRuiz.setCity("Casarrubuelos de arriba");
        antonioRuiz.setPostalCode("90666");
        antonioRuiz.setMobile("90666");
        antonioRuiz.setAddress("C/ Antonio Ruiz");
        contactDAO.insert(antonioRuiz);

        pilarSancho.setName("Pilar M. de Sancho");
        pilarSancho.setEmail("pilar@dominio.com");
        pilarSancho.setNotified(false);
        pilarSancho.setEmail2("pilar@dominio.com");
        pilarSancho.setPhone("+342468");
        pilarSancho.setPhone2("212312");
        pilarSancho.setMobile("212312");
        pilarSancho.setFax("+348642");
        pilarSancho.setCountry("España");
        pilarSancho.setProvince(province);
        pilarSancho.setCity("Casarrubuelos de abajo");
        pilarSancho.setPostalCode("90123");
        pilarSancho.setAddress("C/ Pilar Sancho");
        contactDAO.insert(pilarSancho);

        // relacion entre contactos y puestos
        final ContactInfo contactInfoAntonio = new ContactInfo();
        contactInfoAntonio.setContact(antonioRuiz);
        contactInfoAntonio.setDepartment(department);
        contactInfoAntonio.setOrganization(organization);
        contactInfoAntonio.setPosition(becario);
        antonioRuiz.addContactInfo(contactInfoAntonio);
        contactDAO.update(antonioRuiz);

        final ContactInfo contactInfoPilar = new ContactInfo();
        contactInfoPilar.setContact(pilarSancho);
        contactInfoPilar.setDepartment(department);
        contactInfoPilar.setOrganization(organization);
        contactInfoPilar.setPosition(analista);
        pilarSancho.addContactInfo(contactInfoPilar);
        contactDAO.update(pilarSancho);
    }
}