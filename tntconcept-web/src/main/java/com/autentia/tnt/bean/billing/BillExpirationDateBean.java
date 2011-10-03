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
package com.autentia.tnt.bean.billing;


import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.businessobject.Bill;

import com.autentia.tnt.businessobject.BillPayment;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.hibernate.UserDAO;
import com.autentia.tnt.manager.billing.BillManager;
import com.autentia.tnt.manager.security.AuthenticationManager;
import com.autentia.tnt.manager.security.Principal;
import com.autentia.tnt.util.ConfigurationUtil;
import com.autentia.tnt.util.SpringUtils;
import com.ibm.icu.impl.ICUBinary.Authenticate;

public class BillExpirationDateBean {
	
	
	
	private static final Log log = LogFactory.getLog(BillExpirationDateBean.class);
	//private Set<BillPayment> billPayment = new LinkedHashSet<BillPayment>();
	
	public void updateExpirationDate(){
		
		
		// TODO extract login name to a properties file
		authenticateAs(ConfigurationUtil.getDefault().getAdminUser());
		
		List<Bill> bills = BillManager.getDefault().getAllEntities( null, new SortCriteria("name"));
		
		
		for (Bill b: bills){					

			Date creationDate = b.getCreationDate();
			
			creationDate.setMonth(creationDate.getMonth()+1);			
			if(! b.getBillPayment().isEmpty()){
				for (BillPayment bp : b.getBillPayment()){
				
					bp.setExpirationDate(creationDate);
				}
			}
			else{
				//creamos el billPayment para el expirationDate
				createBillPayment(b);
				for (BillPayment bp : b.getBillPayment()){					
					bp.setExpirationDate(creationDate);
				}
				
			}
		}
	}

	
	// TODO Extract to a Security Utils Class or similar
	private void authenticateAs(final String userLogin) {
		final Principal principal = (Principal) AuthenticationManager.getDefault().loadUserByUsername(userLogin);		 
		Authentication auth = new UsernamePasswordAuthenticationToken(principal, principal.getUser().getPassword(),principal.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);		
	}
	
	
	public String createBillPayment(Bill bill) {
		final BillPayment item = new BillPayment();
		item.setBill(bill);
		if (bill.getBillPayment() == null) {
			bill.setBillPayment(new HashSet());
		}
		bill.getBillPayment().add(item);
		return null;
	}
	

}
