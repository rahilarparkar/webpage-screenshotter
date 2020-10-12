package com.ajackus.ws.services;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import com.ajackus.ws.constants.ErrorConstants;
import com.ajackus.ws.constants.ResponseType;
import com.ajackus.ws.helper.WebpageScreenshotHelper;
import com.ajackus.ws.response.ScreenshotResponse;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

/**
 * This service class contains the logic to get a webpage screenshot.
 * 
 * @author rahil
 *
 */

@Service
public class WebpageScreenshotService {
	
	/**
	 * This method takes url as a parameter, launches the appropriate chromedriver, takes a screenshot and saves 
	 * the file in the output folder.
	 * @param url
	 * @return
	 */
	public ScreenshotResponse getScreenshot(String url) {
		
		ScreenshotResponse response = new ScreenshotResponse();
		try {
			// Fetch appropriate chrome driver based on OS
			System.setProperty("webdriver.chrome.driver", ChromeDriverFactory.fetchChromeDriver());
			// Chrome options to get screenshot at max resolution
			ChromeOptions opts = new ChromeOptions();
			opts.addArguments("start-maximized");
			opts.addArguments("disable-infobars");
			opts.addArguments("--disable-extensions");
			// Launch chrome with url and take screenshot
			WebDriver driver =  new ChromeDriver(opts);
			driver.get(url);
			Screenshot myScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
			// Removing http/https prefix and building filename with partial url and timestamp
			String urlOutputName = url.replace("https://", "").replace("http://", "");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
			String timestamp = LocalDateTime.now().format(formatter);
			String fileName = "./output/" + urlOutputName.substring(0, urlOutputName.length()>9?9:urlOutputName.length()-1) + timestamp + ".png";
			// Saving image to file
			ImageIO.write(myScreenshot.getImage(),"png",new File(fileName));
			// Building API response object
			String filepath = System.getProperty("user.dir") + fileName.substring(1, fileName.length());
			response.setImagePath(filepath.replace("\\\\", "/").replace("\\", "/"));
			response.setResponseType(ResponseType.SUCCESS);
			response.setDetailedMessage(WebpageScreenshotHelper.getErrorMessage(ErrorConstants.E_SUCCESS));
			// Closing chrome window
			driver.quit();
		} catch (IOException e) {
			response.setResponseType(ResponseType.FAILURE);
			response.setDetailedMessage(WebpageScreenshotHelper.getErrorMessage(ErrorConstants.E_WRITE_FAILED));
		}
		return response;
	}
}
