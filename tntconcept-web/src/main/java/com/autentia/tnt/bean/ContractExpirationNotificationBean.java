package com.autentia.tnt.bean;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.businessobject.Role;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.UserSearch;
import com.autentia.tnt.mail.DefaultMailService;
import com.autentia.tnt.manager.admin.RoleManager;
import com.autentia.tnt.manager.admin.UserManager;
import com.autentia.tnt.manager.security.AuthenticationManager;
import com.autentia.tnt.manager.security.Principal;
import com.autentia.tnt.tracking.EntityChange;
import com.autentia.tnt.tracking.mail.TrackChangesMailService;
import com.autentia.tnt.util.ConfigurationUtil;
import com.autentia.tnt.util.SpringUtils;

public class ContractExpirationNotificationBean {
	private static final Log log = LogFactory.getLog(ContractExpirationNotificationBean.class);

	public void checkExpirationDate() throws MessagingException {

		// TODO extract login name to a properties file
		authenticateAs(ConfigurationUtil.getDefault().getAdminUser());

		List<User> users = UserManager.getDefault().getAllEntities(null, new SortCriteria("name"));

		for (User user : users) {
			Calendar contractExpirationCal = Calendar.getInstance();
			Calendar testExpirationCal = Calendar.getInstance();
			
			if(user.getEndContractDate() != null){
				contractExpirationCal.setTime(user.getEndContractDate());
			}
			
			if(user.getEndTestPeriodDate() != null){
				testExpirationCal.setTime(user.getEndTestPeriodDate());
			}
			
			Calendar actualCal = Calendar.getInstance();
			
			

			int diffYearContract = contractExpirationCal.get(Calendar.YEAR) - actualCal.get(Calendar.YEAR);
			int diffMonthContract = diffYearContract * 12 + contractExpirationCal.get(Calendar.MONTH) - actualCal.get(Calendar.MONTH);
			
			int diffYearTest = testExpirationCal.get(Calendar.YEAR) - actualCal.get(Calendar.YEAR);
			int diffMonthTest = diffYearTest * 12 + contractExpirationCal.get(Calendar.MONTH) - actualCal.get(Calendar.MONTH);
			
			if(diffMonthTest == 3 || diffMonthContract == 3){
				DefaultMailService mailService = (DefaultMailService) SpringUtils.getSpringBean("mailService");
				
				for (String admin : ConfigurationUtil.getDefault().getMailNotificationContractSendTo()) {
					String text = "Id: " + user.getId() + "\n" + "Nombre y Apellidos: " + user.getName() + "\n" + "Email: " + user.getEmail();
					mailService.send(admin, ConfigurationUtil.getDefault().getMailNotificationContractSubject(), text);
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
}
