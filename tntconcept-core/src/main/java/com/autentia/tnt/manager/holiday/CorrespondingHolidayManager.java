package com.autentia.tnt.manager.holiday;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.businessobject.Holiday;
import com.autentia.tnt.businessobject.User;
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

	public List<Holiday> calcCorrespondingHolidays(List<Holiday> holidays, User user){
    	
    	LocalDate hiringDate =  parseDate(user.getStartDate());
    	
    	int currentYear = (holidays.size() > 0) ? parseDate(holidays.get(0).getDate()).getYear() : LocalDate.now().getYear();
    	
    	List<Holiday> compensationDays = holidays.stream().filter(holiday -> holiday.isCompensation()).collect(Collectors.toList());
    	
    	List<LocalDate> firstWorkingDays = getFirstWorkingDays(holidays, compensationDays.size(), currentYear);
    	
    	int notCorrespondingDays = 0;
    	int i;
    	
    	for(i = 0; i < firstWorkingDays.size(); i++) {
    		if(hiringDate.equals(firstWorkingDays.get(i))) {
    			break;
    		}
    	}    	
    	notCorrespondingDays = (hiringDate.getYear() == currentYear) ? i : 0;
    	
    	return removeCompensationDays(holidays, compensationDays, notCorrespondingDays);
    }
    
    private List<LocalDate> getFirstWorkingDays(List<Holiday> holidays, int numberOfDays, int currentYear){
    	
    	List<LocalDate> workingDays = new ArrayList<LocalDate>();
    	
    	for (LocalDate day = LocalDate.of(currentYear, Month.JANUARY, 1); numberOfDays != 0; day = day.plusDays(1))
    	{
    		boolean isHoliday = false;
    		    		
    		for(Holiday holiday : holidays) {
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
    	
    	Collections.reverse(compensationDays);
    	
    	List<Holiday> correspondingCompensation = compensationDays.stream().limit(compensationDays.size()-notCorresponding).collect(Collectors.toList());
    	
    	holidays.removeIf(holiday -> holiday.isCompensation() && !correspondingCompensation.contains(holiday));
    	
    	return holidays;
    }
}
