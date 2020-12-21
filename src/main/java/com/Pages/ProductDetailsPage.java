package com.Pages;

import com.BaseClass.TestBase;
import com.utility.MobileBot;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

  public class ProductDetailsPage extends MenuPage {

	  @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[1]") private MobileElement bagTitle;
	  
	  @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[2]") private MobileElement bagDesciption;
	  
	  @AndroidFindBy(accessibility = "test-BACK TO PRODUCTS") private MobileElement backToProduct;
	  
	  @AndroidFindBy(accessibility = "test-Price") private MobileElement bagPrice;


  public String getBagTitle() {
	 return getText(bagTitle);
   }
  
  public String getBagDescription() {
	 waitForVisibilty(bagDesciption);
	 return getText(bagDesciption);
   }

  public ProductDetailsPage scrollToBagPrice() {
	  scrollToElement();
	  return this;
  }
  
  public String getBagPrice() {
	  String price =  getText(scrollToElement());
	  return price;
  }
  
  public ProductPage pressBackToProductButton() {
	  click(backToProduct);
	  return new ProductPage();
  }
  
 }
