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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.acegisecurity.GrantedAuthority;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.custom.navmenu.NavigationMenuItem;

import com.autentia.tnt.businessobject.AccountEntry;
import com.autentia.tnt.businessobject.AccountEntryType;
import com.autentia.tnt.businessobject.Activity;
import com.autentia.tnt.businessobject.AdminHoliday;
import com.autentia.tnt.businessobject.Bill;
import com.autentia.tnt.businessobject.Book;
import com.autentia.tnt.businessobject.BulletinBoard;
import com.autentia.tnt.businessobject.BulletinBoardCategory;
import com.autentia.tnt.businessobject.Collaborator;
import com.autentia.tnt.businessobject.Commissioning;
import com.autentia.tnt.businessobject.CompanyState;
import com.autentia.tnt.businessobject.Contact;
import com.autentia.tnt.businessobject.ContractType;
import com.autentia.tnt.businessobject.CreditTitle;
import com.autentia.tnt.businessobject.Department;
import com.autentia.tnt.businessobject.FinancialRatio;
import com.autentia.tnt.businessobject.Holiday;
import com.autentia.tnt.businessobject.Idea;
import com.autentia.tnt.businessobject.Interaction;
import com.autentia.tnt.businessobject.InteractionType;
import com.autentia.tnt.businessobject.Inventary;
import com.autentia.tnt.businessobject.Magazine;
import com.autentia.tnt.businessobject.Objective;
import com.autentia.tnt.businessobject.Occupation;
import com.autentia.tnt.businessobject.Offer;
import com.autentia.tnt.businessobject.OfferRejectReason;
import com.autentia.tnt.businessobject.Organization;
import com.autentia.tnt.businessobject.OrganizationISOCategory;
import com.autentia.tnt.businessobject.OrganizationType;
import com.autentia.tnt.businessobject.PeriodicalAccountEntry;
import com.autentia.tnt.businessobject.Position;
import com.autentia.tnt.businessobject.Project;
import com.autentia.tnt.businessobject.Publication;
import com.autentia.tnt.businessobject.RequestHoliday;
import com.autentia.tnt.businessobject.Setting;
import com.autentia.tnt.businessobject.Tag;
import com.autentia.tnt.businessobject.Tutorial;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.businessobject.UserCategory;
import com.autentia.tnt.businessobject.WorkingAgreement;
import com.autentia.tnt.manager.security.AuthenticationManager;
import com.autentia.tnt.manager.security.Permission;
import com.autentia.tnt.manager.security.Principal;
import com.autentia.tnt.util.FacesUtils;
import com.autentia.tnt.util.SpringUtils;

/**
* Dynamic Menu definition bean
* @author German
*/
public class DynMenuBean extends BaseBean implements Serializable {

	private static final long			serialVersionUID	= 1L;

	/** Log system */
	private static final Log			log					= LogFactory.getLog(DynMenuBean.class);

	/** Language resources */
	private static final ResourceBundle	msg					= ResourceBundle
																	.getBundle("com.autentia.tnt.resources.messages");
	/** List of NavigationItems */
	private List<NavigationMenuItem>	navItems			= null;

	private Locale locale = FacesUtils.getViewLocale();
	/**
	 * Constructor
	 */
	public DynMenuBean() {

	}

	/**
	 * Inicializa las prefencias necesarias del usuario, obtidas de base de datos,
	 * y que han de ser genéricas para toda l apalicacion (por ejemplo el locale).
	 * 
	 * Se pone en este punto de generación del menu porque se realiza el mismo para cualquier
	 * pantalla.
	 * 
	 */
	public void initUserPreferences() {
		
		FacesUtils.setActualLoggedLocale();		
	}
	
	public List<NavigationMenuItem> getNavItems() {
		initUserPreferences();
		return generateMenu();
	}

	/**
	 * Generates a list of navigation items, representating entries of the dynamic menu bar
	 * 
	 * @return
	 */
	private List<NavigationMenuItem> generateMenu() {

		if (navItems == null || !locale.equals(FacesUtils.getViewLocale())) {
			locale = FacesUtils.getViewLocale();
			
			if (log.isDebugEnabled()) {
				log.debug("generating dynamic menu");
			}

			Principal creds = null;
			boolean isAnonymous = true;

			try {
				creds = SpringUtils.getPrincipal();
				isAnonymous = false;
			} catch (ClassCastException e) {
				// veamos si es un usuario anonimo 
				if ("anonymousUser".equals(SpringUtils.getPrincipalAsObject())) {
					isAnonymous = true;
				} else {
					throw new RuntimeException(e);
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

			navItems = new ArrayList<NavigationMenuItem>();

			NavigationMenuItem item = null;

			if (!isAnonymous) {
				// menu items for NON anonymous users

				if (log.isDebugEnabled()) {
					log.debug("user is NOT anonymous");
				}

				// file menu
				if ((item = createMenuItem(creds, null, "file", null)) != null) {
					addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(Setting.class), "settings","/img/configure.png")));
					addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(User.class), "#{dynMenuBean.exit}","/img/exit.png")));				
					
					addItem2Items(navItems,item);
				}

				
				// administration menu
				if ((item = createMenuItem(creds, null, "admin", null)) != null) {
					
					
					addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(User.class), "users",
							null)));
					addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(UserCategory.class),
							"userCategorys", null)));
					addItem2Item(item,(createMenuItem(creds, null, "changePassword", "/img/key.png")));
					addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(Department.class),
							"departments", null)));
				
					addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(WorkingAgreement.class),
							"workingAgreements", null)));
					
					
					
					addItem2Items(navItems,item);
				}

				// master tables menu
				if ((item = createMenuItem(creds, null, "masterTables", null)) != null) {
					addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(AccountEntryType.class),
							"accountEntryTypes", null)));
					addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(OrganizationType.class),
							"organizationTypes", null)));
					addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(InteractionType.class),
							"interactionTypes", null)));
					addItem2Item(item,(createMenuItem(creds, Permission
							.Entity_Menu(OrganizationISOCategory.class),
							"organizationISOCategorys", null)));
					addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(ContractType.class),
							"contractTypes", null)));
					addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(Magazine.class),
							"magazines", null)));
					addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(OfferRejectReason.class),
							"offerRejectReasons", null)));
										
					addItem2Items(navItems,item);
				}

				// billing
				if ((item = createMenuItem(creds, null, "billing", null)) != null) {
					addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(Bill.class), "bills",
							null)));
					addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(CreditTitle.class), "creditTitles",
							null)));
					addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(AccountEntryType.class),
							"accounts", null)));
					addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(AccountEntry.class),
							"accountEntrys", null)));
					addItem2Item(item,(createMenuItem(creds, Permission
							.Entity_Menu(PeriodicalAccountEntry.class), "periodicalAccountEntrys",
							null)));
					addItem2Item(item,(createMenuItem(creds, Permission.Action_NOF, "nof", null)));
					addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(FinancialRatio.class),
							"financialRatios", null)));
					addItem2Items(navItems,item);
				}

				// contacts
				if ((item = createMenuItem(creds, null, "contacts", null)) != null) {
					addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(Organization.class),
							"organizations", null)));
					addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(Position.class),
							"positions", null)));
					addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(Interaction.class),
							"interactions", null)));
					addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(Contact.class),
							"contacts", null)));
					addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(Offer.class), "offers",
							null)));
					addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(Project.class),
							"projects", null)));
					addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(Tag.class), "tags", null)));
                    addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(Collaborator.class), "collaborators", null)));
					addItem2Items(navItems,item);
				}

				// commissionings
				if ((item = createMenuItem(creds, null, "commissionings", null)) != null) {
					addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(Commissioning.class),
							"commissionings", null)));
					addItem2Items(navItems,item);					
				}
				
				// quality
				if ((item = createMenuItem(creds, null, "quality", null)) != null) {
					addItem2Item(item,(createMenuItem(creds, Permission.Action_ListQualityDocuments,
							"qualityDocuments", null)));
					addItem2Items(navItems,item);
				}

				// bulletin
				if ((item = createMenuItem(creds, null, "bulletin", null)) != null) {
					addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(BulletinBoard.class),
							"bulletinBoards", null)));
					addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(CompanyState.class),
							"companyStates", null)));
					addItem2Item(item,(createMenuItem(creds, Permission
							.Entity_Menu(BulletinBoardCategory.class), "bulletinBoardCategorys",
							null)));
					addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(Idea.class), "ideas",
							null)));
					addItem2Items(navItems,item);
				}

				// activity
				if ((item = createMenuItem(creds, null, "activity", null)) != null) {
					addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(Activity.class),
							"activitys", null)));
					//addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(ExternalActivity.class),
					//		"externalActivities", null)));
					addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(Objective.class),
							"objectives", null)));
					addItem2Item(item,(createMenuItem(creds, Permission.Action_GlobalHoursReport, "globalHoursReport", null)));
					
//addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(Occupation.class), "jpivotTest", null)));
					
					addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(Occupation.class),
							"availabilitys", null)));
					addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(Occupation.class),
							"occupations", null)));
					addItem2Items(navItems,item);
				}

				// reports tables menu
				if ((item = createMenuItem(creds, null, "reports", null)) != null) {
					addItem2Item(item,(createMenuItem(creds, Permission.Action_GeneralReports,
							"generalReports", null)));
					addItem2Item(item,(createMenuItem(creds, Permission.Action_BitacoreReports,
							"bitacoreReports", null)));
					addItem2Item(item,(createMenuItem(creds, Permission.Action_BillReports, "billReports",
							null)));
					addItem2Item(item,(createMenuItem(creds, Permission.Action_ProjectReports,
							"projectReports", null)));
					addItem2Item(item,(createMenuItem(creds, Permission.Action_InteractionReports,
							"interactionReports", null)));
					addItem2Item(item,(createMenuItem(creds, Permission.Action_OrganizationReports,
							"organizationReports", null)));
					addItem2Item(item,(createMenuItem(creds, Permission.Action_OfferReports, "offerReports",
							null)));
					addItem2Item(item,(createMenuItem(creds, Permission.Action_CommissioningReports, "commissioningReports",
							null)));
					addItem2Item(item,(createMenuItem(creds, Permission.Action_OwnReports, "ownReports",
							null)));
					addItem2Item(item,(createMenuItem(creds, Permission.Action_PersonalReports,
							"personalReports", null)));
					addItem2Items(navItems,item);
				}

				// publish
				if ((item = createMenuItem(creds, null, "publish", null)) != null) {
					addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(Tutorial.class),
							"tutorials", null)));
					addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(Publication.class),
							"publications", null)));
					addItem2Items(navItems,item);
				}

				// holiday
				if ((item = createMenuItem(creds, null, "holiday", null)) != null) {
					
					
					addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(Holiday.class),
							"holidays", null)));
					addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(RequestHoliday.class),
							"requestHolidays", null)));
					addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(AdminHoliday.class),
							"adminHolidays", null)));
					
					addItem2Item(item,(createMenuItem(creds, Permission.Action_UserHolidaysState, "userHolidayState", null)));
					addItem2Item(item,(createMenuItem(creds, Permission.Action_MyHolidays, "#{userHolidaysStateBean.myHolidays}", null)));
					
					addItem2Items(navItems,item);
				}

				// utils
				if ((item = createMenuItem(creds, null, "utils", null)) != null) {
					addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(Book.class), "books",
							null)));
					addItem2Item(item,(createMenuItem(creds, Permission.Entity_Menu(Inventary.class),
							"inventarys", null)));
					addItem2Items(navItems,item);
				}

			} else {
				// menu items for anonymous users
				if (log.isDebugEnabled()) {
					log.debug("user is NOT anonymous");
				}
			}

		}

		return navItems;
	}
	
	private void addItem2Items(List<NavigationMenuItem> items, NavigationMenuItem item)
	{
		if(item==null || item.getNavigationMenuItems()==null || item.getNavigationMenuItems().length<=0) {
			return;
		}
		
		items.add(item);
	}
	private void addItem2Item(NavigationMenuItem padre, NavigationMenuItem hijo) {
		
		if(hijo!=null)
			padre.add(hijo);
		
		
		
	}
	
	/**
	 * Generates a navigation menu item. This item could be nested into others navigation menu items
	 * 
	 * @param creds
	 * @param neededRole
	 * @param cmd
	 * @param iconUrl
	 * @return
	 */
	private NavigationMenuItem createMenuItem(Principal creds, GrantedAuthority neededRole,
			String cmd, String iconUrl) {

		NavigationMenuItem item = null;

		if (neededRole == null || creds.hasAuthority(neededRole)) {
			String text = cmd;
			if (text.indexOf("#{") >= 0) {
				text = text.substring(text.indexOf('.') + 1, text.indexOf('}'));
			}
			try {
				//text = msg.getString("menu." + text);
				text = FacesUtils.getMessage("menu." + text);
			} catch (MissingResourceException e) {
				text = "MISSING : " + text + " : MISSING";
			}

			if (iconUrl == null) {
				item = new NavigationMenuItem(text, cmd);
			} else {
				item = new NavigationMenuItem(text, cmd, iconUrl, false);
			}

		}

		return item;
	}
	
	//XXX llamar a este metodo para limpiar la sesion.. (desde donde???)
	/**
	 * Clear admin beans from session
	private void clearSession() {
		// Remove admin beans from session
	    FacesUtils.removeFromSession("userBean");
	    FacesUtils.removeFromSession("userCategoryBean");
	    FacesUtils.removeFromSession("inventaryBean");
	    FacesUtils.removeFromSession("projectBean");
	    FacesUtils.removeFromSession("ideaBean");
	    FacesUtils.removeFromSession("changePasswordBean");
	    FacesUtils.removeFromSession("userBean");
	    FacesUtils.removeFromSession("userCategoryBean");
	    FacesUtils.removeFromSession("inventaryBean");
	    FacesUtils.removeFromSession("projectBean");
	    FacesUtils.removeFromSession("projectRoleBean");
	    FacesUtils.removeFromSession("ideaBean");
	    FacesUtils.removeFromSession("changePasswordBean");
	    FacesUtils.removeFromSession("departmentBean");
	    FacesUtils.removeFromSession("contractTypeBean");
	    
	    // Remove biling beans from session
	    FacesUtils.removeFromSession("billBean");    
	    FacesUtils.removeFromSession("accountBean");
	    FacesUtils.removeFromSession("accountEntryTypeBean");
	    FacesUtils.removeFromSession("accountEntryBean");
	    FacesUtils.removeFromSession("periodicalAccountEntryBean");
	    FacesUtils.removeFromSession("nofBean");
	    FacesUtils.removeFromSession("financialRatioBean");
	    
	    // Remove contacts beans from session
	    FacesUtils.removeFromSession("organizationBean");
	    FacesUtils.removeFromSession("contactBean");
	    FacesUtils.removeFromSession("interactionBean");
	    FacesUtils.removeFromSession("interactionTypeBean");
	    FacesUtils.removeFromSession("organizationTypeBean");
	    FacesUtils.removeFromSession("organizationISOCategoryBean");
	    FacesUtils.removeFromSession("offerRejectReasonBean");
	    FacesUtils.removeFromSession("offerBean");
	    
	    // Remove quality beans from session
	    FacesUtils.removeFromSession("qualityDocumentBean");
	    
	    // Remove bulletin beans from session
	    FacesUtils.removeFromSession("bulletinBoardBean");
	    FacesUtils.removeFromSession("companypathBean");
	    FacesUtils.removeFromSession("bulletinBoardCategoryBean");
	    
	    // Remove activity beans from session
	    FacesUtils.removeFromSession("activityBean");
	    FacesUtils.removeFromSession("objectiveBean");
	    
	    // Remove activity beans from session
	    FacesUtils.removeFromSession("activityReportBean");
	    FacesUtils.removeFromSession("generalReportBean");
	    FacesUtils.removeFromSession("billReportBean");
	    FacesUtils.removeFromSession("interactionReportBean");
	    FacesUtils.removeFromSession("projectReportBean");
	    FacesUtils.removeFromSession("organizationReportBean");
	    FacesUtils.removeFromSession("personalReportBean");
	    FacesUtils.removeFromSession("offerReportBean");
	    
	    // Remove tutorial beans from session
	    FacesUtils.removeFromSession("tutorialBean");
	    FacesUtils.removeFromSession("publicationBean");
	    FacesUtils.removeFromSession("magazineBean");
	    
	    // Remove util beans from session
	    FacesUtils.removeFromSession("sendMailBean");
	}
	 */
	
	
	public String exit() {
		HttpSession sessionContext = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		if(sessionContext!=null) {
			sessionContext.invalidate();			
		}
				
		return "exit";
		
	}
}
