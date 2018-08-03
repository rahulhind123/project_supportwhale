package com.support.schedular.exception;

public class ScheduleGenerationException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public ScheduleGenerationException(String errorMessage){
		super(errorMessage);
	}

}
