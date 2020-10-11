package com.ajackus.ws.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ajackus.ws.constants.ErrorConstants;
import com.ajackus.ws.constants.ResponseType;
import com.ajackus.ws.helper.WebpageScreenshotHelper;
import com.ajackus.ws.response.ScreenshotResponse;
import com.ajackus.ws.services.WebpageScreenshotService;

@RestController
@RequestMapping(path="/url")
public class WebpageScreenshotController {
	
	@Autowired
	WebpageScreenshotService service;
	
	@RequestMapping(value = "**", method = RequestMethod.GET)
	@ResponseBody
	public ScreenshotResponse getWebpageScreenshot(HttpServletRequest request) {
		String requestString = request.getRequestURL().toString();
	    String url = requestString.split("/url/")[1];
	    
	    url = WebpageScreenshotHelper.completeUrl(url);
	    ScreenshotResponse response;
	    if (WebpageScreenshotHelper.validateUrl(url)) {
	    	response = service.getScreenshot(url);
	    	return response;
	    } else {
	    	response = new ScreenshotResponse();
	    	response.setResponseType(ResponseType.FAILURE);
	    	response.setDetailedMessage(WebpageScreenshotHelper.getErrorMessage(ErrorConstants.E_URL_NOT_FOUND));
	    	return response;
	    }
	}
}
