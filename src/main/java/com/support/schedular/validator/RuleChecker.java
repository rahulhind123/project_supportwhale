package com.support.schedular.validator;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.support.schedular.constant.ApplicationConstant;
import com.support.schedular.entities.EngineerEntity;

public interface RuleChecker {
	
	public boolean validate(final EngineerEntity testEngineer ,Date currentDate, Map<Date, Map<List<EngineerEntity>, Integer>>  dataEngineerMap,ApplicationConstant constant);

}
