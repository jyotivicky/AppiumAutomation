package com.Pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CartCheckoutPage extends MenuPage {

	@AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"test-Item title\"])[1]")private MobileElement bagTitle;

	@AndroidFindBy(xpath = "//android.widget.TextView[text()='YOUR CART']")private MobileElement YourCart;

	@AndroidFindBy(xpath = "(//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"])[1]")private MobileElement AddToCart;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.widget.ImageView") private MobileElement CartIcon;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-REMOVE\"]") private MobileElement Remove;

	@AndroidFindBy(accessibility = "test-CHECKOUT") private MobileElement CheckOut;

	@AndroidFindBy(xpath = "//android.widget.TextView[text()='CHECKOUT: INFORMATION']") private MobileElement CheckoutInformation;

	@AndroidFindBy(accessibility = "test-First Name") private MobileElement FirstName;

	@AndroidFindBy(accessibility = "test-Last Name") private MobileElement LastName;

	@AndroidFindBy(accessibility = "test-Zip/Postal Code") private MobileElement PostalCode;

	@AndroidFindBy(accessibility = "test-CONTINUE") private MobileElement Continue;
	
	@AndroidFindBy(accessibility = "test-FINISH") private MobileElement Finish;

	@AndroidFindBy(xpath = "//android.widget.TextView[text()='CHECKOUT: OVERVIEW']") private MobileElement CheckoutOverview;

	@AndroidFindBy(xpath = "//android.widget.TextView[text()='CHECKOUT: COMPLETE!']") private MobileElement CheckoutComplete;

	@AndroidFindBy(xpath = "//android.widget.ScrollView[@content-desc=\"test-CHECKOUT: COMPLETE!\"]/android.view.ViewGroup/android.widget.TextView[1]\r\n"
			+ "")
	private MobileElement ThankYouMessage;

	public void pressAddToCart() {
		click(AddToCart);
	}

	public String getCartButtonText() {
		waitForVisibilty(Remove);
		return getText(Remove);
	}

	public void clickLoginButton() {
		click(CartIcon);
	}

}
