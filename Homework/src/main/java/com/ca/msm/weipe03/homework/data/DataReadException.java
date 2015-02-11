package com.ca.msm.weipe03.homework.data;

public class DataReadException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataReadException() {
	}

	public DataReadException(String message){
		super(message);
	}
	
	public DataReadException(Throwable cause){
		super(cause);
	}
	
	public DataReadException(String message, Throwable cause){
		super(message, cause);
	}

}
