package com.support.schedular.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EngineerSelectionCriteria {
	
	@Autowired
	private MaxOneShiftAllocationValidtor maxOneShiftAllocationValidator;
	
	@Autowired
	private MaxShiftAllocationValidator maxShiftAllocationValidator;
	
	@Autowired
	private NoConsecutiveAllocationValidator noConsecutiveAllocationValidator;
	
	public EngineerSelectionCriteria(MaxOneShiftAllocationValidtor maxOneShiftAllocationValidator ,
			MaxShiftAllocationValidator maxShiftAllocationValidator ,
			NoConsecutiveAllocationValidator noConsecutiveAllocationValidator){
		this.maxOneShiftAllocationValidator = maxOneShiftAllocationValidator;
		this.maxShiftAllocationValidator = maxShiftAllocationValidator;
		this.noConsecutiveAllocationValidator = noConsecutiveAllocationValidator;
	}
	
	
	private List<RuleChecker> ruleList = new ArrayList<RuleChecker>();
	
	
	public List<RuleChecker> getRuleCheckerList(){

		ruleList.add(maxOneShiftAllocationValidator);
		ruleList.add(maxShiftAllocationValidator);
		ruleList.add(noConsecutiveAllocationValidator);
		
		return ruleList;
	}

}
