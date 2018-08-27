package com.support.schedular.validator;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.support.schedular.constant.ApplicationConstant;
import com.support.schedular.entities.EngineerEntity;

@Component
public class MaxShiftAllocationValidator implements RuleChecker {
	
	@Override
	public boolean validate(final EngineerEntity pickedEngineer, Date currentDate,
			Map<Date, Map<List<EngineerEntity>, Integer>> dataEngineerMap,ApplicationConstant constant) {

		// Rule : Each engineer should have completed one whole day of support in any 2 week period
		if(!(pickedEngineer.getShiftNumber() == constant.getMax_engineer_shift())){
				return true;
		}
		return false;
	}

}
