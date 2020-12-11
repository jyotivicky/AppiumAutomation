package com.Pages;

import com.BaseClass.TestBase;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ProductPage extends MenuPage {

	@AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"test-Item title\"])[1]")
	private MobileElement bagTitle;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"test-Price\"])[1]")
	private MobileElement bagPrice;

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

 }
