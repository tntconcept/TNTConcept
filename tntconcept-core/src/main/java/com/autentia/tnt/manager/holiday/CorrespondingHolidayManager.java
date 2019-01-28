package com.autentia.tnt.manager.holiday;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.businessobject.Holiday;
import com.autentia.tnt.businessobject.User;
import com.autentia.tnt.dao.search.HolidaySearch;
import com.autentia.tnt.util.SpringUtils;

public class CorrespondingHolidayManager {
	
	/** Logger */
	private static final Log log = LogFactory.getLog(UserHolidaysStateManager.class);
	  
	public static CorrespondingHolidayManager getDefault()
	{
		return (CorrespondingHolidayManager)SpringUtils.getSpringBean("managerCorrespondingHoliday");
	}
	
	/** 
	* Empty constructor needed by CGLIB (Spring AOP)
	*/
	protected CorrespondingHolidayManager()
	{
	}

	public List<Holiday> calcCorrespondingHolidays(List<Holiday> holidays, User user, int yearOfCalculation){
    	
    	LocalDate hiringDate =  parseDate(user.getStartDate());
    	
    	holidays = holidays.stream().filter(holiday -> parseDate(holiday.getDate()).getYear() == yearOfCalculation).collect(Collectors.toList());
    	
    	int notCorrespondingDays = 0;
    	
    	if(hiringDate.getYear() == yearOfCalculation) {
        	
        	List<Holiday> compensationDays = holidays.stream().filter(holiday -> holiday.isCompensation()).collect(Collectors.toList());
      
        	List<LocalDate> firstWorkingDays = getFirstNWorkingDays(compensationDays.size(), yearOfCalculation);
        	
        	int i;
        	
        	for(i = 0; i < firstWorkingDays.size(); i++) {
        		if(hiringDate.equals(firstWorkingDays.get(i))) {
        			break;
        		}
        	}    	
        	notCorrespondingDays = i;
        	
        	return removeCompensationDays(holidays, compensationDays, notCorrespondingDays);
    		
    	}
    	else {
    		return holidays;
    	}
    }
    
    private List<LocalDate> getFirstNWorkingDays(int numberOfDays, int currentYear){
    	
    	List<LocalDate> workingDays = new ArrayList<LocalDate>();
    	
    	HolidayManager holidayManager = HolidayManager.getDefault();
    	
    	Date januaryMin = new GregorianCalendar(currentYear, Calendar.JANUARY, 01, 0, 0).getTime();
    	Date januaryMax = new GregorianCalendar(currentYear, Calendar.JANUARY, 31, 23, 59).getTime();
    	
    	HolidaySearch holidaySearch = new HolidaySearch();
		holidaySearch.setStartDate(januaryMin);
		holidaySearch.setEndDate(januaryMax);
    	
    	List<Holiday> januaryHolidays = holidayManager.getAllEntities(holidaySearch, null);
    	
    	for (LocalDate day = LocalDate.of(currentYear, Month.JANUARY, 1); numberOfDays != 0; day = day.plusDays(1))
    	{
    		boolean isHoliday = false;
    		    		
    		for(Holiday holiday : januaryHolidays) {
    			if(parseDate(holiday.getDate()).equals(day)) {
    				isHoliday = true;
    				break;
    			}
    		}
    		
    		if(isWeekend(day) || isHoliday){
    			continue;
    		}
    		else {
    			workingDays.add(day);
    			numberOfDays--;
    		}
    	}
    	return workingDays;
    }
    
    private boolean isWeekend(LocalDate date) {
		final DayOfWeek dayOfWeek = date.getDayOfWeek();
		final Set<DayOfWeek> weekend = EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
		return weekend.contains(dayOfWeek);
	}
    
    private LocalDate parseDate(Date date) {
    	return date.toInstant()
  		      .atZone(ZoneId.systemDefault())
  		      .toLocalDate();
    }
    
    private List<Holiday> removeCompensationDays(List<Holiday> holidays, List<Holiday> compensationDays, int notCorresponding){
    	
    	List<Holiday> correspondingCompensation = compensationDays.stream().sorted((c1, c2) -> c2.getDate().compareTo(c1.getDate())).limit(compensationDays.size()-notCorresponding).collect(Collectors.toList());
    	
    	holidays.removeIf(holiday -> holiday.isCompensation() && !correspondingCompensation.contains(holiday));
    	
    	return holidays;
    }
}
