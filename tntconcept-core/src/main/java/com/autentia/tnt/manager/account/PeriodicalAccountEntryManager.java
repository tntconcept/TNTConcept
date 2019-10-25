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

package com.autentia.tnt.manager.account;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.businessobject.AccountEntry;
import com.autentia.tnt.businessobject.PeriodicalAccountEntry;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.hibernate.AccountEntryDAO;
import com.autentia.tnt.dao.hibernate.PeriodicalAccountEntryDAO;
import com.autentia.tnt.dao.search.PeriodicalAccountEntrySearch;
import com.autentia.tnt.util.BeanUtils;
import com.autentia.tnt.util.SpringUtils;

public class PeriodicalAccountEntryManager {

	public PeriodicalAccountEntry copy(int id) {		
		PeriodicalAccountEntry periodicalAccountEntry = new PeriodicalAccountEntry();
		AccountEntryDAO accountEntryDAO = new AccountEntryDAO();	   
		AccountEntry copyAccountEntry = accountEntryDAO.loadById(id);
/*
		 	periodicalAccountEntry.setConcept(copyAccountEntry.getConcept());
		   	periodicalAccountEntry.setAccount(copyAccountEntry.getAccount());
		   	periodicalAccountEntry.setDate(copyAccountEntry.getDate());
		   	periodicalAccountEntry.setObservations(copyAccountEntry.getObservations());
			periodicalAccountEntry.setType(copyAccountEntry.getType());
			periodicalAccountEntry.setAmount(copyAccountEntry.getAmount());
*/
			BeanUtils.copyTransferObject(copyAccountEntry,periodicalAccountEntry);
			
		return periodicalAccountEntry;
	}

  /**
   * List periodicalAccountEntrys between start and end dates
   * @param search search filter to apply
   * @param sort sorting criteria
   * @param start the start date 
   * @param end the end date
   * @return the list of all periodicalAccountEntrys sorted by requested criterion
   */
  public List<PeriodicalAccountEntry> getEntities(PeriodicalAccountEntrySearch search, SortCriteria sort, Date start, Date end){
	  List<PeriodicalAccountEntry> res = periodicalAccountEntryDAO.search( search, sort );
	  List<PeriodicalAccountEntry> list = new ArrayList<PeriodicalAccountEntry>();
	  Calendar calendar = Calendar.getInstance();
	  boolean outOfRange;
	  PeriodicalAccountEntry newPeriodicalAccountEntry = null;
	  
	  //calendar.add( Calendar.MONTH, 0 );
	  
	  // Recorremos la lista de PeriodicalAccountEntry
	  for ( PeriodicalAccountEntry item : res ) {
		  // Metemos en el calendar la fecha del item
		  calendar.setTime( item.getDate() );
		  outOfRange = false;
		  
		  /* Comparamos la fecha del item con el rango de fechas seleccionado por el usuario. Mientras no nos salgamos del rango
		     se calculan tantos pagos como entren en el periodo que va entre las fechas start y end*/
		  while ( !outOfRange ) {

			  // Si la fecha está dentro del rango de fechas seleccionado se añade a la lista con la fecha calculada del próximo pago 
			  if ( (calendar.getTimeInMillis() >= start.getTime()) && (calendar.getTimeInMillis() <= end.getTime()) ) {
				  // Creamos un nuevo PeriodicalAccountEntry
				  newPeriodicalAccountEntry = createNewPeriodicalAccountEntry( item, new Date(calendar.getTimeInMillis()) );
				  
				  // Añadimos a la lista
				  list.add( newPeriodicalAccountEntry );
				  
				  // Si la frecuencia es 'ocasional' terminamos con este item
				  if (item.getFrequency().getMonths() == 0) {
					  outOfRange = true;
				  } else {
					  // Sumamos la frecuencia del pago a la fecha del item
					  calendar.add( Calendar.MONTH, item.getFrequency().getMonths() );
				  }
			  } else if ( calendar.getTimeInMillis() > end.getTime() ) {
				  // Si la fecha calculada se ha salido de rango terminamos con este item
				  outOfRange = true;
			  } else {
				  //Sumamos la frecuencia del pago a la fecha del item
				  calendar.add( Calendar.MONTH, item.getFrequency().getMonths() );
			  }
		  }
	  }
	  
	  return list;
  }
  
  /**
   * Create a new PeriodicalAccountEntry
   * @param periodicalAccountEntry
   * @param nextDate
   * @return
   */
  private PeriodicalAccountEntry createNewPeriodicalAccountEntry( PeriodicalAccountEntry periodicalAccountEntry, Date nextDate ) {
	  PeriodicalAccountEntry newPeriodicalAccountEntry = new PeriodicalAccountEntry();
	  
	  newPeriodicalAccountEntry.setDate( nextDate );
	  newPeriodicalAccountEntry.setAccount( periodicalAccountEntry.getAccount() );
	  newPeriodicalAccountEntry.setAmount( periodicalAccountEntry.getAmount() );
	  newPeriodicalAccountEntry.setConcept( periodicalAccountEntry.getConcept() );
	  newPeriodicalAccountEntry.setFrequency( periodicalAccountEntry.getFrequency() );
	  newPeriodicalAccountEntry.setOrganization( periodicalAccountEntry.getOrganization() );
	  newPeriodicalAccountEntry.setObservations( periodicalAccountEntry.getObservations() );
	  newPeriodicalAccountEntry.setRise( periodicalAccountEntry.getRise() );
	  newPeriodicalAccountEntry.setType( periodicalAccountEntry.getType() );
	  
	  return newPeriodicalAccountEntry;
  }
	  
/* PeriodicalAccountEntry - generated by stajanov (do not edit/delete) */


  /** Logger */
  private static final Log log = LogFactory.getLog(PeriodicalAccountEntryManager.class);

  /** PeriodicalAccountEntry DAO **/
  private PeriodicalAccountEntryDAO periodicalAccountEntryDAO;

  /**
   * Get default PeriodicalAccountEntryManager as defined in Spring's configuration file.
   * @return the default singleton PeriodicalAccountEntryManager
   */
  public static PeriodicalAccountEntryManager getDefault()
  {
    return (PeriodicalAccountEntryManager)SpringUtils.getSpringBean("managerPeriodicalAccountEntry");
  }

  /** 
   * Empty constructor needed by CGLIB (Spring AOP)
   */
  protected PeriodicalAccountEntryManager()
  {
  }
	
  /** 
   * Default constructor 
   * @deprecated do not construct managers alone: use Spring's declared beans
   */
  public PeriodicalAccountEntryManager( PeriodicalAccountEntryDAO periodicalAccountEntryDAO )
  {
    this.periodicalAccountEntryDAO = periodicalAccountEntryDAO;
  }

  /**
   * List periodicalAccountEntrys. 
   * @param search search filter to apply
   * @param sort sorting criteria
   * @return the list of all periodicalAccountEntrys sorted by requested criterion
   */
  public List<PeriodicalAccountEntry> getAllEntities(PeriodicalAccountEntrySearch search, SortCriteria sort){
    return periodicalAccountEntryDAO.search( search, sort );
  }
  
  // Getters to list possible values of related entities
      
    
  /**
   * Get periodicalAccountEntry by primary key.
   * @return periodicalAccountEntry selected by id.
   */
  public PeriodicalAccountEntry getEntityById(int id){
    return periodicalAccountEntryDAO.loadById(id);
  }
	
  /**
   * Insert periodicalAccountEntry. 
   */
  public void insertEntity(PeriodicalAccountEntry periodicalAccountEntry) {
    periodicalAccountEntryDAO.insert(periodicalAccountEntry);
  }
	 
  /**
   * Update periodicalAccountEntry. 
   */
  public void updateEntity(PeriodicalAccountEntry periodicalAccountEntry) {
    periodicalAccountEntryDAO.update(periodicalAccountEntry);
  }

  /**
   * Delete periodicalAccountEntry. 
   */
  public void deleteEntity(PeriodicalAccountEntry periodicalAccountEntry) {
    periodicalAccountEntryDAO.delete(periodicalAccountEntry);
  }
/* PeriodicalAccountEntry - generated by stajanov (do not edit/delete) */

	
}
