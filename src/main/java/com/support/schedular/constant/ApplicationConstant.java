package com.support.schedular.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "com.support")
@Configuration("applicationConstant")
public class ApplicationConstant {
	
	private int max_day_shift;
	private int max_engineer_shift;
	private int max_engineer_allocation;
	
	public int getMax_day_shift() {
		return max_day_shift;
	}
	public void setMax_day_shift(int max_day_shift) {
		this.max_day_shift = max_day_shift;
	}
	public int getMax_engineer_shift() {
		return max_engineer_shift;
	}
	public void setMax_engineer_shift(int max_engineer_shift) {
		this.max_engineer_shift = max_engineer_shift;
	}
	public int getMax_engineer_allocation() {
		return max_engineer_allocation;
	}
	public void setMax_engineer_allocation(int max_engineer_allocation) {
		this.max_engineer_allocation = max_engineer_allocation;
	}
	
}
