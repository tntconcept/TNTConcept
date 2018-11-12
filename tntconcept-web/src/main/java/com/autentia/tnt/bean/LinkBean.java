package com.autentia.tnt.bean;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import com.autentia.tnt.businessobject.Link;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.dao.search.LinkSearch;
import com.autentia.tnt.dao.search.UserSearch;
import com.autentia.tnt.mail.DefaultMailService;
import com.autentia.tnt.manager.admin.LinkManager;
import com.autentia.tnt.manager.admin.UserManager;
import com.autentia.tnt.manager.security.AuthenticationManager;
import com.autentia.tnt.util.ConfigurationUtil;
import com.autentia.tnt.util.FacesUtils;
import com.autentia.tnt.util.SpringUtils;

public class LinkBean extends BaseBean {

	/** Manager */
	private static LinkManager manager = LinkManager.getDefault();

	/**
	 * UserManager
	 */
	private static UserManager userManager = UserManager.getDefault();

	/**
	 * Default authentication manager
	 */
	private static final AuthenticationManager authManager = AuthenticationManager.getDefault();

	private String name;
	private String link;

	public LinkBean() {

	}

	public LinkBean(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLink() {
		return this.link;
	}
	
	public List<User> getUserWithName(String name) {
		UserSearch search = new UserSearch();
		search.setLogin(name);

		return userManager.getAllEntities(search, null);
	}
	
	public Link generateLink(String name) {
		
		// Generate random string for verification link

		StringBuilder buffer = new StringBuilder();
		char[] characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
		Random random = new Random();
		for (int i = 0; i < 30; i++) {
			buffer.append(characters[random.nextInt(characters.length)]);
		}

		Link link = new Link();
		link.setLink(buffer.toString());
		link.setUser(name);
		return link;
	}
	
	public void sendMail(Link link, String mailAddress) {
		DefaultMailService mailService = (DefaultMailService) SpringUtils.getSpringBean("mailService");
		try {
			HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
			String url = req.getRequestURL().toString();
			
			mailService.send(mailAddress, "[RESETEO DE CONTRASEÑA] Email de verificación",
					"Haz click en el siguiente link para verificar que eres tú si quieres cambiar la contraseña: "+url.substring(0, url.length() - req.getRequestURI().length()) + req.getContextPath() + "/"+"linkEmailVerification.jsf?link="
							+ link.getLink());
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public String passwordResetRequest() {
		
		// Check if user exists or is active

		List<User> users = getUserWithName(this.name);
		
		if (!users.isEmpty() && users.get(0).isActive()) {
			
			// send mail and store link

			Link link = generateLink(this.name);
			
			manager.insertEntityWithoutUser(link);
			
			sendMail(link, users.get(0).getEmail());
			
		} else {
			// do nothing, user doesn't exist
			System.out.println("ignore restablishment");
		}

		return "emailSent";
	}
	
	public List<Link> getLinksWithLink(String link) {
		
		LinkSearch search = new LinkSearch();
		search.setLink(link);

		return manager.getAllEntities(search, null);
	}
	
	public void deleteLinkFromBD(Link link) {
		
		manager.deleteEntity(link);
		
	}
	
	public String checkLinkAndResetPassword(String link) {


		List<Link> links = getLinksWithLink(link);
		
		if (!links.isEmpty() && isOnTime(links.get(0))) {

			List<User> users = getUserWithName(links.get(0).getUser());

			if (!users.isEmpty() && users.get(0).isActive()) {
				
				
				deleteLinkFromBD(links.get(0));
				String resetPassword = resetPassword(users.get(0));
				
				return "Tu nueva contraseña es: <b>"+resetPassword+"</b></br> <p>Se te pedirá que la modifiques al entrar por primera vez.</p>";
			}

		}
		return "<p>El enlace no existe o ha caducado</p>";
	}
	
	public String resetPassword(User user) {
		// get random words from properties, depending on locale
		String[] rnd0 = FacesUtils.formatMessage("AuthenticationManager.randomWords0").split(",");
		String[] rnd1 = FacesUtils.formatMessage("AuthenticationManager.randomWords1").split(",");
		String[] rnd2 = FacesUtils.formatMessage("AuthenticationManager.randomWords2").split(",");
		String[] rnd3 = FacesUtils.formatMessage("AuthenticationManager.randomWords3").split(",");
		String[] rnd4 = FacesUtils.formatMessage("AuthenticationManager.randomWords4").split(",");

		// Change user password
		String changedPassword = authManager.resetPassword(user, rnd0, rnd1, rnd2, rnd3, rnd4);
		if (!ConfigurationUtil.getDefault().isLdapProviderEnabled()) {
			userManager.updateEntity(user, true);
		}
		return changedPassword;
	}

	public boolean isOnTime(Link link) {
		Date nowDate = new Date();
		Date insertDate = link.getInsertDate();
		Calendar cal = Calendar.getInstance();
		cal.setTime(insertDate);
		int dayOfInsertion = cal.get(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, dayOfInsertion+1);
		return nowDate.before(cal.getTime());
	}

	public String goPasswordChange() {
		return "passwordChange";
	}
}