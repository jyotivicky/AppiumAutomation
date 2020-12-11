package com.TestCases;

import org.testng.annotations.Test;
import com.BaseClass.TestBase;
import com.Pages.LoginPage;
import com.Pages.ProductPage;
import com.Report.TestReport;
import com.aventstack.extentreports.Status;

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
	}

	@AfterMethod
	public void afterMethod() {
	}

	@Test(priority = 1)
	public void InvalidUsernameTest() throws Exception {

		TestReport.getTest().log(Status.INFO, "Enter Username");
		
		loginPage.enterUsername(loginUsers.getJSONObject("invalidusername").getString("username"));
		loginPage.enterPassword(loginUsers.getJSONObject("invalidusername").getString("password"));
		loginPage.clickLoginButton();

		String ExpectedMessage = hs.get("err_invalid_username_or_password");
		String ActualMessage = loginPage.getErrorTxt();
		Assert.assertEquals(ExpectedMessage, ActualMessage);
	}

	@Test(enabled = false)
	public void InvalidPasswordTest() {
		loginPage.enterUsername("standard_user");
		loginPage.enterPassword("InvalidPassword");
		loginPage.clickLoginButton();
		
		String ExpectedMessage = "Username and password do not match any user in this service.";
		String ActualMessage = loginPage.getErrorTxt();
		Assert.assertEquals(ExpectedMessage, ActualMessage);
	}

	@Test(enabled = false)
	public void ValidUsernameTest() {
		loginPage.enterUsername("standard_user");
		loginPage.enterPassword("secret_sauce");
		loginPage.clickLoginButton();
	}

}

    
    