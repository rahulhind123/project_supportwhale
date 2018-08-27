package com.support.schedular.validator;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.support.schedular.constant.ApplicationConstant;
import com.support.schedular.entities.EngineerEntity;

@Component
public class NoConsecutiveAllocationValidator implements RuleChecker {

	@Override
	public boolean validate(final EngineerEntity pickedEngineer, Date currentDate, Map<Date, Map<List<EngineerEntity>, Integer>> dataEngineerMap,
			ApplicationConstant constant) {
		
		//Rule : Check engineer allocation not on consecutive days and on the same days
		Date yesterday = this.getPreviousDate(currentDate);
		if(!(dataEngineerMap.get(yesterday) != null && dataEngineerMap.get(yesterday).keySet().iterator().next().contains(pickedEngineer))){
				return true;
		}
		return false;
	}
	
	public Date getPreviousDate(Date currentDate){

		Calendar c = Calendar.getInstance();
		c.setTime(currentDate);
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		if(dayOfWeek == Calendar.MONDAY){
			return new Date(currentDate.getTime() -3*24*60*60*1000);
		}else{
			return new Date(currentDate.getTime() -24*60*60*1000);
		}
		
	}

}
