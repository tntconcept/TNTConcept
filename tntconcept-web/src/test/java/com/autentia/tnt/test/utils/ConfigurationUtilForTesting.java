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

package com.autentia.tnt.test.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import javax.naming.NamingException;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.util.ConfigurationUtil;
import com.autentia.tnt.util.SpringUtils;

public class ConfigurationUtilForTesting extends ConfigurationUtil {
	private static final Log log = LogFactory
			.getLog(ConfigurationUtilForTesting.class);

	/**
	 * Get default configuration bean defined in Spring.
	 *
	 * @return the default configuration bean
	 */
	public static ConfigurationUtilForTesting getDefault() {
		return (ConfigurationUtilForTesting) SpringUtils
				.getSpringBean("configuration");
	}

	/** Configuration object */
	private PropertiesConfiguration config = null;

	/** Configuration directory */
	private String configDir = null;

	/**
	 * Constructor
	 *
	 * @param jndiPathVar
	 *            JNDI variable in which configuration directory is stored
	 * @param file
	 *            path to configuration file
	 */


	public ConfigurationUtilForTesting(String jndiPathVar, String file)
			throws ConfigurationException, NamingException {

		super();
		configDir =  System.getProperty("user.dir") + jndiPathVar;
		if (!configDir.endsWith("/") && !configDir.endsWith("\\")) {
			configDir += "/";
		}
		config = new PropertiesConfiguration(configDir + file);
	}

	/**
	 * Get configuration directory (with ending "/")
	 *
	 * @return configuration directory (with ending "/")
	 */
	public String getConfigDir() {
		return configDir;
	}

	/**
	 * Get id of public bulletin board category.
	 *
	 * @return id of public bulletin board category
	 */
	public int getIdPublicCategory() {
		return Integer.parseInt(getProperty("idPublicCategory", "1"));
	}

	/**
	 * Get id of public bulletin board category. Get id of public bulletin board
	 * category.
	 *
	 * @return id of public bulletin board category
	 */
	public int getIdOurCompany() {
		return Integer.parseInt(getProperty("idOurCompany", "1"));
	}

	/**
	 * Get id of the main Category's Id for Quality Documents.
	 *
	 * @return id of quality document's category
	 */
	public int getQualityDocumentCategoryId() {
		return Integer.parseInt(getProperty("qualityDocumentCategoryId", "1"));
	}

	/**
	 * Get id of the Category's Id for User Documents.
	 *
	 * @return id of User Documents Category.
	 */
	public int getUserDocumentCategoryId() {
		return Integer.parseInt(getProperty("userDocumentCategoryId", "4"));
	}

	/**
	 * Get uploaded files path (always with a trailing /).
	 *
	 * @return uploaded files root path
	 */
	public String getUploadPath() {
		String ret = getProperty("pathFicheros", "/var/lib/tntconcept/upload");
		if (!ret.endsWith("\\") || !ret.endsWith("/")) {
			ret += File.separator;
		}

		return ret;
	}

	/**
	 * Get personal reports path (always with a trailing /).
	 *
	 * @return personal reports path
	 */
	public String getReportPath() {
		return getProperty("pathReports", "/var/lib/tntconcept/reports");
	}

	/**
	 * Get number of children objectives that will be created when a previous
	 * objective expires.
	 *
	 * @return number of children objectives
	 */
	public int getChildObjectivesCount() {
		return Integer.parseInt(getProperty("childObjectivesCount", "3"));
	}

	/**
	 * Get minimum number of children objectives that can be created when a
	 * previous objective expires.
	 *
	 * @return number of children objectives
	 */
	public int getMinChildObjectivesCount() {
		return Integer.parseInt(getProperty("minChildObjectivesCount", "2"));
	}

	/**
	 * Get maximum number of children objectives that can be created when a
	 * previous objective expires.
	 *
	 * @return number of children objectives
	 */
	public int getMaxChildObjectivesCount() {
		return Integer.parseInt(getProperty("maxChildObjectivesCount", "5"));
	}

	/**
	 * Get the number of days in milliseconds before a category is considered as
	 * updated
	 *
	 * @return number of days in milliseconds
	 */
	public long getUpdatedCategoryDaysInMillis() {
		return (long) Long.parseLong(getProperty("updatedCategoryDays", "5"))
				* 24 * 60 * 60 * 1000;
	}

	public int getCostId() {
		return Integer.parseInt(getProperty("costId", "2"));
	}

	public int getIncomeId() {
		return Integer.parseInt(getProperty("incomeId", "1"));
	}

	public int getInitialEntryId() {
		return Integer.parseInt(getProperty("initialEntryId", "4"));
	}

	public int getRoleAdminId() {
		return Integer.parseInt(getProperty("roleAdminId", "1"));
	}

	public int getRoleSupervisorId() {
		return Integer.parseInt(getProperty("roleSupervisorId", "2"));
	}

	public int getRoleUserId() {
		return Integer.parseInt(getProperty("roleUserId", "3"));
	}

	public int getRoleStaffId() {
		return Integer.parseInt(getProperty("roleStaffId", "4"));
	}

	public int getRoleClientId() {
		return Integer.parseInt(getProperty("roleClientId", "5"));
	}

	@Override
    public int getRoleProjectManagerId() {
        return Integer.parseInt(getProperty("roleProjectManagerId", "6"));
    }

	public float getIvaUntilJuly2010() {
		return Float.parseFloat(getProperty("ivaUntilJuly2010", "16"));
	}

	public float getIvaUntilSeptember2012() {
		return Float.parseFloat(getProperty("ivaUntilSeptember2012", "18"));
	}

	public float getActualIva() {
		return Float.parseFloat(getProperty("iva", "21"));
	}

	public String getAdminUser() {
		return getProperty("UserAdmin", "admin");
	}

	public String getIdentityCardValidator() {
		return getProperty("identityCardValidator",
				"com.autentia.tnt.validator.NifValidator");
	}

	public String getMoneyValidator() {
		return getProperty("moneyValidator",
				"com.autentia.tnt.validator.EuroValidator");
	}

	public String getAccountEntryValidator() {
		return getProperty("accountEntryValidator",
				"com.autentia.tnt.validator.AccountEntryValidator");
	}

	public String getPeriodicalAccountEntryValidator() {
		return getProperty("periodicalAccountEntryValidator",
				"com.autentia.tnt.validator.PeriodicalAccountEntryValidator");
	}

	public String getDateValidator() {
		return getProperty("dateValidator",
				"com.autentia.tnt.validator.DateValidator");
	}

	public int getAccountEntryMaximumYears() {
		return Integer.parseInt(getProperty("accountEntryMaximumYears", "4"));
	}

	public String getReportCSVDelimiter() {
		return getProperty("reportCSVDelimiter", ";");
	}

	public int getLoadingReportOnLoad() {
		return Integer.parseInt(getProperty("loadingReportOnLoad", "1"));
	}

	public int getOrganizationTypeProvider() {
		return Integer.parseInt(getProperty("organizationTypeProvider", "2"));
	}

	public int getOrganizationTypeProviderAndClient() {
		return Integer.parseInt(getProperty(
				"organizationTypeProviderAndClient", "3"));
	}

	public boolean getShowLogs() {
		return Boolean.parseBoolean(getProperty("showLogs", "false"));
	}

	public boolean getPayBillsWhenCreditTitleIsPaid() {
		return Boolean.parseBoolean(getProperty(
				"payBillsWhenCreditTitleIsPaid", "true"));
	}

	/**
	 * Get a configuration property by name.
	 *
	 * @param propertyName
	 *            property name
	 * @return a named property
	 */
	private String getProperty(String propertyName, String defaultValue) {
		String ret = config.getString(propertyName);
		if (ret == null)
			ret = defaultValue;
		return ret;
	}

	/**
	 *
	 * return the name of the logo file for reports. (just the name)
	 */
	public String getLogoName() {
		return getProperty("reportLogoName", "logo.bmp");
	}

	/**
	 * Return the path of the logo (including name)
	 *
	 * @return
	 */

	public String getLogoPath() {
		return getConfigDir() + getLogoName();
	}

	public InputStream getLogoAsStream() {
		File f = new File(getLogoPath());

		try {
			return new BufferedInputStream(new FileInputStream(f));
		} catch (FileNotFoundException e) {
			log.error("Error reading logo file", e);
			return null;
		}

	}

	/**
	 * Return a boolean value representing the value of isUsingExternalCss.
	 *
	 * @return
	 */
	public boolean isUsingExternalCss() {
		String valor = getProperty("isUsingExternalCss", "false");
		return Boolean.getBoolean(valor);
	}

	/**
	 * Return the name of the folder of docroot (external files)
	 *
	 * @return
	 */
	public String getDocumentoRootFolder() {
		return getProperty("documentRoot", "docroot");
	}

	/**
	 * Return the name of the mail server. For instance, 'smtp.mycompany.com'
	 *
	 * @return
	 */
	public String getMailHost() {
		return getProperty("mail.host", "smtp.mycompany.com");
	}

	/**
	 * Return the port number of the smtp server (by default, 25)
	 *
	 * @return
	 */
	public String getMailPort() {
		return getProperty("mail.port", "25");
	}

	/**
	 * Return the username of an smtp user
	 *
	 * @return
	 */
	public String getMailUsername() {
		return getProperty("mail.username", "admin@mycompany.com");
	}

	/**
	 * Return the username of an smtp user
	 *
	 * @return
	 */
	public String getMailPassword() {
		return getProperty("mail.password", "admin@mycompany.com_password");
	}

	/**
	 * Return if the smtp server requiures authorization
	 *
	 * @return
	 */
	public String getMailRequiresAuth() {
		return getProperty("mail.requiresAuth", "true");
	}

	/**
	 * Return the absolute path of the folder docroot (external files) with
	 * ending /
	 *
	 * @return
	 */
	public String getDocumentRootPath() {
		String path = getDefault().getConfigDir();

		if (!path.endsWith("\\") || !path.endsWith("/")) {
			path += File.separator;
		}

		path += getDefault().getDocumentoRootFolder();

		if (!path.endsWith("\\") || !path.endsWith("/")) {
			path += File.separator;
		}

		return path;

	}

	public String getSecurityMatrix() {
		return getProperty("securityMatrix", "securityConfiguration-test.xml");
	}

	public boolean isForceCompileReports() {
		return "1".equals(getProperty("forceCompileReports", "0").trim()) ? true
				: false;
	}

	public String getCommissioningMailSubject() {
		return getProperty("mail.commissioning.mailSubject",
				"Ficha de Proyecto del OSE");
	}

	public String getCommissioningMailBody() {
		return getProperty("mail.commissioning.mailBody",
				"Se envia adjunta la ficha de proyecto.\n\nUn saludo");
	}

	public int getDaysToExpirePassword() {
		try {
			int daysToExpirePassword = Integer.parseInt(this.getProperty(
					"daysToExpirePassword", "365"));

			if (daysToExpirePassword <= 0) {
				throw new IllegalArgumentException();
			}

			return daysToExpirePassword;
		} catch (Exception ex) {
			log.warn("daysToExpirePassword must be a positive number, setting default interval.");

			return 365; // Por defecto cada 90 dias
		}
	}

	@Override
	public String getMailNotificationContractSubject() {
        return getProperty("mail.notification.contract.subject", "Finalizacion de Contrato Proximamente");
    }

	@Override
    public Collection<String> getMailNotificationContractSendTo() {
        final String mailSendTo =getProperty("mail.notification.contract.sendTo", "admin@mycompany.com");
        return Arrays.asList(mailSendTo.split(" "));
    }

	@Override
    public boolean getEnabledSendMail() {
        return Boolean.valueOf(getProperty("enabledSendMail", "false"));
    }

	public Boolean getReadOnlyBill() {
		return Boolean.valueOf(getProperty("readOnlyBill", "true"));
	}

	public int getMaxHoursByContract() {
		return Integer.parseInt(getProperty("maxHoursByContract", ""));
	}

	public BigDecimal getDefaultIRPF() {
		return new BigDecimal(getProperty("defaultIRPF", "15"));
	}

	public String getNoEvidenceInActivityMailSubject() {
		return getProperty("mail.activity.noEvidenceSubject", "Falta evidencia");
	}

	public String getNoEvidenceInActivityMailBody() {
		return getProperty("mail.activity.noEvidenceMailBody", "Es necesario adjuntar al menos una imagen cada 7 días como evidencia de tus actividades.\n\nUn saludo");
	}
}
