package scout.alis.core.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import webdriver.Driver;
import webdriver.FindWebElements;

public class WebButton {
	
	protected final static WebDriver DRIVER = Driver.instance;
	private static WebElement element = null;
	private static String fullXpath = null;
	
	
    public void clickAlisButtonByName(String btnName){
        fullXpath = String.format("//span[text()='%s']", btnName);
        WebElement clientAlisSubMenuItem = FindWebElements.findWebElementVisibleByXpath(fullXpath);
        clientAlisSubMenuItem.click();
    }
    
    public void clickAlisSaveButton(){ 
    	WebElement ALIS_SAVE_BUTTON = FindWebElements.findWebElementVisibleByXpath("//span[text()='Save']");
    	ALIS_SAVE_BUTTON.click();
    }
    
    public void clickAlisNextButton(){
    	WebElement ALIS_NEXT_BUTTON = FindWebElements.findWebElementVisibleByXpath("//span[text()='Next']");
    	ALIS_NEXT_BUTTON.click();
    }
    
    public void clickAlisCloseButton(){
    	WebElement ALIS_CLOSE_BUTTON = FindWebElements.findWebElementVisibleByXpath("//span[text()='Close']");
    	ALIS_CLOSE_BUTTON.click();
    }

}
