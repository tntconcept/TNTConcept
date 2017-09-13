package com.autentia.tnt.bean;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.dao.search.UserSearch;
import com.autentia.tnt.mail.MailService;
import com.autentia.tnt.manager.admin.UserManager;
import com.autentia.tnt.manager.security.AuthenticationManager;
import com.autentia.tnt.manager.security.Principal;
import com.autentia.tnt.util.ConfigurationUtil;

public class ContractExpirationNotificationBean {

	private static final Log log = LogFactory.getLog(ContractExpirationNotificationBean.class);

	private MailService mailService;

	public ContractExpirationNotificationBean(MailService mailService) {
		this.mailService = mailService;
	}

	public int checkExpirationDate() throws MessagingException {

		authenticateAs(ConfigurationUtil.getDefault().getAdminUser());

		Date today = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date inThreeMonths = Date.from(LocalDate.now().plusMonths(3).atStartOfDay(ZoneId.systemDefault()).toInstant());

		// User with a contract ending within 3 months
		UserSearch search = new UserSearch();
		search.setActive(true);
		search.setStartEndContractDate(today);
		search.setEndEndContractDate(inThreeMonths);
		List<User> users = UserManager.getDefault().getAllEntities(search, null);

		// Users with a probation ending within 3 months
		search.reset();
		search.setActive(true);
		search.setStartEndTestPeriodDate(today);
		search.setEndEndTestPeriodDate(inThreeMonths);
		users.addAll(UserManager.getDefault().getAllEntities(search, null));

		log.info("Number of users with contract or probation to be expired: " + users.size());

		// send email
		if (!users.isEmpty()) {
			String emailSubject = ConfigurationUtil.getDefault().getMailNotificationContractSubject();
			StringBuilder emailContent = new StringBuilder();
			for (User user : users) {
				emailContent.append("\nUserId: " + user.getId() + ", Nombre: " + user.getName()
								  + ", Email: " + user.getEmail());
			}
			log.info("Email content: " + emailContent);
			for (String recipient : ConfigurationUtil.getDefault().getMailNotificationContractSendTo()) {
				mailService.send(recipient, emailSubject, emailContent.toString());
				log.info("Email sent to: " + recipient);
			}
		}

		return users.size();
	}

	// TODO Extract to a Security Utils Class or similar
	private void authenticateAs(final String userLogin) {
		final Principal principal = (Principal) AuthenticationManager.getDefault().loadUserByUsername(userLogin);
		Authentication auth = new UsernamePasswordAuthenticationToken(principal, principal.getUser().getPassword(),principal.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
	 }
}
