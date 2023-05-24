package com.autentia.tnt.test.utils;

import java.util.Calendar;
import java.util.Date;

public final class DateMother {

    public static Date from(int year, int month, int dayOfWeek, int hours, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, dayOfWeek, hours, minutes);
        return calendar.getTime();
    }

    public static Date from(int year, int month, int dayOfWeek) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, dayOfWeek);
        return calendar.getTime();
    }

}
