package com.support.schedular.exception;

public class DateNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 4465503837441265565L;

	public DateNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
