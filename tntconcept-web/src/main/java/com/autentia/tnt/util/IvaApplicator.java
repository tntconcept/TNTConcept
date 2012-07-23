package com.autentia.tnt.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;

import com.autentia.tnt.businessobject.TaxableObject;

public class IvaApplicator {

	public static void applyIvaToTaxableObject(Date creationDate, TaxableObject item) {
		
		final GregorianCalendar fecha = new GregorianCalendar(2010,6,30);
		if(creationDate.after(fecha.getTime())){		
			item.setIva(new BigDecimal(ConfigurationUtil.getDefault().getIva18()));
		}
		else{			
			item.setIva(new BigDecimal(ConfigurationUtil.getDefault().getIva()));
		}
	}
}