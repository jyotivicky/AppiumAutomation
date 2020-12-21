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

    public class ProductCheckOutTest extends TestBase {

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

	@AfterClass
	public void afterClass() {
		settingPage = productPage.pressSettingsBtn();
		loginPage = settingPage.pressLogOutBtn();
	}

	@Test(priority = 1)
	public void validateAddToCart() throws Exception {
		SoftAssert sft = new SoftAssert();
		cartCheckout.pressAddToCart();
		String AddedText = cartCheckout.getCartButtonText();
		sft.assertEquals(AddedText, hs.get("after_added"));
		TestReport.getTest().log(Status.INFO, hs.get("product_added_sucessfully"));
		cartCheckout.clickCartButton();
		sft.assertAll();
	}
	
	@Test(dependsOnMethods = {"validateAddToCart"})
	public void validateProductOnCartPage() {
		SoftAssert sft = new SoftAssert();
		String YourCart = cartPage.getPageTitleText();
		sft.assertEquals(YourCart, hs.get("your_cart"));
		TestReport.getTest().log(Status.INFO, hs.get("product_showing_in_cart_page"));
		cartPage.clickCheckoutButton();
		sft.assertAll();
	}	
		
	@Test(dependsOnMethods = {"validateProductOnCartPage"})
	public void validateProductOnInformationPage() {	
		SoftAssert sft = new SoftAssert();
		String infoPage = checkoutInfo.getPageTitle();
		sft.assertEquals(infoPage, hs.get("information_page_title"));
		TestReport.getTest().log(Status.INFO, hs.get("product_showing_in_info_page"));
		checkoutInfo.enterFirstname(info.getJSONObject("CheckoutInformation").getString("firstname"));
		checkoutInfo.enterLastname(info.getJSONObject("CheckoutInformation").getString("lastname"));
		checkoutInfo.enterPostalcode(info.getJSONObject("CheckoutInformation").getString("postalCode"));
		checkoutInfo.clickContinueButton();
		sft.assertAll();
	}
	
	@Test(dependsOnMethods = {"validateProductOnInformationPage"})
	public void validateProductOnOverviewPage() throws InterruptedException {
		SoftAssert sft = new SoftAssert();
		String overviewPage = overview.getPageTitleText();
		sft.assertEquals(overviewPage, hs.get("overview_page_title"));
		TestReport.getTest().log(Status.INFO, hs.get("product_showing_in_overview_page"));
		overview.clickFinishButton();
		sft.assertAll();	
	}
	
	@Test(dependsOnMethods = {"validateProductOnOverviewPage"})
	public void validateProductOrderSucess() {
		SoftAssert sft = new SoftAssert();
		String completePage = overview.getPageTitleCompletePage();
		sft.assertEquals(completePage, hs.get("checkout_complete_page_title"));
		TestReport.getTest().log(Status.INFO, hs.get("product_showing_in_checkout_complete_page"));		
		String thankYouMessage = overview.getThankyouMessage();
		sft.assertEquals(thankYouMessage, hs.get("thank_you_msg"));
		TestReport.getTest().log(Status.INFO, hs.get("product_ordered_sucessfully"));
		sft.assertAll();
	}
 

}

    
    
    
    