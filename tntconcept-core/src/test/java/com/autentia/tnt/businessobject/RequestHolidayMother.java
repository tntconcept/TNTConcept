package com.autentia.tnt.businessobject;

import java.util.Date;

public final class RequestHolidayMother {

    public static RequestHoliday random(
            Date beginDate,
            Date endDate
    ) {
        final RequestHoliday requestHoliday = new RequestHoliday();
        requestHoliday.setBeginDate(beginDate);
        requestHoliday.setFinalDate(endDate);
        return requestHoliday;
    }

}
