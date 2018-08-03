package com.support.schedular.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.support.schedular.model.UserDateModel;
import com.support.schedular.service.ScheduleService;

@Controller
public class SupportFateApiController {
	
	public static final Logger logger = LoggerFactory.getLogger(SupportFateApiController.class);
	
    @Autowired
    private ScheduleService schedulerService;
    
	@GetMapping(value="/")
	public String mainPage(){
		return "main";
	}
	
	@PostMapping(value="/generate_schedule" )
	public String generateSchedules(UserDateModel userModel , Model model){
		
		logger.info(" Entered Start Date {} and End Date {} ", userModel.getStartDate() , userModel.getEndDate()) ;
		try{
		
			model.addAttribute("schedulesDataMap", schedulerService.generateSchedules(userModel.getStartDate(), userModel.getEndDate()));
			model.addAttribute("userModel", userModel);
		}catch(Exception e){
			model.addAttribute("error",e.getMessage());
		}
		logger.info(" Model -> {} ", model) ;
		return "main";
	}

}
