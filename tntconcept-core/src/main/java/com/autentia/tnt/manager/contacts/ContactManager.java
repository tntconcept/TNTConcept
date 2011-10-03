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

package com.autentia.tnt.manager.contacts;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.businessobject.Contact;
import com.autentia.tnt.businessobject.ContactInfo;
import com.autentia.tnt.businessobject.Department;
import com.autentia.tnt.businessobject.Organization;
import com.autentia.tnt.businessobject.Position;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.hibernate.ContactDAO;
import com.autentia.tnt.dao.hibernate.DepartmentDAO;
import com.autentia.tnt.dao.hibernate.PositionDAO;
import com.autentia.tnt.dao.search.AdvancedSearchContactSearch;
import com.autentia.tnt.dao.search.ContactSearch;
import com.autentia.tnt.dao.search.DepartmentSearch;
import com.autentia.tnt.dao.search.PositionSearch;
import com.autentia.tnt.manager.admin.DepartmentManager;
import com.autentia.tnt.manager.contacts.advancedsearch.ContactPosition;
import com.autentia.tnt.manager.security.AuthenticationManager;
import com.autentia.tnt.util.SpringUtils;
import java.util.ArrayList;




public class ContactManager {


/* contact - generated by stajanov (do not edit/delete) */

    

  /** Logger */
  private static final Log log = LogFactory.getLog(ContactManager.class);

  /** Contact DAO **/
  private ContactDAO contactDAO;

  	private AuthenticationManager authManager;

	public AuthenticationManager getAuthManager() {
		return authManager;
	}

	public void setAuthManager(AuthenticationManager authManager) {
		this.authManager = authManager;
	}
    
  /**
   * Get default ContactManager as defined in Spring's configuration file.
   * @return the default singleton ContactManager
   */
  public static ContactManager getDefault()
  {
    return (ContactManager)SpringUtils.getSpringBean("managerContact");
  }

  /** 
   * Empty constructor needed by CGLIB (Spring AOP)
   */
  protected ContactManager()
  {
  }
	
  /** 
   * Default constructor 
   * @deprecated do not construct managers alone: use Spring's declared beans
   */
  public ContactManager( ContactDAO contactDAO )
  {
    this.contactDAO = contactDAO;
  }

  /**
   * List contacts. 
   * @param search search filter to apply
   * @param sort sorting criteria
   * @return the list of all contacts sorted by requested criterion
   */
  public List<Contact> getAllEntities(ContactSearch search, SortCriteria sort){
    return contactDAO.search( search, sort );
  }
  
  /**
   * Get contact by primary key.
   * @return contact selected by id.
   */
  public Contact getEntityById(int id){
    final Contact contact = contactDAO.getById(id);
		// contact.initChanges();
    return contact;
  }

  /**
   * Insert contact. 
   */
  public void insertEntity(Contact contact) {
    contactDAO.insert(contact);
  }
	 
  /**
   * Update contact. 
   */
  public void updateEntity(Contact contact) {
		// final Contact changes = contact.getChanges();

    contactDAO.update(contact);

		// final Contact contactHibSession = contactDAO.getById(contact.getId());
		// contactHibSession.setChanges(changes);
		// this.trackEntityChanges(contactHibSession);
  }

  /**
   * Delete contact. 
   */
  public void deleteEntity(Contact contact) {
    contactDAO.delete(contact);
  }

  public Contact addOrganizationDepartmentOrPositionByDefault (final Contact contact, final Organization organization, final Department department, final Position position) {
	  try {
		  if (organization != null) {

			  	Department selectedDepartment = null;
			  	Position selectedPosition = null;
			  
				if (department == null) {
					final DepartmentSearch departmentSearch = new DepartmentSearch();
					departmentSearch.setName("Indefinido");
					selectedDepartment = DepartmentDAO.getDefault().search(departmentSearch, new SortCriteria("id")).get(0);
				} else {
					selectedDepartment = department;
				}

				if (position == null) {
					final PositionSearch positionSearch = new PositionSearch();
					positionSearch.setName("Indefinido");
					selectedPosition = PositionDAO.getDefault().search(positionSearch, new SortCriteria("id")).get(0);
				} else {
					selectedPosition = position;					
				} 
	
				final ContactInfo contactInfo = new ContactInfo();
				contactInfo.setContact(contact);
				contactInfo.setOrganization(organization);
				contactInfo.setDepartment(selectedDepartment);				
				contactInfo.setPosition(selectedPosition);
				contact.addContactInfo(contactInfo);
                this.updateEntity(contact);
		  }
	  } catch (Exception e) {
		  
	  }
	  return contact;
  }

    
}
