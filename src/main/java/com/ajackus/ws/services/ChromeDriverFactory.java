package com.ajackus.ws.services;

/**
 * This class will determine the system OS and return an appropriate
 * chrome driver for the OS. Chrome drivers for the different OS will
 * be packaged in the resources folder.
 * 
 * @author rahil
 *
 */
public class ChromeDriverFactory {
	
	public static String fetchChromeDriver() {
		
		String os = System.getProperty("os.name").toLowerCase();
		String driverPath = "./src/main/resources/chromedriver";
		if (os.contains("win")) {
			return driverPath + ".exe";
		} else if (os.contains("nix") || os.contains("nux")) {
			return driverPath + "_linux";
		} else if (os.contains("mac")) {
			return driverPath + "_mac";
		}
		return null;
	}
}
