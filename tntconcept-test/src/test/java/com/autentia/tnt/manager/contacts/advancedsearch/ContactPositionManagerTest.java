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

package com.autentia.tnt.manager.contacts.advancedsearch;

import com.autentia.tnt.businessobject.Contact;
import com.autentia.tnt.businessobject.ContactInfo;
import com.autentia.tnt.businessobject.Department;
import com.autentia.tnt.businessobject.Organization;
import com.autentia.tnt.businessobject.OrganizationISOCategory;
import com.autentia.tnt.businessobject.OrganizationType;
import com.autentia.tnt.businessobject.Position;
import com.autentia.tnt.businessobject.Province;
import com.autentia.tnt.dao.hibernate.ContactDAO;
import com.autentia.tnt.dao.hibernate.DepartmentDAO;
import com.autentia.tnt.dao.hibernate.OrganizationDAO;
import com.autentia.tnt.dao.hibernate.OrganizationISOCategoryDAO;
import com.autentia.tnt.dao.hibernate.OrganizationTypeDAO;
import com.autentia.tnt.dao.hibernate.PositionDAO;
import com.autentia.tnt.dao.hibernate.ProvinceDAO;
import com.autentia.tnt.dao.search.AdvancedSearchContactSearch;
import com.autentia.tnt.tracking.EntityChange;
import com.autentia.tnt.tracking.hibernate.dao.EntityChangeDAO;
import com.autentia.tnt.util.HibernateUtil;
import com.autentia.tnt.util.testing.SpringUtilsForTesting;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContactPositionManagerTest {

    private static final boolean JUST_ACTIVE = false;
    private static final boolean ACTIVE_AND_HISTORICAL = true;
    private static SessionFactory sessionFactory;

    // datos basicos
    private Province province = new Province();
    private OrganizationType organizationType = new OrganizationType();
    private OrganizationISOCategory organizationISOCategory = new OrganizationISOCategory();
    private Organization organization = new Organization();
    private Department department = new Department();

    // informacion de los contactos
    private Position becario = new Position();
    private Position responsableDireccion = new Position();
    private Position responsableFacturacion = new Position();
    private Position analista = new Position();
    private Contact antonioRuiz = new Contact();
    private Contact pilarSancho = new Contact();

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
     * Busqueda sin filtrado
     */
    @Test
    public void testDoAdvancedSearchWithoutCriteria() {

        final PositionDAO positionDAO = (PositionDAO) SpringUtilsForTesting.getSpringBean("daoPosition");
        final ContactDAO contactDAO = (ContactDAO) SpringUtilsForTesting.getSpringBean("daoContact");
        final ContactPositionManager contactPositionManager = new ContactPositionManager(contactDAO, positionDAO);

        this.insertInitialData();
        this.insertContactData();

        final AdvancedSearchContactSearch advancedSearch = new AdvancedSearchContactSearch();
        final List<ContactPosition> contactPositions = contactPositionManager.doAdvancedSearch(advancedSearch, JUST_ACTIVE);
        // deben salir 4 resultados, puesto que hay 2 contactos con puesto y 2 puestos sin asignar
        if (contactPositions.size() != 4) {
            fail("Deberian haber salido 4 resultados, pero han salido " + contactPositions.size());
        }
    }

    /**
     * Buscar por puesto ocupado
     */
    @Test
    public void testDoAdvancedSearchBusyPosition() {

        final PositionDAO positionDAO = (PositionDAO) SpringUtilsForTesting.getSpringBean("daoPosition");
        final ContactDAO contactDAO = (ContactDAO) SpringUtilsForTesting.getSpringBean("daoContact");
        final ContactPositionManager contactPositionManager = new ContactPositionManager(contactDAO, positionDAO);

        this.insertInitialData();
        this.insertContactData();

        final AdvancedSearchContactSearch advancedSearch = new AdvancedSearchContactSearch();
        final List<Position> positions = new ArrayList<Position>();
        positions.add(becario);
        advancedSearch.setPositions(positions);
        final List<ContactPosition> contactPositions = contactPositionManager.doAdvancedSearch(advancedSearch, JUST_ACTIVE);
        // debe salir un resultado, que es Antonio Ruiz ya que posee el cargo de becario
        if (contactPositions.size() != 1) {
            fail("Deberia haber salido 1 resultado (1 contacto), pero han salido " + contactPositions.size());
        }

        if (!contactPositions.get(0).getName().equals("Antonio Ruiz")) {
            fail("El resultado debería haber sido 'Antonio Ruiz', pero ha sido " + contactPositions.get(0).getName());
        }
    }

    /**
     * Buscar por puesto libre
     */
    @Test
    public void testDoAdvancedSearchEmptyPosition() {

        final PositionDAO positionDAO = (PositionDAO) SpringUtilsForTesting.getSpringBean("daoPosition");
        final ContactDAO contactDAO = (ContactDAO) SpringUtilsForTesting.getSpringBean("daoContact");
        final ContactPositionManager contactPositionManager = new ContactPositionManager(contactDAO, positionDAO);

        this.insertInitialData();
        this.insertContactData();

        final AdvancedSearchContactSearch advancedSearch = new AdvancedSearchContactSearch();
        final List<Position> positions = new ArrayList<Position>();
        positions.add(responsableDireccion);
        advancedSearch.setPositions(positions);
        final List<ContactPosition> contactPositions = contactPositionManager.doAdvancedSearch(advancedSearch, JUST_ACTIVE);
        // debe salir un resultado que es el puesto sin ocupantes
        if (contactPositions.size() != 1) {
            fail("Deberia haber salido 1 resultado (1 puesto), pero han salido " + contactPositions.size());
        }

        if (!contactPositions.get(0).getPosition().equals("Responsable de Dirección")) {
            fail("El resultado debería haber sido 'Responsable de Dirección', pero ha sido " + contactPositions.get(0).getPosition());
        }
    }

    /**
     * Buscar por en historico y en activo
     */
    @Test
    public void testDoAdvancedSearchHistorical() {

        final PositionDAO positionDAO = (PositionDAO) SpringUtilsForTesting.getSpringBean("daoPosition");
        final ContactDAO contactDAO = (ContactDAO) SpringUtilsForTesting.getSpringBean("daoContact");
        final EntityChangeDAO entityChangeDAO = (EntityChangeDAO) SpringUtilsForTesting.getSpringBean("daoEntityChange");
        final ContactPositionManager contactPositionManager = new ContactPositionManager(contactDAO, positionDAO);

        this.insertInitialData();
        this.insertContactData();

        // añadimos que Pilar Sancho fue becaria
        final EntityChange changePosition = new EntityChange();
        changePosition.setField(Contact.FIELD_POSITION);
        changePosition.setNewValue("");
        changePosition.setOldValue("becario");
        changePosition.setEntityId(pilarSancho.getId());
        changePosition.setEntityName("com.autentia.tnt.businessobject.Contact");
        entityChangeDAO.insert(changePosition);

        final AdvancedSearchContactSearch advancedSearch = new AdvancedSearchContactSearch();
        final List<Position> positions = new ArrayList<Position>();
        positions.add(becario);
        advancedSearch.setPositions(positions);
        final List<ContactPosition> contactPositions = contactPositionManager.doAdvancedSearch(advancedSearch, ACTIVE_AND_HISTORICAL);
        // debe salir dos resultados, ya que hay uno que es becario actualmente y otro que lo fue
        if (contactPositions.size() != 2) {
            fail("Deberian haber salido 2 resultados, pero han salido " + contactPositions.size());
        }
    }

    /**
     * Buscar por en historico y en activo
     */
    @Test
    public void testDoAdvancedSearchHistoricalSimpleValue() {

        final PositionDAO positionDAO = (PositionDAO) SpringUtilsForTesting.getSpringBean("daoPosition");
        final ContactDAO contactDAO = (ContactDAO) SpringUtilsForTesting.getSpringBean("daoContact");
        final EntityChangeDAO entityChangeDAO = (EntityChangeDAO) SpringUtilsForTesting.getSpringBean("daoEntityChange");
        final ContactPositionManager contactPositionManager = new ContactPositionManager(contactDAO, positionDAO);

        this.insertInitialData();
        this.insertContactData();

        // añadimos que Pilar Sancho trabajaba en Italia
        final EntityChange changePosition = new EntityChange();
        changePosition.setField(Contact.FIELD_COUNTRY);
        changePosition.setNewValue("España");
        changePosition.setOldValue("Italia");
        changePosition.setEntityId(pilarSancho.getId());
        changePosition.setEntityName("com.autentia.tnt.businessobject.Contact");
        entityChangeDAO.insert(changePosition);

        final AdvancedSearchContactSearch advancedSearch = new AdvancedSearchContactSearch();
        advancedSearch.setCountry("Italia");
        final List<ContactPosition> contactPositions = contactPositionManager.doAdvancedSearch(advancedSearch, ACTIVE_AND_HISTORICAL);
        // debe salir dos resultados, ya que hay uno que es becario actualmente y otro que lo fue
        if (contactPositions.size() != 1) {
            fail("Deberia haber salido 1 resultado, pero han salido " + contactPositions.size());
        }
    }

    /**
     * Metodo encargado de insertar datos muy generales pero que son necesarios
     * en la gestion de contactos
     */
    private void insertInitialData() {

        final ProvinceDAO provinceDAO = (ProvinceDAO) SpringUtilsForTesting.getSpringBean("daoProvince");
        final OrganizationISOCategoryDAO organizationISOCategoryDAO = (OrganizationISOCategoryDAO) SpringUtilsForTesting.getSpringBean("daoOrganizationISOCategory");
        final OrganizationTypeDAO organizationTypeDAO = (OrganizationTypeDAO) SpringUtilsForTesting.getSpringBean("daoOrganizationType");
        final OrganizationDAO organizationDAO = (OrganizationDAO) SpringUtilsForTesting.getSpringBean("daoOrganization");
        final DepartmentDAO departmentDAO = (DepartmentDAO) SpringUtilsForTesting.getSpringBean("daoDepartment");

        // provincia
        province.setName("Madrid");
        provinceDAO.insert(province);

        // categoria de la organizacion
        organizationType.setName("prospecto");
        organizationTypeDAO.insert(organizationType);

        // categoria ISO de la organizacion
        organizationISOCategory.setName("A");
        organizationISOCategoryDAO.insert(organizationISOCategory);

        // organizacion
        organization.setName("Tecnologías de España");
        organization.setProvince(province);
        organization.setType(organizationType);
        organization.setCategory(organizationISOCategory);
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
        antonioRuiz.setEmail("antonio@dominio.com");
        antonioRuiz.setPhone("+341234");
        antonioRuiz.setFax("+349876");
        antonioRuiz.setCountry("España");
        antonioRuiz.setProvince(province);
        antonioRuiz.setCity("Casarrubuelos de arriba");
        antonioRuiz.setPostalCode("90666");
        antonioRuiz.setAddress("C/ Antonio Ruiz");
        contactDAO.insert(antonioRuiz);

        pilarSancho.setName("Pilar M. de Sancho");
        pilarSancho.setEmail("pilar@dominio.com");
        pilarSancho.setPhone("+342468");
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