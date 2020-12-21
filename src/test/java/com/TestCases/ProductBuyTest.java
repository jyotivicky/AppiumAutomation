package com.TestCases;

import java.io.IOException;
import java.io.InputStream;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.BaseClass.TestBase;
import com.Pages.CartCheckoutPage;
import com.Pages.CheckoutInformationPage;
import com.Pages.CheckoutOverviewPage;
import com.Pages.LoginPage;
import com.Pages.ProductPage;
import com.Pages.SettingPage;
import com.Pages.YourCartPage;
import com.Report.TestReport;
import com.aventstack.extentreports.Status;

    public class ProductBuyTest extends TestBase {

	LoginPage loginPage;
	ProductPage productPage;
	JSONObject info;
	CartCheckoutPage cartCheckout;
	YourCartPage cartPage;
	CheckoutInformationPage checkoutInfo;
	CheckoutOverviewPage overview;
	SettingPage settingPage;

	@BeforeClass
	public void beforeClass() throws IOException {
		InputStream datais = null;
		try {
			String dataFileName = "TestData/TestData.json";
			datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
			JSONTokener tokener = new JSONTokener(datais);
			info = new JSONObject(tokener);
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
		cartCheckout = new CartCheckoutPage();
		cartPage = new YourCartPage();
		checkoutInfo = new CheckoutInformationPage();
		overview = new CheckoutOverviewPage();
		loginPage = new LoginPage();
		productPage = loginPage.userLogin(info.getJSONObject("validUser").getString("username"), 
				info.getJSONObject("validUser").getString("password"));	
	}

	@AfterMethod
	public void afterMethod() {
		settingPage = productPage.pressSettingsBtn();
		loginPage = settingPage.pressLogOutBtn();
	}

	@Test(priority = 1)
	public void ProductAddedTest() throws Exception {
	
		SoftAssert sft = new SoftAssert();
		
		cartCheckout.pressAddToCart();
		String str = cartCheckout.getCartButtonText();
		System.out.println("The Product Text is: " + str);
        
		cartCheckout.clickCartButton();
		
		String str2 = cartPage.getPageTitleText();
		System.out.println("The Cart Page Title Text is: " + str2);
		
//		sft.assertEquals(str2, hs.get("err_invalid_username_or_password"));
//		TestReport.getTest().log(Status.INFO, "User not able to login with invalid username and password again");
		
		cartPage.clickCheckoutButton();
		
		checkoutInfo.enterFirstname(info.getJSONObject("CheckoutInformation").getString("firstname"));
		checkoutInfo.enterLastname(info.getJSONObject("CheckoutInformation").getString("lastname"));
		checkoutInfo.enterPostalcode(info.getJSONObject("CheckoutInformation").getString("postalCode"));
		checkoutInfo.clickContinueButton();
		 

//		String str3 = overview.getPageTitleText();
//		System.out.println("The Final Page Title is : " + str3); 
		
		overview.clickFinishButton();
		
//		String str4 = overview.getPageTitleCompletePage();
//		System.out.println("The last Page Title is : " + str4); 
		
//		String str5 = overview.getThankyouMessage();
//		System.out.println("The Thank you message is : " + str5); 
		
		sft.assertAll();
	}

}

    
    
    
    