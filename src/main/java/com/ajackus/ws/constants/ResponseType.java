package com.ajackus.ws.constants;

public enum ResponseType {
	
	SUCCESS("Success"), FAILURE("Failure");
	
	String responseTypeDescription;
	
	private ResponseType(String desc) {
		responseTypeDescription = desc;
	}
	
	public String toString(ResponseType responseType) {
		
		return responseType.responseTypeDescription;
	}
}
