package com.Pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

    public class ProductPage extends MenuPage {

	@AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"test-Item title\"])[1]") private MobileElement bagTitle;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='PRODUCTS']") private MobileElement PageTitle;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"test-Price\"])[1]") private MobileElement bagPrice;
	
	@AndroidFindBy(xpath = "(//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"])[1]") private MobileElement AddToCart;
	
	@AndroidFindBy(accessibility =  "test-REMOVE") private MobileElement Remove;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.view.ViewGroup")
	private MobileElement CartButton;
	
	public String getPageTitle() {
		waitForVisibilty(PageTitle);
		return getText(PageTitle);
	}

	public String getBagTitle() {
		return getText(bagTitle);
	}

	public String getBagPrice() {
		return getText(bagPrice);
	}

	public ProductDetailsPage pressBagTitle() {
		click(bagTitle);
		return new ProductDetailsPage();
	}
	
	public CartCheckoutPage clickAddToCart() {
		click(AddToCart);
		return new CartCheckoutPage();
	}
	
	public String getButtonText() {
		return getText(Remove);
	}
	
	public CartCheckoutPage CartButton() {
		click(CartButton);
		return new CartCheckoutPage();
	}

 }




