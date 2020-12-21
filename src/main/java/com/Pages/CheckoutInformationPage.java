package com.Pages;

import com.BaseClass.TestBase;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

    public class CheckoutInformationPage extends TestBase {
	
	@AndroidFindBy(accessibility = "test-First Name") private MobileElement FirstName;

	@AndroidFindBy(accessibility = "test-Last Name") private MobileElement LastName;

	@AndroidFindBy(accessibility = "test-Zip/Postal Code") private MobileElement PostalCode;

	@AndroidFindBy(accessibility = "test-CONTINUE") private MobileElement Continue;
	
	public void enterFirstname(String text) {
		sendKeys(FirstName, text);
	}
	
	public void enterLastname(String text) {
		sendKeys(LastName, text);
	}
	
	public void enterPostalcode(String text) {
		sendKeys(PostalCode, text);
	}
	
	public void clickContinueButton() {
		click(Continue);
	}

}
