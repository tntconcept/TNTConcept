package com.autentia.tnt.businessobject;

import com.autentia.tnt.util.DateMother;
import org.apache.commons.lang.math.RandomUtils;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class WorkingAgreementMother {

    public static WorkingAgreement random(
            Set<WorkingAgreementTerms> terms
    ) {
        final WorkingAgreement workingAgreement = new WorkingAgreement();
        workingAgreement.setId(RandomUtils.nextInt());
        workingAgreement.setTerms(terms);
        return workingAgreement;
    }

    public static WorkingAgreement sample() {
        final WorkingAgreement workingAgreement = new WorkingAgreement();
        final long annualWorkingTime = Duration.ofHours(1765).toMinutes();
        workingAgreement.setId(RandomUtils.nextInt());
        workingAgreement.setTerms(
                new HashSet<>(
                        Arrays.asList(
                                WorkingAgreementTermsMother.random(annualWorkingTime, 22, DateMother.from(1970, 1, 1)),
                                WorkingAgreementTermsMother.random(100000, 22, DateMother.from(2020, 1, 1)),
                                WorkingAgreementTermsMother.random(annualWorkingTime, 23, DateMother.from(2022, 7, 1))
                        )
                )
        );
        return workingAgreement;
    }

}
