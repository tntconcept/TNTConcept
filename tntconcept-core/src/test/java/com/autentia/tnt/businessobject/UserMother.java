package com.autentia.tnt.businessobject;

import org.apache.commons.lang.math.RandomUtils;

import java.util.Date;

public final class UserMother {
    public static User random(
            WorkingAgreement workingAgreement,
            Date startDate
    ) {
        final User user = new User();
        user.setId(RandomUtils.nextInt());
        user.setAgreement(workingAgreement);
        user.setStartDate(startDate);
        return user;
    }

}
