package scout.alis.core.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import webdriver.Driver;
import webdriver.FindWebElements;

public class LoginPage {
	
    private static final WebDriver DRIVER = Driver.instance;
    private static WebElement userNameField;
    private static WebElement passwordField;
    private static WebElement companyField;
    private static WebElement companyFilterList;
    private static WebElement loginButton;
	
    public static void login(String username, String password, String company){

        userNameField = FindWebElements.findWebElementVisibleById("user.name");
        passwordField = FindWebElements.findWebElementVisibleById("user.password");
        companyField = FindWebElements.findWebElementVisibleByClassName("v-filterselect-input");
        loginButton = FindWebElements.findWebElementVisibleById("login.button");

        userNameField.clear();
        userNameField.sendKeys(username);

        passwordField.clear();
        passwordField.sendKeys(password);

        companyField.clear();
        companyField.sendKeys(company);

        companyFilterList = FindWebElements.findWebElementVisibleById("VAADIN_COMBOBOX_OPTIONLIST");
        companyFilterList.click();
        loginButton.click();

    }
    
}
