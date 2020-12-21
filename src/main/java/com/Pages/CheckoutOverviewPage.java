package com.Pages;

import com.BaseClass.TestBase;
import com.utility.MobileBot;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

   public class CheckoutOverviewPage extends TestBase{
	   
		@AndroidFindBy(xpath = "//android.widget.TextView[@text='CHECKOUT: OVERVIEW']") private MobileElement CheckoutOverview;

		@AndroidFindBy(xpath = "//android.widget.TextView[@text='CHECKOUT: COMPLETE!']") private MobileElement CheckoutComplete;

		@AndroidFindBy(xpath = "//android.widget.ScrollView[@content-desc=\"test-CHECKOUT: COMPLETE!\"]/android.view.ViewGroup/android.widget.TextView[1]\r\n"
				+ "")
		private MobileElement ThankYouMessage;
		
		@AndroidFindBy(accessibility = "test-FINISH") private MobileElement Finish;

		public String getPageTitleText() {
			waitForVisibilty(CheckoutOverview);
			return getText(CheckoutOverview);
		}
		
		public void clickFinishButton() throws InterruptedException {
			Thread.sleep(2000);
			MobileBot.scrollUpAndDown("up", driver);
			click(Finish);
		}
		
		public String getPageTitleCompletePage() {
			return getText(CheckoutComplete);
		}
		
		public String getThankyouMessage() {
			return getText(ThankYouMessage);
		}
		
   }
