package selftesting;


import org.testng.Assert;

import scout.alis.core.ui.LoginPage;
import scout.alis.core.ui.WebPage;
import webdriver.Driver;

public class Main {

	public static void main(String[] args) {
		
		Driver.getInstance();
		Driver.getBaseUrl();
		
		LoginPage.login("Clerk", "a", "tfl_6631_auto");
		
		WebPage.waitLoading();
		
		String activePageName = WebPage.getActivePageName();
		Assert.assertEquals(activePageName, "User Dashboard");
		WebPage.selectAlisMenuItem("Client/New Person");
		
		WebPage.waitLoading();
		
		activePageName = WebPage.getActivePageName();
		Assert.assertEquals(activePageName, "Client Details");
		
		WebPage.alisButton.clickAlisCloseButton();
		
		WebPage.waitLoading();
		
		activePageName = WebPage.getActivePageName();
		Assert.assertEquals(activePageName, "User Dashboard");

		System.out.println("Done");
		
		Driver.closeBrowser();

	}

}
