package com.autentia.tnt.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;

import com.autentia.tnt.businessobject.TaxableObject;

public class IvaApplicator {

	public static void applyIvaToTaxableObject(Date creationDate, TaxableObject item) {
		
		final GregorianCalendar firstDayOfActualIva = new GregorianCalendar(2012, 8, 1);
		if (creationDate.after(firstDayOfActualIva.getTime())) {		
			item.setIva(new BigDecimal(ConfigurationUtil.getDefault().getActualIva()));
		} else {
			
			final GregorianCalendar fecha = new GregorianCalendar(2010, 5, 30);
			if (creationDate.after(fecha.getTime())) {		
				item.setIva(new BigDecimal(ConfigurationUtil.getDefault().getIvaUntilSeptember2012()));
			}
			else{			
				item.setIva(new BigDecimal(ConfigurationUtil.getDefault().getIvaUntilJuly2010()));
			}
		}
	}
}