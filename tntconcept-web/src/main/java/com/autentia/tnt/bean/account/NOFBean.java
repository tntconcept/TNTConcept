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

package com.autentia.tnt.bean.account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.autentia.tnt.bean.BaseBean;
import com.autentia.tnt.businessobject.AccountEntry;
import com.autentia.tnt.businessobject.AccountEntryType;
import com.autentia.tnt.businessobject.Bill;
import com.autentia.tnt.businessobject.BillState;
import com.autentia.tnt.businessobject.BillType;
import com.autentia.tnt.businessobject.CreditTitle;
import com.autentia.tnt.businessobject.CreditTitleState;
import com.autentia.tnt.businessobject.CreditTitleType;
import com.autentia.tnt.businessobject.Frequency;
import com.autentia.tnt.businessobject.PeriodicalAccountEntry;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.BillSearch;
import com.autentia.tnt.dao.search.CreditTitleSearch;
import com.autentia.tnt.dao.search.PeriodicalAccountEntrySearch;
import com.autentia.tnt.manager.account.PeriodicalAccountEntryManager;
import com.autentia.tnt.manager.billing.BillManager;
import com.autentia.tnt.manager.billing.CreditTitleManager;
import com.autentia.tnt.util.ConfigurationUtil;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;

/**
 * UI bean for NOF objects.
 * 
 * @author Autentia
 */
public class NOFBean extends BaseBean {

	/** Serial Version UID */
	private static final long serialVersionUID = -3165007831286521687L;
	
	  
	/** Active Bill object */
	private Bill bill;
	
	/** Active PeriodicalAccountEntry object */
	private PeriodicalAccountEntry periodicalAccountEntry;
	
	/** Default sort column in periodical account entry list*/
	private String sortColumnPeriodicalAccount = "date";
	
	/** Active search object */
	private BillSearch billSearch = new BillSearch();
	
	/** Active search object */
	private PeriodicalAccountEntrySearch periodicalAccountSearch = new PeriodicalAccountEntrySearch();
	  
	private CreditTitleSearch creditTitleSearch = new CreditTitleSearch();
	
	/** Bill Manager */
	private static BillManager billManager = BillManager.getDefault();
	
	private static CreditTitleManager creditTitleManager = CreditTitleManager.getDefault();
	
	/** Periodical Account Manager */
	private static PeriodicalAccountEntryManager periodicalAccountManager = PeriodicalAccountEntryManager.getDefault();
	  
	/** Total bills totalPrevisionIncomes */
	private BigDecimal totalPrevisionIncomes;
	
	/** Total Account Periodical*/
	private BigDecimal totalPeriodicalAccount;

	/** End value to calculate the NOF */
	private Calendar endDate;
	
	/**
	 * List bills. Order depends on Faces parameter sort.
	 * @return the list of all bills sorted by requested criterion
	 */
	public List<GenericNOF> getAllPrevisionIncomes() {
		List<GenericNOF> allPrevisionIncomes = new ArrayList<GenericNOF>();
		for (GenericNOF genericNOF: getAllNOFIssuedBills()) {
			allPrevisionIncomes.add(genericNOF);
		}
		
		for (GenericNOF genericNOF: getAllNOFReceivedCreditTitle()) {
			allPrevisionIncomes.add(genericNOF);
		}

		if (allPrevisionIncomes != null) {
			calcTotalsBill(allPrevisionIncomes);
		}

		return allPrevisionIncomes;
	}
	
	
	public List<GenericNOF> getAllNOFIssuedBills() {
		/**** Facturas emitidas impagadas que vencen en el periodo ****/
		/**** Facturas emitidas impagadas ya vencidas ****/
		/**** Facturas recibidas impagadas que vencen en el periodo ****/
		/**** Facturas recibidas impagadas ya vencidas ****/
		int years = ConfigurationUtil.getDefault().getYearsBackSearchNotPaidBillsNOF();
		List<BillType> billTypes = new ArrayList<BillType>();
		billTypes.add(BillType.ISSUED);
		billSearch.setBillTypes(billTypes);
		billSearch.setState(BillState.EMITTED);
		billSearch.setStartEndBillDate(calculateStartEndByPassedYear(years).getTime());
		List<Bill> total = billManager.getAllEntities(billSearch, new SortCriteria( "creationDate", true ), 
												 new GregorianCalendar(1900,1,1).getTime() , getEndDate());
		return convertFromBillToGenericNOF(total);
	}


	private Calendar calculateStartEndByPassedYear(int years) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -years);
		return cal;
	}
	
	public List<GenericNOF> getAllNOFReceivedBills() {
		/**** Facturas emitidas impagadas que vencen en el periodo ****/
		/**** Facturas emitidas impagadas ya vencidas ****/
		/**** Facturas recibidas impagadas que vencen en el periodo ****/
		/**** Facturas recibidas impagadas ya vencidas ****/
		List<BillType> billTypes = new ArrayList<BillType>();
		billTypes.add(BillType.RECIEVED);
		billSearch.setBillTypes(billTypes);
		billSearch.setState(BillState.EMITTED);
		List<Bill> total = billManager.getAllEntities(billSearch, new SortCriteria( "creationDate", true ), 
												 new GregorianCalendar(1900,1,1).getTime() , getEndDate());

		return convertFromBillToGenericNOF(total);
	}
	
	/**
	 * Recover a list of issued credit titles that has to be paid in the period 
	 */
	public List<GenericNOF> getAllNOFIssuedCreditTitle() {
		creditTitleSearch.setCreditTitleType(CreditTitleType.ISSUED);
		creditTitleSearch.setCreditTitleState(CreditTitleState.EMITTED);
		List <CreditTitle> creditTitles = creditTitleManager.getAllEntities(creditTitleSearch, new SortCriteria( "issueDate", true ), 
												 new GregorianCalendar(1900,1,1).getTime() , getEndDate());
		
		return convertFromCreditTitleToGenericNOF(creditTitles);
	}
	
	/**
	 * Recover a list of received credit titles that has to be paid in the period 
	 */
	public List<GenericNOF> getAllNOFReceivedCreditTitle() {
		creditTitleSearch.setCreditTitleType(CreditTitleType.RECEIVED);
		creditTitleSearch.setCreditTitleState(CreditTitleState.EMITTED);
		List <CreditTitle> creditTitles = creditTitleManager.getAllEntities(creditTitleSearch, new SortCriteria( "issueDate", true ), 
												 new GregorianCalendar(1900,1,1).getTime() , getEndDate());
		
		return convertFromCreditTitleToGenericNOF(creditTitles);
	}
	
	/**
	 * Converts from list of credit titles to a list of GeneticNOF objects to be seen in the page
	 */
	public List <GenericNOF> convertFromCreditTitleToGenericNOF (List<CreditTitle> creditTitles) {
		List <GenericNOF> genericNOFList = new ArrayList<GenericNOF> (creditTitles.size());
		for (CreditTitle creditTitle: creditTitles) {
			GenericNOF genericNOF = new GenericNOF();
			genericNOF.setEndDate(creditTitle.getExpirationDate());
			
			if (creditTitle.getType().equals(CreditTitleType.ISSUED)) {
				genericNOF.setBillType(BillType.ISSUED);
			}
			else {
				genericNOF.setBillType(BillType.RECIEVED);
			}
			genericNOF.setType(NOFType.CREDIT_TITLE);
			genericNOF.setProvider(creditTitle.getOrganization().getName());
			genericNOF.setNumber(creditTitle.getNumber());
			genericNOF.setOrganization(creditTitle.getOrganization());
			genericNOF.setDescription(creditTitle.getObservations());			
						
			// [bugzilla:2638] se resta el montante de las facturas pagadas del pagare del montante del propio pagare
			BigDecimal amountInBills = new BigDecimal(0);
			for (Bill bill: creditTitle.getBills()) {
				if (bill.getState().equals(BillState.PAID)) {
					amountInBills = amountInBills.add(bill.getAmount());
				}
			}
			
			genericNOF.setTotal(creditTitle.getAmount().subtract(amountInBills));
			genericNOF.setExpired(creditTitle.getExpirationDate().before(new Date()));
			if (!genericNOF.getTotal().toString().equals("0.00")) { 
				genericNOFList.add(genericNOF);
			}
		}
		return genericNOFList;
	}
	
	/**
	 * Converts from list of bills to a list of GeneticNOF objects to be seen in the page
	 */
	public List <GenericNOF> convertFromBillToGenericNOF (List<Bill> bills) {
		List <GenericNOF> genericNOFList = new ArrayList<GenericNOF> (bills.size());
		for (Bill bill: bills) {
			// no se incluyen las facturas que estan en pagares
			if (bill.getState() != BillState.IN_CREDITTITLE) 
			{
				GenericNOF genericNOF = new GenericNOF();
				genericNOF.setEndDate(bill.getExpiration());
				genericNOF.setType(NOFType.BILL);
				if (bill.getBillType().equals(BillType.RECIEVED)) {
					genericNOF.setProvider(bill.getProvider().getName());
				}
				genericNOF.setDescription(bill.getName());
				genericNOF.setBillType(bill.getBillType());
				genericNOF.setNumber(bill.getNumber());
				genericNOF.setOrganization(bill.getProject().getClient());
				
				// [bugzilla:2638] se resta el montante de los asientos de la factura del montante de la propia factura
/*				
				BigDecimal amountInAccountEntries = new BigDecimal(0);
				for (AccountEntry accountEntry: bill.getEntries()) {					
					amountInAccountEntries = amountInAccountEntries.add(accountEntry.getAmount());
				}
				// si la factura es recibida se deberia sumar el valor de los asientos por cómo se presentan
				// los datos en el jsp. Para no hacer logica de sumar o restar, en caso de ser recibida el valor
				// de los asientos se multiplica por -1 y se resta, que es equivalente a sumar.
				if (bill.getBillType().equals(BillType.RECIEVED)) {
					amountInAccountEntries = amountInAccountEntries.multiply(new BigDecimal(-1));
				}
				genericNOF.setTotal(bill.getTotal().subtract(amountInAccountEntries));
*/				
				genericNOF.setTotal(bill.getUnpaid());
				
				
				

				// logica de presentacion de datos en el jsp
				if (bill.getBillType().equals(BillType.RECIEVED)) 
				{
					genericNOF.setTotal(genericNOF.getTotal().multiply(new BigDecimal(-1)));
				}
				genericNOF.setExpired(bill.getExpiration().before(new Date()));	
				
				// finalmente se añade si se debe o se nos debe algo
				if (!genericNOF.getTotal().toString().equals("0.00")) { 
					genericNOFList.add(genericNOF);
				}
			}
		}
		return genericNOFList;
	}

	public List<GenericNOF> getAllPeriodicalAccountEntry() {
		List<GenericNOF> allPeriodicalAccountEntry = new ArrayList<GenericNOF>();		
		for (GenericNOF genericNOF: getAllNOFPeriodicalAccountEntry()) {
			allPeriodicalAccountEntry.add(genericNOF);
		}
	
		for (GenericNOF genericNOF: getAllNOFIssuedCreditTitle()) {
			allPeriodicalAccountEntry.add(genericNOF);
		}
		
		for (GenericNOF genericNOF: getAllNOFReceivedBills()) {
			allPeriodicalAccountEntry.add(genericNOF);
		}		
		
		if (allPeriodicalAccountEntry != null) {
			calcTotalsPeriodicalAccount(allPeriodicalAccountEntry);
		}

		return allPeriodicalAccountEntry;
	}
	
	
	
	/**
	 * List periodicalAccountEntrys. Order depends on Faces parameter sort.
	 * @return the list of all periodicalAccountEntrys sorted by requested criterion
	 */
	public List<GenericNOF> getAllNOFPeriodicalAccountEntry() {

		/**** Movimientos periódicos que vencen en el periodo ****/
		List<PeriodicalAccountEntry> res = periodicalAccountManager.getEntities(periodicalAccountSearch, 
				   						   new SortCriteria( sortColumnPeriodicalAccount, true ), getActualDate(), getEndDate());
		
		/* Si el criterio de ordenación es por fecha se debe ordenar otra vez la lista ya que las fechas
		 *  no coinciden con las de la base de datos */
		if (sortColumnPeriodicalAccount.equals("date")) {
			orderListByDate(res);
		}
		
		for (PeriodicalAccountEntry periodicalAccountEntry: res) {
			periodicalAccountEntry.setAmount(periodicalAccountEntry.getAmount().multiply(new BigDecimal(-1)));
		}
		
		return convertFromPeriodicalAccountEntryToGenericNOF(res);
	}
	
	/**
	 * Converts from list of periodical account entries to a list of GeneticNOF objects to be seen in the page
	 */
	public List <GenericNOF> convertFromPeriodicalAccountEntryToGenericNOF (List<PeriodicalAccountEntry> periodicalAccountEntries) {
		List <GenericNOF> genericNOFList = new ArrayList<GenericNOF> (periodicalAccountEntries.size());
		for (PeriodicalAccountEntry periodicalAccountEntry: periodicalAccountEntries) {
			GenericNOF genericNOF = new GenericNOF();
			genericNOF.setEndDate(periodicalAccountEntry.getDate());
			genericNOF.setType(NOFType.PERIODICAL_ACCOUNT_ENTRY);
			genericNOF.setProvider(periodicalAccountEntry.getAccount().getName());
			
			genericNOF.setBillType(BillType.RECIEVED);
			genericNOF.setOrganization(periodicalAccountEntry.getOrganization());
			genericNOF.setDescription(periodicalAccountEntry.getConcept());
			genericNOF.setFrecuency(periodicalAccountEntry.getFrequency().getName());
			genericNOF.setTotal(periodicalAccountEntry.getAmount().multiply(new BigDecimal(-1)));
			genericNOF.setPeriodicalTypeDescription(periodicalAccountEntry.getType().getName());
			genericNOFList.add(genericNOF);
		}
		return genericNOFList;
	}

	/**
	 * Order list by date.
	 * @param res the list of PeriodicaAccountEntry
	 */
	private void orderListByDate(List<PeriodicalAccountEntry> res) {
		
		PeriodicalAccountEntry temp = null;
		boolean sorted = false;
		
		for ( int i = 0; i < res.size() && !sorted; i++ ) {
			sorted = true;
			
			for( int j = res.size()-1; j > i; j-- ) {
				
				PeriodicalAccountEntry first = null;
				PeriodicalAccountEntry second = null;
				
				// Ascending order
				first = res.get(j);
				second = res.get(j-1);
				
				if ( first.getDate().getTime() < second.getDate().getTime() ) {
					temp = res.get(j);
					res.set(j, res.get(j-1));
					res.set(j-1, temp);
					sorted = false;
				}
			}
		}
	}

	/**
	 * Calculate the total bill costs
	 * @param res
	 */
	private void calcTotalsBill(List<GenericNOF> res) {

		BigDecimal value = new BigDecimal(0);
		for (GenericNOF elem : res) {
//			if (elem.getBillType() ==  BillType.ISSUED)
				value = value.add(elem.getTotal());
//			else value = value.add(elem.getTotal().negate());
		}
		
		setTotalPrevisionIncomes(value);
	}
	
	/**
	 * Calculate the total periodical account costs
	 * @param res
	 */
	private void calcTotalsPeriodicalAccount(List<GenericNOF> res) {

		BigDecimal value = new BigDecimal(0);
		for (GenericNOF elem : res) {
			value = value.add(elem.getTotal());
		}		
		setTotalPeriodicalAccount(value);
	}
	
	/**
	 * @return the totalPrevisionIncomes
	 */
	public BigDecimal getTotalPrevisionIncomes() {
		return totalPrevisionIncomes;
	}
	
	/**
	 * @param totalPrevisionIncomes the totalPrevisionIncomes to set
	 */
	public void setTotalPrevisionIncomes(BigDecimal totalPrevisionIncomes) {
		this.totalPrevisionIncomes = totalPrevisionIncomes;
	}
	
	/**
	 * @return the totalPeriodicalAccount
	 */
	public BigDecimal getTotalPeriodicalAccount() {
		return totalPeriodicalAccount;
	}
	
	/**
	 * @param totalPeriodicalAccount the totalPeriodicalAccount to set
	 */
	public void setTotalPeriodicalAccount(BigDecimal totalPeriodicalAccount) {
		this.totalPeriodicalAccount = totalPeriodicalAccount;
	}
	
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		// The first value is calculated adding 1 month to actual date
		if (endDate == null) {
			endDate = Calendar.getInstance();
			endDate.add(Calendar.MONTH, 1);
		}
		return endDate.getTime();
	}

	/**
	 * @return the actualDate
	 */
	public Date getActualDate() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate.setTime(endDate);
	}
	
	/**
	 * @return the sortColumnPeriodicalAccount
	 */
	public String getSortColumnPeriodicalAccount() {
		return sortColumnPeriodicalAccount;
	}

	/**
	 * @param sortColumnPeriodicalAccount the sortColumnPeriodicalAccount to set
	 */
	public void setSortColumnPeriodicalAccount(String sortColumnPeriodicalAccount) {
		this.sortColumnPeriodicalAccount = sortColumnPeriodicalAccount;
	}

	public Date getCreationDate() {
		return bill.getCreationDate();
	}

	public String getNumber() {
		return bill.getNumber();
	}

	public String getName() {
		return bill.getName();
	}

	public String getObservations() {
		return bill.getObservations();
	}
	
	public String getConcept() {
	    return periodicalAccountEntry.getConcept();
	}
	        
    public Date getDate() {
	    return periodicalAccountEntry.getDate();
    }
    
    public BigDecimal getAmount() {
	    return periodicalAccountEntry.getAmount();
	}
   
    public AccountEntryType getType() {
    	return periodicalAccountEntry.getType();
    }
		  
    public Frequency getFrequency() {
	    return periodicalAccountEntry.getFrequency();
	}
	
}
