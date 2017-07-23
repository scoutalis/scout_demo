package scout.alis.core.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import webdriver.Driver;
import webdriver.FindWebElements;

public class WebEdit {
	
	protected final static WebDriver DRIVER = Driver.instance;
	private static WebElement element = null;
	private static String fullXpath = null;
	
    public static void setTextFieldByCaption(String inputFieldCaption, String valueToSet){

        fullXpath = String.format("//div[div[text()='%s']]", inputFieldCaption);
        WebElement fieldCaption = FindWebElements.findWebElementVisibleByXpath(fullXpath);
        WebElement inputField = fieldCaption.findElement(By.xpath("following-sibling::input"));
        inputField.clear();
        inputField.sendKeys(valueToSet);
    }
    
    public static void setDateFieldByCaption(String inputFieldCaption, String valueToSet){

        fullXpath = String.format("//div[div[text()='%s']]", inputFieldCaption);
        WebElement fieldCaption = FindWebElements.findWebElementVisibleByXpath(fullXpath);
        WebElement inputField = fieldCaption.findElement(By.xpath("following-sibling::div/input"));
        inputField.clear();
        inputField.sendKeys(valueToSet);
    }
}
