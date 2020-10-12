package com.ajackus.ws.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ajackus.ws.WebpageScreenshotApplication;
import com.ajackus.ws.constants.ErrorConstants;
import com.ajackus.ws.constants.ResponseType;
import com.ajackus.ws.helper.WebpageScreenshotHelper;
import com.ajackus.ws.response.ScreenshotResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes=WebpageScreenshotApplication.class)
class WebpageScreenshotApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;
	
	private void initialize() {
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	private ScreenshotResponse performGetRequest(String url) throws Exception{
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/url/" + url);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String responseJson = result.getResponse().getContentAsString();
		ObjectMapper mapper = new ObjectMapper();
		ScreenshotResponse response = mapper.readValue(responseJson, ScreenshotResponse.class);
		return response;
	}
	
	@Test
	public void testWebpageScreenshotSuccess() throws Exception {
		
		initialize();
		String url = "www.google.com"; // URL exists
		
		ScreenshotResponse response = performGetRequest(url);
		File imageFile = new File(response.getImagePath());
		assertTrue(imageFile.exists());
		assertEquals(response.getResponseType(), ResponseType.SUCCESS);
		assertEquals(response.getDetailedMessage(), WebpageScreenshotHelper.getErrorMessage(ErrorConstants.E_SUCCESS));
	}
	
	@Test
	public void testWebpageScreenshotBadUrl() throws Exception {
		
		initialize();
		String url = "www.googlex.com"; // URL does not exist
		
		ScreenshotResponse response = performGetRequest(url);
		assertNull(response.getImagePath());
		assertEquals(response.getResponseType(), ResponseType.FAILURE);
		assertEquals(response.getDetailedMessage(), WebpageScreenshotHelper.getErrorMessage(ErrorConstants.E_URL_NOT_FOUND));
	}
}
