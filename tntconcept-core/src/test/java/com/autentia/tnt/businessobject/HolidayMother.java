package com.autentia.tnt.businessobject;

import java.util.Date;

public final class HolidayMother {
    public static Holiday random(
            Date date
    ) {
        final Holiday holiday = new Holiday();
        holiday.setDate(date);
        return holiday;
    }
}
