package com.autentia.tnt.test.utils;

import com.autentia.tnt.util.DateUtils;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class DateUtilsTest {

    @Test
    public void shouldReturnFirstDayOfMonth() {

        final Date testDate = new GregorianCalendar(2019, Calendar.JULY, 17).getTime();

        final Date result = DateUtils.getFirstDayOfMonth(testDate);

        final Date expectedDate = new GregorianCalendar(2019, Calendar.JULY, 1).getTime();
        assertThat(result, is(expectedDate));
    }

    @Test
    public void shouldReturnLastDayOfMonth() {
        final Date testDate = new GregorianCalendar(2019, Calendar.JULY, 17).getTime();

        final Date result = DateUtils.getLastDayOfMonth(testDate);

        Date expectedDate = new GregorianCalendar(2019, Calendar.JULY, 31, 23, 59, 59).getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(expectedDate);
        calendar.set(Calendar.MILLISECOND, calendar.getMaximum(Calendar.MILLISECOND));
        expectedDate = calendar.getTime();
        assertThat(result, is(expectedDate));
    }

    @Test
    public void shouldReturnFirstDayOfYear() {

        final Date testDate = new GregorianCalendar(2019, Calendar.JULY, 17).getTime();

        final Date result = DateUtils.getFirstDayOfYear(testDate);

        final Date expectedDate = new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime();
        assertThat(result, is(expectedDate));
    }

    @Test
    public void shouldReturnLastDayOfYear() {

        final Date testDate = new GregorianCalendar(2019, Calendar.JULY, 17).getTime();

        final Date result = DateUtils.getLastDayOfYear(testDate);

        Date expectedDate = new GregorianCalendar(2019, Calendar.DECEMBER, 31, 23, 59, 59).getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(expectedDate);
        calendar.set(Calendar.MILLISECOND, calendar.getMaximum(Calendar.MILLISECOND));
        expectedDate = calendar.getTime();
        assertThat(result, is(expectedDate));
    }

}
