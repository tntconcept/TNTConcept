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


package com.autentia.tnt.bean;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import com.autentia.tnt.businessobject.Link;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.dao.DataNotFoundException;
import com.autentia.tnt.dao.search.LinkSearch;
import com.autentia.tnt.mail.DefaultMailService;
import com.autentia.tnt.manager.admin.LinkManager;
import com.autentia.tnt.manager.admin.UserManager;
import com.autentia.tnt.manager.security.AuthenticationManager;
import com.autentia.tnt.util.ConfigurationUtil;
import com.autentia.tnt.util.FacesUtils;
import com.autentia.tnt.util.SpringUtils;

public class LinkBean extends BaseBean {
	
	private static final String LINK_ENTRYPOINT_PATH = "/linkEmailVerification.jsf";

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
	private boolean resetEmailFailed=false;

	public LinkBean() {
	}

	private void resetLinkBean() {
		this.name=null;
		this.link=null;
		this.resetEmailFailed=false;
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

	public boolean isResetEmailFailed() {
		return resetEmailFailed;
	}

	public void setResetEmailFailed(boolean resetEmailFailed) {
		this.resetEmailFailed = resetEmailFailed;
	}

	public User getUserByName(String name) {
		return userManager.getUserByLogin(name);
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
		DefaultMailService mailService = getMailService();
		try {
			String verificationLink = buildResetPasswordVerificationLink(link);
			
			mailService.send(mailAddress, "[RESETEO DE CONTRASEÑA] Email de verificación",
					"Haz click en el siguiente link para verificar que eres tú si quieres cambiar la contraseña: " + verificationLink);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	private String buildResetPasswordVerificationLink(Link link) {

		String entryPoint = ConfigurationUtil.getDefault().getTntconceptUrl() + LINK_ENTRYPOINT_PATH;
		
		return entryPoint + "?link=" + link.getLink();
	}

	protected ExternalContext getFacesExternalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}

	protected DefaultMailService getMailService() {
		return (DefaultMailService) SpringUtils.getSpringBean("mailService");
	}

	public String passwordResetRequest() {
		try{
			User user = getUserByName(this.name);
			if (user.isActive()) {
				Link link = generateLink(this.name);

				manager.insertEntityWithoutUser(link);

				sendMail(link, user.getEmail());
				setResetEmailFailed(false);
				return "emailSent";

			}else{
				setResetEmailFailed(true);
				return "emailSentFailed";
			}

		} catch (DataNotFoundException ex) {
			setResetEmailFailed(true);
			return "emailSentFailed";
		}
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

			User user = getUserByName(links.get(0).getUser());

			if (user!=null && user.isActive()) {
				
				deleteLinkFromBD(links.get(0));
				String resetPassword = resetPassword(user);
				
				return "Tu nueva contraseña es: <b>"+resetPassword+"</b></br> <p>Se te pedirá que la modifiques al entrar por primera vez.</p>";
			}
		}
		return "<p>El enlace no existe o ha caducado</p>";
	}
	
	public String resetPassword(User user) {
		// get random words from properties, depending on locale
		String[] rnd0 = callFacesUtilsFormatMessage("AuthenticationManager.randomWords0");
		String[] rnd1 = callFacesUtilsFormatMessage("AuthenticationManager.randomWords1");
		String[] rnd2 = callFacesUtilsFormatMessage("AuthenticationManager.randomWords2");
		String[] rnd3 = callFacesUtilsFormatMessage("AuthenticationManager.randomWords3");
		String[] rnd4 = callFacesUtilsFormatMessage("AuthenticationManager.randomWords4");
		
		// Change user password
		String changedPassword = authManager.resetPasswordExternal(user, rnd0, rnd1, rnd2, rnd3, rnd4);
		if (!ConfigurationUtil.getDefault().isLdapProviderEnabled()) {
			userManager.updateEntity(user, true);
		}
		return changedPassword;
	}
	
	protected String [] callFacesUtilsFormatMessage(String randomWords) {
		return FacesUtils.formatMessage(randomWords).split(",");
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
		resetLinkBean();
		return "passwordChange";
	}
}