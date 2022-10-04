package com.autentia.tnt.businessobject;

import org.apache.commons.lang.math.RandomUtils;

import java.util.Date;

public final class WorkingAgreementTermsMother {

    public static WorkingAgreementTerms random(
            long annualWorkingTime,
            int vacations,
            Date effectiveFrom
    ) {
        final WorkingAgreementTerms workingAgreementTerms = new WorkingAgreementTerms();
        workingAgreementTerms.setId(RandomUtils.nextInt());
        workingAgreementTerms.setAnnualWorkingTime((int) annualWorkingTime);
        workingAgreementTerms.setVacation(vacations);
        workingAgreementTerms.setEffectiveFrom(effectiveFrom);
        return workingAgreementTerms;
    }
}
