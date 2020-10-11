package com.ajackus.ws.response;

import com.ajackus.ws.constants.ResponseType;

/**
 * This class defines the response object for the screenshot request.
 * 
 * @author rahil
 *
 */
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
	 * The detailed response message
	 */
	private String detailedMessage;

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

	public String getDetailedMessage() {
		
		return detailedMessage;
	}

	public void setDetailedMessage(String detailedMessage) {
		
		this.detailedMessage = detailedMessage;
	}
}
