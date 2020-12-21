package com.Pages;

import com.BaseClass.TestBase;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

    public class LoginPage extends TestBase {

	@AndroidFindBy(accessibility = "test-Username")
	private MobileElement userNameTextFld;

	@AndroidFindBy(accessibility = "test-Password")
	private MobileElement passwordTxtFld;

	@AndroidFindBy(accessibility = "test-LOGIN")
	private MobileElement loginBtn;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView")
	private MobileElement errTxt;

	public LoginPage enterUsername(String username) {
		clear(userNameTextFld);
		utility.log().info("Login with : " + username);
		sendKeys(userNameTextFld, username);
		return this;
	}

	public LoginPage enterPassword(String password) {
		clear(passwordTxtFld);
		utility.log().info("Login with : " + password);
		sendKeys(passwordTxtFld, password);
		return this;
	}

	public ProductPage clickLoginButton() {
		utility.log().info("Click On Login Button");
		click(loginBtn);
		return new ProductPage();
	}

	public ProductPage userLogin(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		return clickLoginButton();
	}

	public String getErrorTxt() {
		return getText(errTxt);
	}
}

    
    
    