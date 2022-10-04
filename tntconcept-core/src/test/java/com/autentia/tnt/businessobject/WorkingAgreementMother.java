package com.autentia.tnt.businessobject;

import org.apache.commons.lang.math.RandomUtils;

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

}
