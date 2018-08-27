package com.support.schedular.validator;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.support.schedular.constant.ApplicationConstant;
import com.support.schedular.entities.EngineerEntity;

@Component
public class MaxOneShiftAllocationValidtor implements RuleChecker {
	

	@Override
	public boolean validate(EngineerEntity pickedEngineer, Date currentDate, 
			Map<Date, Map<List<EngineerEntity>, Integer>> dataEngineerMap ,ApplicationConstant constant) {

		// Rule :An engineer can do at most one half day shift in a day
		if(!(dataEngineerMap.get(currentDate) != null && dataEngineerMap.get(currentDate).keySet().iterator().next().contains(pickedEngineer))){
			return true;
		}
		return false;
	}

}
