package com.ajackus.ws.helper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class WebpageScreenshotHelper {

	/**
	 * This method fetches the detailed error message for the error code encountered
	 * using resource bundle.
	 * 
	 * @param errorCode
	 * @return
	 */
	public static String getErrorMessage(String errorCode) {
		
		ResourceBundle bundle = ResourceBundle.getBundle("errorMessages", Locale.US);
		return bundle.getString(errorCode);
	}
	
	/**
	 * This method validates that the url received loads a valid webpage.
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static boolean validateUrl(String url) {
		
		boolean isValid = false;
		try {
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection)u.openConnection();
			int responseCode = conn.getResponseCode();
			if (HttpURLConnection.HTTP_OK == responseCode) {
				isValid = true;
			}
		} catch (IOException e) {
			isValid = false;
		}
		return isValid;
	}
	
	public static String completeUrl(String url) {
		
		String completeUrl = url;
		if (!url.startsWith("https://") && !url.startsWith("http://")) {
			completeUrl = "https://" + url;
		}
		return completeUrl;
	}
}
