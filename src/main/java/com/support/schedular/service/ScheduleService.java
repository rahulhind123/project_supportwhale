package com.support.schedular.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.support.schedular.constant.ApplicationConstant;
import com.support.schedular.entities.EngineerEntity;
import com.support.schedular.exception.ScheduleGenerationException;
import com.support.schedular.repository.EngineerRepository;
import com.support.schedular.util.ApplicationUtilities;
import com.support.schedular.validator.MaxOneShiftAllocationValidtor;
import com.support.schedular.validator.MaxShiftAllocationValidator;
import com.support.schedular.validator.NoConsecutiveAllocationValidator;
import com.support.schedular.validator.RuleChecker;

@Service
public class ScheduleService {

	public static final Logger logger = LoggerFactory.getLogger(ScheduleService.class);

	@Autowired
	private ApplicationConstant applicationConstant;
	
	@Autowired
	private EngineerRepository engineerRespository;

	// assigned userDate to local variable
	private Date fromDate , toDate;
	private List<Date> workingDates ;
	
	private Map<Date, Map<List<EngineerEntity>, Integer>> dataEngineerMap = null;

	public Map<Date, Map<List<EngineerEntity>, Integer>> generateSchedules(String startDate, String endDate) throws Exception{

		   try{
				fromDate = ApplicationUtilities.parseDate(startDate);
				toDate = ApplicationUtilities.parseDate(endDate);
		   }catch(ParseException e){
			   throw new RuntimeException("InValid date format");
		   }
		
			
			int workingInterval = (applicationConstant.getMax_engineer_allocation() * applicationConstant.getMax_engineer_shift())/applicationConstant.getMax_day_shift();
			logger.info("Working Interval {} ",workingInterval);
			
			workingDates = ApplicationUtilities.calculateWorkingDays(fromDate, toDate ,workingInterval);
			dataEngineerMap = new TreeMap<Date, Map<List<EngineerEntity>, Integer>>();
			
			
			logger.info("+++++++++++++++++++++++++++++++++++++");
			logger.info("Available Working days : {}", workingDates);
			logger.info("+++++++++++++++++++++++++++++++++++++");
			// Adding all Validator Rules 

			/*
			 * Now we need to find engineer corresponding to working days. Also keep
			 * in mind max shift allocation for one engineer is 2 in 2 weeks
			 */
			
			List<EngineerEntity> engineersPool = this.engineerRespository.findAll();
			if(engineersPool.size() <1){
				 throw new RuntimeException(" No Engineer Records available");
			}
			
			for (Date workDate : workingDates) {
				dataEngineerMap = this.validateEngineer(engineersPool, dataEngineerMap ,workDate,this.getRuleCheckerList()) ;
			}

			logger.info("dataEngineerMap {} ", dataEngineerMap);
			if(dataEngineerMap == null){
				throw new ScheduleGenerationException(" Unable to schedule ||| May be NO engineer in the records");
			}
			
			return dataEngineerMap;
		
		
	}
	
	public Map<Date, Map<List<EngineerEntity>, Integer>> validateEngineer(List<EngineerEntity> engineersPool , Map<Date, Map<List<EngineerEntity>, Integer>> dataEngineerMap ,
			Date currentDate,List<RuleChecker> ruleList) throws Exception{
	  
	   do{
		  dataEngineerMap = this.pickAndValidateEngineer(engineersPool,currentDate, dataEngineerMap, ruleList);

	   }while(dataEngineerMap.get(currentDate) != null && dataEngineerMap.get(currentDate).keySet().iterator().next().size()< applicationConstant.getMax_day_shift());
		
	  return dataEngineerMap; 
	}
	
	
	public Map<Date, Map<List<EngineerEntity>, Integer>> pickAndValidateEngineer(List<EngineerEntity> engineersPool ,Date currentDate,  Map<Date, Map<List<EngineerEntity>, Integer>>  dataEngineerMap ,
			List<RuleChecker> ruleList) throws Exception{
	
		// Picked random selected engineer
		EngineerEntity pickedEngineer = this.getRandom(engineersPool);
		
		// We need to fetch non validate engineer .. List not contains any duplicate engineer so we used set here
		Set<EngineerEntity> invalidEngineerList = new HashSet<EngineerEntity>();
			
        ruleList.forEach(r -> {
        	if(!r.validate(pickedEngineer, currentDate, dataEngineerMap,applicationConstant)){
        		 invalidEngineerList.add(pickedEngineer);
            }  
        });
        
        if(invalidEngineerList.size() == 0 ){
         	// update schedule properties
        	int shift = pickedEngineer.getShiftNumber();
        	pickedEngineer.setShiftNumber(shift + 1);
        	
	    	//update dataEngineerMap 
	    	Map<List<EngineerEntity>, Integer>  enginerShtMap = new HashMap<List<EngineerEntity>, Integer>();
	 
	    	
	    	if(dataEngineerMap.get(currentDate) == null){
	    	   	List<EngineerEntity> engineerList = new ArrayList<>();
		    	engineerList.add(pickedEngineer);
	    		enginerShtMap.put(engineerList, 1);
	    	}else{
	    		List<EngineerEntity> tempList = dataEngineerMap.get(currentDate).keySet().iterator().next();
	    		tempList.add(pickedEngineer);
	    		enginerShtMap.put(tempList, dataEngineerMap.get(currentDate).values().size()+1);
	    	}
          
	    	dataEngineerMap.put(currentDate, enginerShtMap);
        }else{
        	this.pickAndValidateEngineer(engineersPool, currentDate, dataEngineerMap, ruleList);
        }
        
        return dataEngineerMap;
	}	
	
	public EngineerEntity getRandom(List<EngineerEntity> engineersPool ){
          Random random = new Random();
          EngineerEntity selectedEngineer = engineersPool.get(random.nextInt(engineersPool.size()));
          for(EngineerEntity eng : engineersPool){
        	  if(eng.getShiftNumber() < selectedEngineer.getShiftNumber()){
        		  selectedEngineer =eng; 
        	  }
          }
	      return selectedEngineer ;
		  
	}
	
	public List<RuleChecker> getRuleCheckerList(){
		
		List<RuleChecker> ruleList = new ArrayList<RuleChecker>();
		ruleList.add(new MaxOneShiftAllocationValidtor());
		ruleList.add(new MaxShiftAllocationValidator());
		ruleList.add(new NoConsecutiveAllocationValidator());
		
		return ruleList;
	}
}
