package com.ajackus.ws.services;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import com.ajackus.ws.constants.ErrorMessages;
import com.ajackus.ws.response.ScreenshotResponse;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

@Service
public class WebpageScreenshotService {
	
public ScreenshotResponse getScreenshot(String url) {
		
		ScreenshotResponse response = new ScreenshotResponse();
		try {
			System.setProperty("webdriver.chrome.driver", ChromeDriverFactory.fetchChromeDriver());
	        ChromeOptions opts = new ChromeOptions();
	        opts.addArguments("start-maximized");
	        opts.addArguments("disable-infobars");
	        opts.addArguments("--disable-extensions");
	        WebDriver driver =  new ChromeDriver(opts);
	        driver.get(url);
	        Screenshot myScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
	        
	        // Removing http/https prefix and building filename with partial url and timestamp
	        String urlOutputName = url.replace("https://", "").replace("http://", "");
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	        String timestamp = LocalDateTime.now().format(formatter);
	        String fileName = "./output/" + urlOutputName.substring(0, 10) + timestamp + ".png";
  
	        ImageIO.write(myScreenshot.getImage(),"png",new File(fileName));
//	        response.setScreenshot(myScreenshot);
	        response.setImagePath(System.getProperty("user.dir") + fileName.substring(1, fileName.length()));
	        response.setErrorMessage(getErrorMessage(ErrorMessages.E_SUCCESS));
	        driver.quit();
		} catch (IOException e) {
			//TODO: handle exception
		}
		return response;
	}

	private String getErrorMessage(String errorCode) {
		
		ResourceBundle bundle = ResourceBundle.getBundle("errorMessages", Locale.US);
		return bundle.getString(errorCode);
	}
}
