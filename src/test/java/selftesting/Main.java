package selftesting;


import org.testng.Assert;

import scout.alis.business.LoginPage;
import scout.alis.core.ui.WebComboBox;
import scout.alis.core.ui.WebEdit;
import scout.alis.core.ui.WebPage;
import webdriver.Driver;

public class Main {

	public static void main(String[] args) {
		
		Driver.getInstance();
		Driver.getBaseUrl("http://alis-deploy15:8094/tfl_pl_env8/alis#alis");
		
		LoginPage.login("Clerk", "a", "tfl_6631_auto");
		
		WebPage.waitLoading();
		
		String activePageName = WebPage.getActivePageName();
		Assert.assertEquals(activePageName, "User Dashboard");
		WebPage.selectAlisMenuItem("Client/New Person");
		
		WebPage.waitLoading();
		
		activePageName = WebPage.getActivePageName();
		Assert.assertEquals(activePageName, "Client Details");

		WebComboBox alisComboBox = new WebComboBox();
		WebEdit alisTextField = new WebEdit();	
		
		alisTextField.initializeWebEdit("First Name").setText("John 2307171600");
		alisTextField.initializeWebEdit("Middle Name").setText("Maininsured");
		alisTextField.initializeWebEdit("Last Name").setText("Fifty");
		alisTextField.initializeWebEdit("Date Of Birth").setText("01/01/1965");		
		alisComboBox.initializeWebEdit("Gender").selectComboBoxItem("male");
		alisComboBox.initializeWebEdit("Tobacco").selectComboBoxItem("No");

		WebPage.alisCheckBox.setCheckboxTrueByLabelName("Non Driver");
		
		
		
		WebPage.alisButton.clickAlisCloseButton();
		
		WebPage.waitLoading();
		
		activePageName = WebPage.getActivePageName();
		Assert.assertEquals(activePageName, "User Dashboard");

		System.out.println("Done");
		
		Driver.closeBrowser();

	}

}
