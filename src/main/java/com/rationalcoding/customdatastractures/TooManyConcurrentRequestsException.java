package com.rationalcoding.customdatastractures;

public class TooManyConcurrentRequestsException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TooManyConcurrentRequestsException(String message){
		super(message);
	}

}
