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

package com.autentia.tnt.tracking.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

import com.autentia.tnt.tracking.Tracking;
import com.autentia.tnt.tracking.exception.TrackingChangesException;

@Aspect
public class ChangesHistoryAspect {

	/** Logger */
	private static final Log log = LogFactory
			.getLog(ChangesHistoryAspect.class);

	private final static String FIELD_KEY = "fieldName";

	private final static String METHOD_KEY = "method";

	/**
	 * Interception method that add current changes for history to the target object 
	 * @param call
	 */
	@Before("@annotation(com.autentia.tnt.tracking.annotation.ChangesHistory)")
	public void addEntityChange(JoinPoint call) {
		if (isTrackingActive(call)) {
			try {

				Properties props = this.getAnnotationProperties(call);
				String methodName = props.getProperty(METHOD_KEY);
				String fieldName = props.getProperty(FIELD_KEY);

				Object[] args = call.getArgs();
				if (args.length != 1) {
					throw new TrackingChangesException(
							"Invalid number of arguments.");
				}
				Object newObject = args[0];
				Method metodo = getCallMethod(call);
				String setter = metodo.getName();
				if (!setter.startsWith("set")) {
					throw new TrackingChangesException("Invalid setter method.");
				}
				String getterName = setter.substring(3);
				getterName = "get" + getterName;

				Method getter = getMethod(call, getterName);

				Object callObject = call.getTarget();

				Object[] argsMethod = {};
				Object oldObject = getter.invoke(callObject, argsMethod);

				String oldValue = "";
				String newValue = "";

				Method method = getSetterArgsClass(call)[0]
						.getMethod(methodName);
				if (oldObject != null) {
					Object returnObject = method.invoke(oldObject, argsMethod);
					if (returnObject != null) {
						oldValue = returnObject.toString();
					}
				}
				if (newObject != null) {
					Object returnObject = method.invoke(newObject, argsMethod);
					if (returnObject != null) {
						newValue = returnObject.toString();
					}
				}

				((Tracking) callObject).addEntityChange(fieldName, oldValue,
						newValue);

			} catch (SecurityException e) {
				log.error("Could not track changes", e);
			} catch (NoSuchMethodException e) {
				log.error("Could not track changes", e);
			} catch (IllegalAccessException e) {
				log.error("Could not track changes", e);
			} catch (InvocationTargetException e) {
				log.error("Could not track changes", e);
			} catch (TrackingChangesException e) {
				log.error("Could not track changes", e);
			}
		}
	}

	/**
	 * checks if the target object has changes tracking active
	 * @param call
	 * @return
	 */
	private boolean isTrackingActive(JoinPoint call) {
		try{
			Tracking tracking = (Tracking)call.getTarget();
			return tracking.isTrackingActive();
		}catch(ClassCastException e){
			log.error("Intercepted object is not a Tracking object", e);
			return false;
		}
	}

	/**
	 * returns an array with the classes of the arguments of the setter method
	 * @param call
	 * @return
	 */
	private Class[] getSetterArgsClass(JoinPoint call) {

		Method metodo = getCallMethod(call);

		Class[] params = metodo.getParameterTypes();

		return params;
	}

	/**
	 * return the method with no arguments of the target object class with the name passed
	 * @param call
	 * @param methodName
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	private Method getMethod(JoinPoint call, String methodName)
			throws SecurityException, NoSuchMethodException {
		Method metodo = null;

		Signature sig = call.getSignature();
		Class clase = sig.getDeclaringType();

		Class[] args = {};
		metodo = clase.getMethod(methodName, args);

		return metodo;
	}

	/**
	 * @return Devuelve un properties con los atributos de la anotación
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws ClassNotFoundException
	 */
	private Properties getAnnotationProperties(JoinPoint call)
			throws SecurityException, NoSuchMethodException {
		Properties properties = new Properties();
		Method metodo = this.getCallMethod(call);

		ChangesHistory anotacion = metodo.getAnnotation(ChangesHistory.class);
		properties.put(FIELD_KEY, anotacion.fieldName());
		properties.put(METHOD_KEY, anotacion.method());

		return properties;
	}

	/**
	 * Returns the intercepted method
	 * @param call
	 * @return
	 */
	private Method getCallMethod(JoinPoint call) {
		Method metodo = null;
		MethodSignature sig = (MethodSignature) call.getSignature();
		metodo = sig.getMethod();

		return metodo;
	}

}
