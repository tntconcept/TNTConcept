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

package com.autentia.tnt.businessobject;


import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class WorkingAgreementTerms implements Serializable {

    private Integer id;
    private Date effectiveFrom;
    private Integer vacation;
    private Integer annualWorkingTime;
    private WorkingAgreement workingAgreement;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getEffectiveFrom() {
        return effectiveFrom;
    }

    public void setEffectiveFrom(Date effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }

    public int getVacation() {
        return vacation;
    }

    public void setVacation(int vacation) {
        this.vacation = vacation;
    }

    public int getAnnualWorkingTime() {
        return annualWorkingTime;
    }

    public void setAnnualWorkingTime(int annualWorkingTime) {
        this.annualWorkingTime = annualWorkingTime;
    }

    public void setVacation(Integer vacation) {
        this.vacation = vacation;
    }

    public void setAnnualWorkingTime(Integer annualWorkingTime) {
        this.annualWorkingTime = annualWorkingTime;
    }

    public WorkingAgreement getWorkingAgreement() {
        return workingAgreement;
    }

    public void setWorkingAgreement(WorkingAgreement workingAgreement) {
        this.workingAgreement = workingAgreement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorkingAgreementTerms)) return false;
        WorkingAgreementTerms that = (WorkingAgreementTerms) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getEffectiveFrom(), that.getEffectiveFrom()) && Objects.equals(getVacation(), that.getVacation()) && Objects.equals(getAnnualWorkingTime(), that.getAnnualWorkingTime()) && Objects.equals(getWorkingAgreement(), that.getWorkingAgreement());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEffectiveFrom(), getVacation(), getAnnualWorkingTime(), getWorkingAgreement());
    }
}
