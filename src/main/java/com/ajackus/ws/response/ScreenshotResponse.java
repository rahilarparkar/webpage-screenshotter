package com.ajackus.ws.response;

import com.ajackus.ws.constants.ResponseType;

public class ScreenshotResponse {
	
	/**
	 * The path of the file on disk where the image is saved
	 */
	private String imagePath;
	
	/**
	 * The response type indicating success or failure of the request
	 */
	private ResponseType responseType;
	
	/**
	 * The error message. Will be 0 in case of successful response
	 */
	private String errorMessage;

	public String getImagePath() {
		
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		
		this.imagePath = imagePath;
	}
	
	public ResponseType getResponseType() {
	
		return responseType;
	}

	public void setResponseType(ResponseType responseType) {
		
		this.responseType = responseType;
	}

	public String getErrorMessage() {
		
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		
		this.errorMessage = errorMessage;
	}
}
