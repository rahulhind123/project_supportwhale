package com.support.schedular.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationUtilities {
	
	public static final Logger logger = LoggerFactory.getLogger(ApplicationUtilities.class);
	
	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public static Date parseDate(String userDate) throws ParseException{
		return sdf.parse(userDate);
	}
	
    public static String formatDate(Date date) {
        return sdf.format(date);
    }
	
	public static List<Date> calculateWorkingDays(Date fromDate , Date toDate , int workingInterval) {
		
		List<Date> workingDates = new ArrayList<Date>();
		Calendar startDateCalendar = Calendar.getInstance();
		startDateCalendar.setTime(fromDate);
		
		startDateCalendar.add(Calendar.DAY_OF_MONTH,-1);
		
		Calendar endDateCalendar = Calendar.getInstance();
		endDateCalendar.setTime(toDate);
		
		
		
		while(startDateCalendar.getTimeInMillis() < endDateCalendar.getTimeInMillis() && workingDates.size() < workingInterval ){
			startDateCalendar.add(Calendar.DAY_OF_MONTH, 1);
			if(startDateCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startDateCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY){
				workingDates.add(startDateCalendar.getTime());
			}
		}
		
		logger.info("workingDates {}" , workingDates);
		return workingDates;
	}

}
