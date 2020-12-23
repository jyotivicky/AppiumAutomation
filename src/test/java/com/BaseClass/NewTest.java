package com.BaseClass;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

  public class NewTest {

	protected static AppiumDriver driver;
	@AndroidFindBy(accessibility = "test-LOGIN") private MobileElement loginBtn;


	  @Test(priority = 1)
	  public static void BrowserStack() throws MalformedURLException, InterruptedException {
		    
	      DesiredCapabilities caps = new DesiredCapabilities();
	      
	      // Set your access credentials
	      caps.setCapability("browserstack.user", "vickydas1");
	      caps.setCapability("browserstack.key", "h5PRyirTSKdfmwPLYpQy");
	      
	      // Set URL of the application under test
	      caps.setCapability("app", "bs://909b4cf56d0bc2c289741ba80bbf9673d2e05621");
	      
	      // Specify device and os_version for testing
	      caps.setCapability("device", "Google Pixel 3");
	      caps.setCapability("os_version", "9.0");
	        
	      // Set other BrowserStack capabilities
	      caps.setCapability("project", "First Java Project");
	      caps.setCapability("build", "Java Android");
	      caps.setCapability("name", "first_test");
	        
	      
	      // Initialise the remote Webdriver using BrowserStack remote URL
	      // and desired capabilities defined above
	        AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(
	            new URL("http://hub.browserstack.com/wd/hub"), caps);
	        
	       Thread.sleep(3000);
	       	       
	       driver.findElementByAccessibilityId("test-LOGIN").click(); 
	       
	       Thread.sleep(3000);
	        
	      /* Write your Custom code here */
	        
	      // Invoke driver.quit() after the test is done to indicate that the test is completed.
	      driver.quit();
	    
	    }

  }

