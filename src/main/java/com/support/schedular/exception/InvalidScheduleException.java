package com.support.schedular.exception;

public class InvalidScheduleException extends RuntimeException{
	
	private static final long serialVersionUID = 4465503837441265565L;

	public InvalidScheduleException(String errorMessage) {
		super(errorMessage);
	}
}
