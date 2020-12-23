package com.utility;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

  public class BrowserStackTest {

	public static AppiumDriver driver;
	@AndroidFindBy(accessibility = "test-LOGIN") private MobileElement loginBtn;


	  @Test
	  public static void openTheSystem() throws MalformedURLException, InterruptedException {
		    
	      DesiredCapabilities caps = new DesiredCapabilities();      
	      // Set your access credentials
	      caps.setCapability("browserstack.user", "vicky280");
	      caps.setCapability("browserstack.key", "Jcim5xTfXggAJqXoVqEG");
	      // Set URL of the application under test
	      caps.setCapability("app", "bs://4a0ef38be50c8b64ed402493693a31f0dce75187");
	      // Specify device and os_version for testing
	      caps.setCapability("device", "Google Pixel 2");
	      caps.setCapability("os_version", "9.0");
	      // Set other BrowserStack capabilities
	      caps.setCapability("project", "First Java Project");
	      caps.setCapability("build", "Java Android");
	      caps.setCapability("name", "first_test");
	      // Initialise the remote Webdriver using BrowserStack remote URL
	      // and desired capabilities defined above
	       AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://hub.browserstack.com/wd/hub"), caps);
	       Thread.sleep(10000);
	       	       
	       driver.findElementByAccessibilityId("test-LOGIN").click(); 
	      /* Write your Custom code here */
	        
	      // Invoke driver.quit() after the test is done to indicate that the test is completed.
	      driver.quit();
	    
	    }
	  
//	  public class BrowserStackSampleTest {
//		  public static final String USERNAME = "vicky280";
//		  public static final String AUTOMATE_KEY = "Jcim5xTfXggAJqXoVqEG";
//		  public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
//		  public void Runne() throws Exception {
//		    DesiredCapabilities caps = new DesiredCapabilities();
//		    
//		    caps.setCapability("browserName", "android");
//		    caps.setCapability("device", "Google Google Pixel 3");
//		    caps.setCapability("realMobile", "true");
//		    caps.setCapability("os_version", "9.0");
//		    
//		    caps.setCapability("name", "vicky280's First Test");
//		    
//		    WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
//		    driver.get("http://www.google.com");
//		    WebElement element = driver.findElement(By.name("q"));
//
//		    element.sendKeys("BrowserStack");
//		    element.submit();
//
//		    System.out.println(driver.getTitle());
//		    driver.quit();
//		  }
//    }
  }
