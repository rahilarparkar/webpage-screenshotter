package com.ajackus.ws.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ajackus.ws.response.ScreenshotResponse;
import com.ajackus.ws.services.WebpageScreenshotService;

@RestController
@RequestMapping(path="/url")
public class WebpageScreenshotController {
	
	@Autowired
	WebpageScreenshotService service;
	
	@RequestMapping(value = "**", method = RequestMethod.GET)
	@ResponseBody
	public ScreenshotResponse moduleStrings(HttpServletRequest request) {
		String requestString = request.getRequestURL().toString();
	    String url = requestString.split("/url/")[1];
	    
	    ScreenshotResponse response = service.getScreenshot(url);
	    return response;

	}
}
