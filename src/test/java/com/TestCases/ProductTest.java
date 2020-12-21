package com.TestCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.BaseClass.TestBase;
import com.Pages.LoginPage;
import com.Pages.ProductDetailsPage;
import com.Pages.ProductPage;
import com.Pages.SettingPage;

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

    public class ProductTest extends TestBase {
	LoginPage loginPage;
	ProductPage productPage;
	JSONObject loginUsers;
	SettingPage settingPage;
	ProductDetailsPage detailsPage;

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
		productPage = loginPage.userLogin(loginUsers.getJSONObject("validUser").getString("username"), 
				loginUsers.getJSONObject("validUser").getString("password"));	
	}

	@AfterMethod
	public void afterMethod() {	
		settingPage = productPage.pressSettingsBtn();
		loginPage = settingPage.pressLogOutBtn();
	}

	@Test(enabled = false)
	public void validateProductsOnProductPage() throws Exception {
		SoftAssert sft = new SoftAssert();
		String expectedBagTitle = productPage.getBagTitle();
		sft.assertEquals(expectedBagTitle, hs.get("products_page_Bag_title"));
		
		String expectedBagPrice = productPage.getBagPrice();
		sft.assertEquals(expectedBagPrice, hs.get("products_page_Bag_price"));
		sft.assertAll();
	}
	
	@Test(priority = 1)
	public void validateProductsOnProductDetailsPage() throws Exception {
		SoftAssert sft = new SoftAssert();
		
		detailsPage = productPage.pressBagTitle();		
		String expectedBagTitle = detailsPage.getBagTitle();
		sft.assertEquals(expectedBagTitle, hs.get("products_page_Bag_title"));
		
		detailsPage.scrollToBagPrice();
		String expectedBagDesc = detailsPage.getBagDescription();
		sft.assertEquals(expectedBagDesc, hs.get("product_details_page_Bag_description"));
		
		String expectedBagPrice = detailsPage.getBagPrice();
		sft.assertEquals(expectedBagPrice, hs.get("product_details_page_Bag_price"));
		productPage = detailsPage.pressBackToProductButton();
		
		sft.assertAll();
	}
  }

    
    
    
    
    
    
    
    
    
    
    
    
    