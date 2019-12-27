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

package com.autentia.tnt.manager.bill;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.autentia.tnt.businessobject.Bill;
import com.autentia.tnt.businessobject.BillPayment;
import com.autentia.tnt.businessobject.BillState;
import com.autentia.tnt.businessobject.Project;
import com.autentia.tnt.dao.hibernate.BillDAO;
import com.autentia.tnt.dao.hibernate.ProjectDAO;
import com.autentia.tnt.util.HibernateUtil;
import com.autentia.tnt.util.testing.SpringUtilsForTesting;

public class BillAndBillPaymentTest {

	private static SessionFactory sessionFactory;

	private Bill bill = new Bill();
	
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
	 * Inserción de sólo un pago
	 */
	@Test
	public void testCreateBillWithOnlyOnePayment() {
		
		final BillDAO billDAO = (BillDAO) SpringUtilsForTesting.getSpringBean("daoBill");
		insertInitialData();
		
		final BillPayment payment = new BillPayment();
		payment.setAmount(new BigDecimal(1000));
		payment.setExpirationDate(new Date());
		payment.setBill(bill);
		bill.getBillPayment().add(payment);		
		billDAO.update(bill);
		
        if (bill.getBillPayment().size() != 1) {
            fail("la factura deberia tener 1 pago, pero tiene " + bill.getBillPayment().size());
        }
	}
	
	/**
	 * Inserción de tres pagos para comprobar que la factura vence el dia que vence el ultimo pago
	 * o cobro
	 */
	@Test
	public void testLastPayment() {
		
		final BillDAO billDAO = (BillDAO) SpringUtilsForTesting.getSpringBean("daoBill");
		insertInitialData();
		
		// se crean 3 fechas para los pagos, el 1 de enero, el 1 de julio y el 31 de diciembre de 2000
		final Calendar _2000enero01 = Calendar.getInstance();
		_2000enero01.set(2000, Calendar.JANUARY, 1);
		
		final Calendar _2000julio01 = Calendar.getInstance();
		_2000julio01.set(2000, Calendar.JULY, 1);
		
		final Calendar _2000diciembre31 = Calendar.getInstance();
		_2000diciembre31.set(2000, Calendar.DECEMBER, 31);

		// se crean 3 pagos y se les ponen fechas
		final BillPayment firstPayment = new BillPayment();
		firstPayment.setAmount(new BigDecimal(1000));
		firstPayment.setExpirationDate(_2000julio01.getTime());
		firstPayment.setBill(bill);
		bill.getBillPayment().add(firstPayment);	
		
		final BillPayment secondPayment = new BillPayment();
		secondPayment.setAmount(new BigDecimal(1000));
		secondPayment.setExpirationDate(_2000diciembre31.getTime());
		secondPayment.setBill(bill);
		bill.getBillPayment().add(secondPayment);			
		
		final BillPayment thirdPayment = new BillPayment();
		thirdPayment.setAmount(new BigDecimal(1000));
		thirdPayment.setExpirationDate(_2000enero01.getTime());
		thirdPayment.setBill(bill);
		bill.getBillPayment().add(thirdPayment);	
		
		// se fuerza el guardado en base de datos de los pagos ya que el calculo del ultimo plazo
		// se realiza mediante un mapeo de tipo formula
		sessionFactory.getCurrentSession().getTransaction().commit();
		sessionFactory.getCurrentSession().beginTransaction();
		
		// se recupera la factura y con ello se recalcula el campo de tipo formula
		bill = billDAO.loadById(bill.getId());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		String fechaFactura = sdf.format(bill.getExpiration());
		String fecha31Diciembre = sdf.format(_2000diciembre31.getTime());
		
        if (!fechaFactura.equals(fecha31Diciembre)) {
            fail("la factura deberia finalizar el " + fecha31Diciembre + " en vez de el " + fechaFactura);
        }
	}
	
    /**
     * Metodo encargado de insertar datos muy generales pero que son necesarios
     * en la gestion de facturas
     */
	private void insertInitialData() {
		
		final BillDAO billDAO = (BillDAO) SpringUtilsForTesting.getSpringBean("daoBill");
		final ProjectDAO projectDAO = (ProjectDAO) SpringUtilsForTesting.getSpringBean("daoProject");
		
		final Project project = new Project();
		projectDAO.insert(project);
		
		bill = new Bill();
		bill.setCreationDate(new Date());
		bill.setState(BillState.EMITTED);
		bill.setNumber("200901");
		bill.setName("Factura por servicios");
		bill.setProject(project);
		bill.setStartBillDate(new Date());
		bill.setEndBillDate(new Date());
		bill.setState(BillState.EMITTED);
		bill.setOrderNumber("12");		
		billDAO.insert(bill);
	}
}
