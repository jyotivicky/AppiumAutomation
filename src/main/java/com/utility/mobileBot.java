package com.utility;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static java.time.Duration.ofMillis;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

    public class mobileBot {
	protected static AppiumDriver driver;

	/*
	 * to Scroll Down or Up based on the argument we pass
	 */
	public static void scrollUpAndDown(String direction) {
		Dimension dim = driver.manage().window().getSize();
		int x = dim.width / 2;
		int startY = 0;
		int endY = 0;

		switch (direction) {
		case "up":
			startY = (int) (dim.getHeight() * 0.8);
			endY = (int) (dim.getWidth() * 0.2);
			break;

		case "down":
			startY = (int) (dim.getHeight() * 0.2);
			endY = (int) (dim.getWidth() * 0.8);
			break;
		}
		TouchAction ts = new TouchAction(driver);
		ts.press(PointOption.point(x, startY)).waitAction(waitOptions(ofMillis(1000)))
				.moveTo(PointOption.point(x, endY)).release().perform();
	}

	public static boolean isDisplay(final By e) {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			return wait.until(new ExpectedCondition<Boolean>() {

				public Boolean apply(WebDriver driver) {
					if (driver.findElement(e).isDisplayed()) {
						return true;
					}
					return false;
				}
			});
		} catch (Exception e2) {
			return false;

		}

	}
	
	public static void scrollToElement(By e, String direction) {
		for(int i=0;i<100;i++) {
			if(isDisplay(e)) {
				break;
			}else {
				if(direction.equalsIgnoreCase("up")) {
					scrollUpAndDown("up");
					}else {
						scrollUpAndDown("down");
					}
				}
			}
			
		}
	
	
	
	
	
	
	}



    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    