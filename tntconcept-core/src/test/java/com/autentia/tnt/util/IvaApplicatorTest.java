package com.autentia.tnt.util;

import com.autentia.tnt.businessobject.TaxableObject;
import com.autentia.tnt.test.utils.DateMother;
import com.autentia.tnt.test.utils.SpringUtilsForTesting;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.Date;


public class IvaApplicatorTest {
    private static final BigDecimal IVA16 = BigDecimal.valueOf(16);
    private static final BigDecimal IVA18 = BigDecimal.valueOf(18);
    private static final BigDecimal IVA21 = BigDecimal.valueOf(21);

    @Before
    public void configureApplicationContext() {
        ApplicationContext appCtx = new ClassPathXmlApplicationContext("applicationContext-test.xml");
        SpringUtilsForTesting.configure(appCtx);
    }

    @Test
    public void testIva16() {
        final TestTaxableObject object = new TestTaxableObject();
        final Date date = DateMother.from(2009, 10, 21);

        IvaApplicator.applyIvaToTaxableObject(date, object);

        Assert.assertEquals(IVA16, object.getIva());
    }

    @Test
    public void testFirstDayIva18() {
        final TestTaxableObject object = new TestTaxableObject();
        final Date date = DateMother.from(2010, 10, 1);

        IvaApplicator.applyIvaToTaxableObject(date, object);

        Assert.assertEquals(IVA18, object.getIva());
    }

    @Test
    public void testLastDayIva18() {
        final TestTaxableObject object = new TestTaxableObject();
        final Date date = DateMother.from(2012, 6, 1);

        IvaApplicator.applyIvaToTaxableObject(date, object);

        Assert.assertEquals(IVA18, object.getIva());
    }

    @Test
    public void testIva21() {
        final TestTaxableObject object = new TestTaxableObject();
        final Date date = DateMother.from(2012, 12, 1);

        IvaApplicator.applyIvaToTaxableObject(date, object);

        Assert.assertEquals(IVA21, object.getIva());
    }


    private static class TestTaxableObject implements TaxableObject {
        private BigDecimal iva;

        public BigDecimal getIva() {
            return this.iva;
        }

        @Override
        public void setIva(BigDecimal iva) {
            this.iva = iva;
        }
    }

}