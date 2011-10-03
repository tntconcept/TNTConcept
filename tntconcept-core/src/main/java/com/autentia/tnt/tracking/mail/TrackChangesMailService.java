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

package com.autentia.tnt.tracking.mail;

import java.util.Collection;
import java.util.ResourceBundle;

import javax.mail.MessagingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.mail.DefaultMailService;
import com.autentia.tnt.manager.security.AuthenticationManager;
import com.autentia.tnt.tracking.EntityChange;
import com.autentia.tnt.util.ConfigurationUtil;
import com.autentia.tnt.util.SpringUtils;

public class TrackChangesMailService {
	
	private static final Log log = LogFactory.getLog(TrackChangesMailService.class);

	private DefaultMailService mailService;
	
	
	
	/**
	 * Get default changes mail service bean defined in Spring.
	 * 
	 * @return the default configuration bean
	 */
	public static TrackChangesMailService getDefault() {
		return (TrackChangesMailService) SpringUtils.getSpringBean("changesMailService");
	}
	
	
	public void setMailService(DefaultMailService mailService) {
		this.mailService = mailService;
	}

	public void sendChangesMail(Collection<String> mailsTo, String entityTextKey, String entityUserFriendlyName, Collection<EntityChange> entityChanges){
		if(mailsTo!=null && !mailsTo.isEmpty()){
			String messageBody = composeMessage(entityTextKey, entityUserFriendlyName, entityChanges);
			for(String to:mailsTo){
				try {
					
					mailService.send(to, getChangesMailSubject(entityTextKey), messageBody);
				} catch (MessagingException e) {
					// can not send message
					log.error("Changes mail service failure", e);
				}
			}
		}
		
	}

	private String composeMessage(String entityTextKey,
			String entityUserFriendlyName, Collection<EntityChange> entityChanges) {
		StringBuilder sb = new StringBuilder();
		if(entityChanges!=null){
			for(EntityChange entityChange:entityChanges){
				String key = entityChange.getField();
				sb.append(getProperty(key));
				sb.append(" [");
				sb.append(getChangesMailBefore());
				sb.append(": ");
				sb.append(entityChange.getOldValue());
				sb.append("] [");
				sb.append(getChangesMailAfter());
				sb.append(": ");
				sb.append(entityChange.getNewValue());
				sb.append("]");
				sb.append(System.getProperty("line.separator"));
			}
		}
		return getChangesMailBody(entityTextKey, entityUserFriendlyName, sb.toString());
	}
	
	private String getChangesMailSubject(String entityTextKey){
		StringBuilder sb = new StringBuilder();
		sb.append(getProperty("mail.changes.mailSubject", "Cambios efectuados en"));
		sb.append(" ");
		sb.append(getProperty(entityTextKey, "..."));
		return sb.toString();
	}
	
	private String getChangesMailBody(String entityTextKey, String entityUserFriendlyName, String changes){
		StringBuilder sb = new StringBuilder();
		sb.append(getProperty("mail.changes.mailBody", "Se han producido los siguientes cambios en"));
		sb.append(" ");
		sb.append(getProperty(entityTextKey, "..."));
		sb.append(" ");
		sb.append(entityUserFriendlyName);
		sb.append(":");
		sb.append(System.getProperty("line.separator"));
		sb.append(System.getProperty("line.separator"));
		sb.append(changes);
		return sb.toString();
	}
	
	public String getProperty(String key){
		StringBuilder sb = new StringBuilder();
		sb.append("???");
		sb.append(key);
		sb.append("???");
		return getProperty(key,sb.toString());
	}

	private Object getChangesMailBefore() {
		return getProperty("mail.changes.before", "antes");
	}

	private Object getChangesMailAfter() {
		return getProperty("mail.changes.after", "antes");
	}
	
	/**
	 * Get a configuration property by name.
	 * 
	 * @param propertyName
	 *            property name
	 * @return a named property
	 */
	private String getProperty(String propertyName, String defaultValue) {
		ResourceBundle bundle=null;
		String ret = null;
		try{
			bundle = ResourceBundle.getBundle(
				"com.autentia.tnt.resources.messages", AuthenticationManager.getDefault().getCurrentPrincipal().getLocale());
			ret= bundle.getString(propertyName);
		}catch(Exception e){
			//Si se produce un error al recuperar los textos lo registramos en el log
			//y lo dejamos
			log.error("Could not retrieve text message.", e);
		}
		if (ret == null)
			ret = defaultValue;
		return ret;
	}
}
