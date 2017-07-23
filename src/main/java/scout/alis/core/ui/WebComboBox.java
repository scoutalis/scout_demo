package scout.alis.core.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import webdriver.Driver;
import webdriver.FindWebElements;

public class WebComboBox {
	
	protected final static WebDriver DRIVER = Driver.instance;
	private static WebElement element = null;
	private static String fullXpath = null;
	
    public static void setComboBoxByCaption(String filterFieldCaption, String valueToSet){

        fullXpath = String.format("//div[div[text()='%s']]", filterFieldCaption);
        WebElement fieldCaption = FindWebElements.findWebElementVisibleByXpath(fullXpath);
        WebElement filterInputField = fieldCaption.findElement(By.xpath("following-sibling::div/input"));
        filterInputField.clear();
        filterInputField.sendKeys(valueToSet);
        WebElement filterList = FindWebElements.findWebElementVisibleById("VAADIN_COMBOBOX_OPTIONLIST");
        filterList.click();
    }

}
