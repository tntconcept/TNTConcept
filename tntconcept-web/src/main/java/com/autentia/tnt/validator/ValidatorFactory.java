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

package com.autentia.tnt.validator;

import java.util.HashMap;
import javax.faces.validator.Validator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.util.ConfigurationUtil;


public class ValidatorFactory {

	private static final Log log = LogFactory.getLog(ValidatorFactory.class);

	private static ValidatorFactory instance = null;

	private HashMap<String, Class> tableValidators = new HashMap<String, Class>();

	private ValidatorFactory() {
	}

	private static void init() {
		if (instance == null) {
			synchronized (ValidatorFactory.class) {
				if (instance == null) {
					instance = new ValidatorFactory();
				}
			}
		}
	}

	public static ValidatorFactory getFactory() {
		init();
		return instance;
	}

	private Validator getValidator(String className) {

		Class classFinal = null;
		Validator validator = null;

		try {
			if (tableValidators.containsKey(className)) {
				classFinal = (Class) tableValidators.get(className);
			} else {
				ClassLoader cl = getClass().getClassLoader();
				classFinal = cl.loadClass(className);
				tableValidators.put(className, classFinal);
			}
			validator = (Validator) classFinal.newInstance();
		} catch (Exception ex) {
			log.error("Error creating Validator: " + ex.getMessage());
		}
		return validator;
	}

	public Validator getIdentityCardValidator() {

		// conseguir nombre de clase por propiedar en properties
		String className = ConfigurationUtil.getDefault().getIdentityCardValidator();
		return getValidator(className);
	}

	public Validator getMoneyValidator() {
		// conseguir nombre de clase por propiedar en properties
		String className = ConfigurationUtil.getDefault().getMoneyValidator();
		return getValidator(className);
	}
	
	public Validator getAccountEntryValidator() {
		String className = ConfigurationUtil.getDefault().getAccountEntryValidator();
		return getValidator(className);
	}
	

	public Validator getPeriodicalAccountEntryValidator() {
		String className = ConfigurationUtil.getDefault().getPeriodicalAccountEntryValidator();
		return getValidator(className);
	}
	
	public Validator getDateValidator() {
		String className = ConfigurationUtil.getDefault().getDateValidator();
		return getValidator(className);
	}
}
