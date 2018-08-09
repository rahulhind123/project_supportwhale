package com.support.schedular.exception;

public class DataNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 4465503837441265565L;

	public DataNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
