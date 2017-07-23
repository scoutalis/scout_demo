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
		
		WebPage.alisTextField.setTextFieldByCaption("First Name", "John 2307171600");
		WebPage.alisTextField.setTextFieldByCaption("Middle Name", "Maininsured");
		WebPage.alisTextField.setTextFieldByCaption("Last Name", "Fifty");
		WebPage.alisTextField.setDateFieldByCaption("Date Of Birth", "01/01/1965");
		WebPage.alisComboBox.setComboBoxByCaption("Gender", "male");
		WebPage.alisComboBox.setComboBoxByCaption("Tobacco", "No");
		WebPage.alisCheckBox.setCheckboxTrueByLabelName("Non Driver");
		
		
		
		WebPage.alisButton.clickAlisCloseButton();
		
		WebPage.waitLoading();
		
		activePageName = WebPage.getActivePageName();
		Assert.assertEquals(activePageName, "User Dashboard");

		System.out.println("Done");
		
		Driver.closeBrowser();

	}

}
