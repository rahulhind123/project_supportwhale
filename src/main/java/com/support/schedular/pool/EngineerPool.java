package com.support.schedular.pool;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.support.schedular.entities.EngineerEntity;
import com.support.schedular.repository.EngineerRepository;

@Component
public class EngineerPool {
	
	public static final Logger logger = LoggerFactory.getLogger(EngineerPool.class);
	
	@Autowired
	private EngineerRepository engineerRepository;
	
	public EngineerPool(EngineerRepository engineerRepository){
		this.engineerRepository = engineerRepository;
	}
	
	private List<EngineerEntity> engineerPool = new ArrayList<EngineerEntity>();
	
    @Override
    public List<EngineerEntity> clone() throws CloneNotSupportedException{
    	
    	List<EngineerEntity> cloneEmpList = new ArrayList<EngineerEntity>();
        this.engineerPool.forEach(eng -> cloneEmpList.add(eng.clone()));
    	logger.info(" Clone Engineer Pool - {}",cloneEmpList);
    	return cloneEmpList;
    }

    @PostConstruct
	public void init() throws Exception {
		this.engineerPool = this.engineerRepository.findAll();
		logger.info(" Engineer Pool - {} ", this.engineerPool );
		
	}
	

}
