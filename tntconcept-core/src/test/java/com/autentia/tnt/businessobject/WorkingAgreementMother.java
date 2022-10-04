package com.autentia.tnt.businessobject;

import org.apache.commons.lang.math.RandomUtils;

import java.time.Duration;
import java.util.Set;

public final class WorkingAgreementMother {

    public static WorkingAgreement random(
            Long workingTime,
            int holidays,
            Set<WorkingAgreementTerms> terms
    ) {
        final WorkingAgreement workingAgreement = new WorkingAgreement();
        workingAgreement.setId(RandomUtils.nextInt());
        workingAgreement.setYearDuration((int) Duration.ofHours(workingTime).toMinutes());
        workingAgreement.setHolidays(holidays);
        workingAgreement.setTerms(terms);
        return workingAgreement;
    }

}
