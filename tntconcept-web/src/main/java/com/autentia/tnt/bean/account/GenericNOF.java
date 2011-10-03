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
import java.util.Date;
import com.autentia.tnt.businessobject.BillType;
import com.autentia.tnt.businessobject.Organization;

/**
 * This class allow us to show the values of NOF independently of his origin, that can be a <code>Bill</code>,
 * a <code>PeriodicalAccountEntry</code>, or a <code>CreditTitle</code>
 */
public class GenericNOF {

	private Date endDate;
	private NOFType type;
	private String number;
	private String provider;
	private Organization organization;
	private String description;
	private String frecuency;
	private BigDecimal total;
	private boolean expired;
	private String periodicalTypeDescription;
	private BillType billType;
	
	public BillType getBillType() {
		return billType;
	}
	public void setBillType(BillType billType) {
		this.billType = billType;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public NOFType getType() {
		return type;
	}
	public void setType(NOFType type) {
		this.type = type;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFrecuency() {
		return frecuency;
	}
	public void setFrecuency(String frecuency) {
		this.frecuency = frecuency;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public boolean isExpired() {
		return expired;
	}
	public void setExpired(boolean expired) {
		this.expired = expired;
	}
	public String getPeriodicalTypeDescription() {
		return periodicalTypeDescription;
	}
	public void setPeriodicalTypeDescription(String periodicalTypeDescription) {
		this.periodicalTypeDescription = periodicalTypeDescription;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
}
