package com.TestCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.BaseClass.TestBase;
import com.Pages.CartCheckoutPage;
import com.Pages.CheckoutInformationPage;
import com.Pages.CheckoutOverviewPage;
import com.Pages.LoginPage;
import com.Pages.ProductPage;
import com.Pages.YourCartPage;
import com.Report.TestReport;
import com.aventstack.extentreports.Status;
import com.utility.MobileBot;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import java.io.StringWriter;
import java.io.IOException;
import java.io.InputStream;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class LoginTest extends TestBase {
	LoginPage loginPage;
	ProductPage productPage;
	JSONObject loginUsers;

	@BeforeClass
	public void beforeClass() throws IOException {
		InputStream datais = null;
		try {
			String dataFileName = "TestData/TestData.json";
			datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
			JSONTokener tokener = new JSONTokener(datais);
			loginUsers = new JSONObject(tokener);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (datais != null) {
				datais.close();
			}
		}
		closeApp();
		lunchApp();
	}

	@AfterClass
	public void afterClass() {
	}

	@BeforeMethod
	public void beforeMethod() {
		loginPage = new LoginPage();
		productPage = new ProductPage();
	}

	@AfterMethod
	public void afterMethod() {
	}

	@Test(enabled = false)
	public void InvalidUsernameTest() throws Exception {
		SoftAssert sft = new SoftAssert();
		
		loginPage.enterUsername(loginUsers.getJSONObject("invalidusername").getString("username"));
		loginPage.enterPassword(loginUsers.getJSONObject("invalidusername").getString("password"));
		loginPage.clickLoginButton();
		
		String ActualMessage = loginPage.getErrorTxt();
		sft.assertEquals(ActualMessage, hs.get("err_invalid_username_or_password"));
		TestReport.getTest().log(Status.INFO, hs.get("invalid_credential_status_msg"));
		sft.assertAll();
	}

	@Test(enabled = false)
	public void InvalidPasswordTest() {
		SoftAssert sft = new SoftAssert();

		loginPage.enterUsername(loginUsers.getJSONObject("invalidPassword").getString("username"));
		loginPage.enterPassword(loginUsers.getJSONObject("invalidPassword").getString("password"));
		loginPage.clickLoginButton();
		
		String ActualMessage = loginPage.getErrorTxt();
		sft.assertEquals(ActualMessage, hs.get("err_invalid_username_or_password"));
		TestReport.getTest().log(Status.INFO, hs.get("invalid_credential_status_msg"));
		sft.assertAll();
	}

	@Test(priority = 1)
	public void ValidUsernameTest() throws InterruptedException {
		SoftAssert sft = new SoftAssert();
		
		loginPage.enterUsername(loginUsers.getJSONObject("validUser").getString("username"));
		loginPage.enterPassword(loginUsers.getJSONObject("validUser").getString("password"));
		loginPage.clickLoginButton();

		String actualPageTitle = productPage.getPageTitle();
		sft.assertEquals(actualPageTitle, hs.get("page_title"));
		TestReport.getTest().log(Status.INFO, hs.get("Valid_credential_status_msg"));
		sft.assertAll();
	}

}
