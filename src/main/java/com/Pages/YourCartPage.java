package com.Pages;

import com.BaseClass.TestBase;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

    public class YourCartPage extends TestBase{
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='YOUR CART']") private MobileElement YourCart;
	
	@AndroidFindBy(accessibility = "test-CHECKOUT") private MobileElement CheckOut;


	public void clickCheckoutButton() {
		click(CheckOut);
	}

	public String getPageTitleText() {
		return getText(YourCart);
	}

}
