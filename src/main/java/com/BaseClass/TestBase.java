package com.BaseClass;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import com.utility.TestUtility;
import com.Report.TestReport;
import com.aventstack.extentreports.Status;
import com.sun.tools.classfile.StackMap_attribute.stack_map_frame;
import com.utility.BrowserStackTest;
import com.utility.GetAppiumStatus;
import com.utility.TimeUtils;
import org.apache.commons.codec.binary.Base64;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class TestBase {

	public static AppiumDriver driver;
	protected static Properties props;
	protected static HashMap<String, String> hs = new HashMap<String, String>();
	InputStream inputStream;
	InputStream inputString;
	protected static String dateTime;
	protected static TestUtility utility;
	private static AppiumDriverLocalService server;
	public static GetAppiumStatus getAppium = new GetAppiumStatus();
	public static ThreadLocal<String> platform = new ThreadLocal<String>();
	public static ThreadLocal<String> deviceName = new ThreadLocal<String>();

	public TestBase() {
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(20)), this);
	}

	public void setPlatform(String Platform) {
		platform.set(Platform);
	}

	public String getPlatform() {
		return platform.get();
	}

	public String getDeviceName() {
		return deviceName.get();
	}

	public void setDeviceName(String DeviceName) {
		deviceName.set(DeviceName);
	}

	/*
	 * Here we first check whether Appium start first, If Appium is not running then
	 * it will to the go to the If condition and start appium server otherwise it
	 * will go to the else section and stop the Appium server and Start Again
	 */
	@BeforeSuite
	public void startAppiumServer() throws Exception, Exception {
		utility = new TestUtility();
		server = getAppium.getAppiumServerDefault();
		if (!getAppium.checkIfAppiumServerIsRunnning(4723)) {
			server.start();
			server.clearOutPutStreams();
			utility.log().info("Appium Server Started Sucessfully");
		} else {
			utility.log().info("Appium Server Already Running");
			Runtime runtime = Runtime.getRuntime();
			try {
				runtime.exec("taskkill /F /IM node.exe");
			} catch (IOException e) {
				e.printStackTrace();
			}
			server.start();
//			server.clearOutPutStreams();
			utility.log().info("Appium Server Started Sucessfully");
		}
	}

	/*
	 * Here we are Stopping te Appium Server after all test case got executed
	 */
	@AfterSuite
	public void stopAppiumServer() {
		utility = new TestUtility();
		server.stop();
		utility.log().info("Appium Server Stopped Sucessfully");
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}

	/*
	 * To Start video capturing and create *.mp4 file
	 */
	@BeforeMethod
	public void startVideoRecording() {
		((CanRecordScreen) driver).startRecordingScreen();
	}

	/*
	 * To stop video capturing and create *.mp4 file
	 */
	@AfterMethod
	public void stopVideoRecording(ITestResult result) throws Exception {
		String media = ((CanRecordScreen) driver).stopRecordingScreen();
		Map<String, String> params = result.getTestContext().getCurrentXmlTest().getAllParameters();

		String dirPath = "TestVideos" + File.separator + params.get("platformName") + "_" + params.get("deviceName")
				+ File.separator + getDateTime() + File.separator
				+ result.getTestClass().getRealClass().getSimpleName();

		File videoDir = new File(dirPath);

		if (!videoDir.exists()) {
			videoDir.mkdirs();
		}
		FileOutputStream stream = null;
		try {
			stream = new FileOutputStream(videoDir + File.separator + result.getName() + ".mp4");
			stream.write(Base64.decodeBase64(media));
			stream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Parameters({"emulator", "platformName", "platformVersion", "deviceName"})
	@BeforeTest
	public void DriverInitializataion(String emulator, String platformName, String platformVersion, String deviceName) throws Exception {
		utility = new TestUtility();
		dateTime = utility.dateTime();
		setPlatform(platformName);
		setDeviceName(deviceName);
		URL appiumURL;
		try {
			props = new Properties();
			String proFileName = "Config.properties";
			String xmlFileName = "Messages/Messages.xml";
			inputStream = getClass().getClassLoader().getResourceAsStream(proFileName);
			props.load(inputStream);
			inputString = getClass().getClassLoader().getResourceAsStream(xmlFileName);
			hs = utility.parseStringXML(inputString);

			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("platformName", platformName);
			cap.setCapability("deviceName", deviceName);
//			cap.setCapability("udid", udid);
			appiumURL = new URL(props.getProperty("appiumUrl"));

			switch (platformName) {
			case "Android":
				cap.setCapability("automationName", props.getProperty("AndroidAutomation"));
				if (emulator.equalsIgnoreCase("true")) {
					cap.setCapability("platformVersion", platformVersion);
					cap.setCapability("deviceName", deviceName);
					cap.setCapability("avdLaunchTimeout", 120000);
					cap.setCapability("appPackage", props.getProperty("Apppackage"));
					cap.setCapability("appActivity", props.getProperty("Appactivity"));
				}
				if (emulator.equalsIgnoreCase("false")) {
					
					final String USERNAME = "vicky280";
					final String AUTOMATE_KEY = "Jcim5xTfXggAJqXoVqEG";
					final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
					  
					cap.setCapability("deviceName", deviceName);
					cap.setCapability("platformVersion", platformVersion);
//					cap.setCapability("realMobile", "true");
//					cap.setCapability("avdLaunchTimeout", 120000);
//					cap.setCapability("browserstack.user", "vicky280");
//					cap.setCapability("browserstack.key", "Jcim5xTfXggAJqXoVqEG");			
					cap.setCapability("app", "bs://4a0ef38be50c8b64ed402493693a31f0dce75187");
//					cap.setCapability("appWaitDuration", 30000);
					cap.setCapability("project", "First Java Project");
					cap.setCapability("build", "Android Testing");
					cap.setCapability("name", "First_Test_002");
					AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL(URL), cap);
				}
				driver = new AndroidDriver(appiumURL, cap);
				break;
			default:
				throw new Exception("Invalid platform! - " + platformName);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;

		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (inputString != null) {
				inputString.close();
			}
		}
	}

	public AppiumDriver getTheDriver() {
		return driver;
	}

	public void waitForVisibilty(MobileElement element) {
		WebDriverWait wait = new WebDriverWait(driver, TimeUtils.Wait);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void clear(MobileElement e) {
		waitForVisibilty(e);
		e.clear();
	}

	public void click(MobileElement e) {
		waitForVisibilty(e);
		e.click();
	}

	public void click(MobileElement e, String msg) {
		waitForVisibilty(e);
		utility.log().info(msg);
		TestReport.getTest().log(Status.INFO, msg);
		e.click();
	}

	public void sendKeys(MobileElement e, String text) {
		waitForVisibilty(e);
		e.sendKeys(text);
	}

//		public String getElementText(MobileElement e, String attribute) {
//			waitForVisibilty(e);
//			return e.get
//		}

	public String getAttribute(MobileElement e, String attribute) {
		waitForVisibilty(e);
		return e.getAttribute(attribute);
	}

	public String getText(MobileElement e) {
			return getAttribute(e, "text");
	}

	public MobileElement scrollToElement() {
		return (MobileElement) ((FindsByAndroidUIAutomator) driver).findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()" + ".description(\"test-Inventory item page\")).scrollIntoView("
						+ "new UiSelector().description(\"test-Price\"));");
	}

	public String getDateTime() {
		return dateTime;
	}

	public void lunchApp() {
		((InteractsWithApps) driver).launchApp();
	}

	public void closeApp() {
		((InteractsWithApps) driver).closeApp();
	}



}