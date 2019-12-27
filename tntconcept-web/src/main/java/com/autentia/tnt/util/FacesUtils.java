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

package com.autentia.tnt.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javax.faces.FactoryFinder;
import javax.faces.application.Application;
import javax.faces.application.ApplicationFactory;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

import com.autentia.tnt.bean.ApplicationBean;
import com.autentia.tnt.manager.security.AuthenticationManager;

import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

/**
 * Utility JSF class
 * 
 * @author ivan
 */
public class FacesUtils {
	/**
	 * Add informational message to current context. The same message is used as
	 * summary and detail.
	 * 
	 * @param clientId
	 *            client id (control id) to which message is associated (can be
	 *            null)
	 * @param args
	 *            optional arguments for message
	 * @param msgId
	 *            message id in resources file
	 */
	public static void addInfoMessage(String clientId, String msgId,
			Object... args) {
		String msg = formatMessage(msgId, args);
		FacesContext.getCurrentInstance().addMessage(clientId,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	/**
	 * Add warning message to current context. The same message is used as
	 * summary and detail.
	 * 
	 * @param clientId
	 *            client id (control id) to which message is associated (can be
	 *            null)
	 * @param args
	 *            optional arguments for message
	 * @param msgId
	 *            message id in resources file
	 */
	public static void addWarningMessage(String clientId, String msgId,
			Object... args) {
		String msg = formatMessage(msgId, args);
		FacesContext.getCurrentInstance().addMessage(clientId,
				new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg));
	}

	/**
	 * Add error message to current context. The same message is used as summary
	 * and detail.
	 * 
	 * @param clientId
	 *            client id (control id) to which message is associated (can be
	 *            null)
	 * @param args
	 *            optional arguments for message
	 * @param msgId
	 *            message id in resources file
	 */
	public static void addErrorMessage(String clientId, String msgId,
			Object... args) {
		String msg = formatMessage(msgId, args);
		FacesContext.getCurrentInstance().addMessage(clientId,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
	}

	/**
	 * Return the id of the view
	 * 
	 * @return
	 */
	public static String getViewId() {
		FacesContext fc = FacesContext.getCurrentInstance();
		UIViewRoot view = fc.getViewRoot();

		String id = (view == null) ? "" : view.getViewId();
		return id;
	}

	/**
	 * Format a resource message given some parameters. The current JSF locale
	 * is used.
	 * 
	 * @param msgId
	 *            id of message in resource file
	 * @param args
	 *            arguments of the message
	 * @return the formatted string
	 */
	public static String formatMessage(String msgId, Object... args) {
		String msg = getMessage(msgId);
		return MessageFormat.format(msg, args);
	}

	/**
	 * Obtain a resource message. The current JSF locale is used.
	 * 
	 * @param msgId
	 *            id of message in resource file *
	 * @return the message
	 */
	public static String getMessage(String msgId) {
		String bundleName = FacesContext.getCurrentInstance().getApplication()
				.getMessageBundle();
		Locale locale = FacesContext.getCurrentInstance().getViewRoot()
				.getLocale();
		ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale);
		return bundle.getString(msgId);
	}

	/**
	 * Put an object in session
	 * 
	 * @param name
	 *            name of object to put in session
	 * @param obj
	 *            object to put in session
	 */
	public static void putInSession(String name, Object obj) {
		getSessionMap().put(name, obj);
	}

	/**
	 * Remove an object from session
	 * 
	 * @param name
	 *            name of object to remove
	 */
	public static void removeFromSession(String name) {
		getSessionMap().remove(name);
	}

	/**
	 * Get a managed bean by name. This method looks first for beans defined
	 * under JSF and, if that fails, looks under Spring's context (if that is
	 * configured in faces-config.xml file).
	 * 
	 * @param name
	 *            bean name
	 * @return the managed bean associated to the name
	 */
	public static Object getBean(String name) {
		return getValueBinding(getJsfEl(name)).getValue(
				FacesContext.getCurrentInstance());
	}

	/**
	 * Get a value binding based on a pseudo-EL expresion
	 * 
	 * @param el
	 *            pseudo-EL expression
	 * @return the value binding
	 */
	public static ValueBinding getValueBinding(String el) {
		return getApplication().createValueBinding(el);
	}

	/**
	 * Get JSF application bean
	 * 
	 * @return JSF application singleton
	 */
	public static Application getApplication() {
		ApplicationFactory appFactory = (ApplicationFactory) FactoryFinder
				.getFactory(FactoryFinder.APPLICATION_FACTORY);
		return appFactory.getApplication();
	}

	/**
	 * Get a request parameter by name
	 * 
	 * @param name
	 *            parameter name
	 * @return value of parameter
	 * @deprecated try to pass values using getManagedBean instead
	 */
	public static String getRequestParameter(String name) {
		return (String) FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get(name);
	}

	/**
	 * Get a component given its id
	 * 
	 * @param id
	 *            component id according to JSF specification
	 * @return the requested component
	 */
	public static UIComponent getComponent(String id) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		UIViewRoot root = facesContext.getViewRoot();
		return root.findComponent(id);
	}

	/**
	 * Skip to RENDER_RESPONSE lifecycle phase
	 */
	public static void renderResponse() {
		FacesContext.getCurrentInstance().renderResponse();
	}

	/**
	 * Get session attributes mutable map
	 * 
	 * @return mutable map of session attributes
	 */
	private static Map getSessionMap() {
		ExternalContext sessionContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		return sessionContext.getSessionMap();
	}

	/**
	 * Get the pseudo-EL name of a bean
	 * 
	 * @param name
	 *            name of bean
	 * @return pseudo-EL expression to get bean
	 */
	private static String getJsfEl(String name) {
		return "#{" + name + "}";
	}

	/**
	 * Get the Locale of the view root
	 * 
	 * @return Locale of the view root
	 */
	public static Locale getViewLocale() {
		UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
		return viewRoot.getLocale();
	}

	/**
	 * Set the user locale
	 * 
	 * @param locale
	 *            the locale user defined
	 */
	public static void setViewLocale(Locale locale) {
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
	}

	/**
	 * Set the user preference locale
	 * 
	 * @return user preference locale stablished
	 */
	public static Locale setActualLoggedLocale() {
		
		boolean loggedIn = (AuthenticationManager.getDefault()
				.getCurrentPrincipal() != null);
		Locale local = null;
		if (loggedIn) {
			local = AuthenticationManager.getDefault().getCurrentPrincipal().getLocale();
			if (local != null) {
				FacesUtils.setViewLocale(local);
			}
		}
		
		return local;
	}
	
	/**
	 * Cleans the dom tree node forcing repaint
	 */
	public static void clearDomRoot(){
		FacesContext.getCurrentInstance().getViewRoot().getChildren().clear();
	}

}
