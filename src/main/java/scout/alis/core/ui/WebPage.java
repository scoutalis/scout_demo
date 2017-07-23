package scout.alis.core.ui;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import webdriver.Driver;
import webdriver.FindWebElements;

public class WebPage {
	
	protected static WebDriver DRIVER = Driver.instance;
	private static WebElement element = null;
	private static String fullXpath = null;
	
	private static String activePageName = null;
	private static WebElement alisMenuItem = null;
	
	public static WebButton alisButton = new WebButton();
	public static WebComboBox alisComboBox = new WebComboBox();
	public static WebEdit alisTextField = new WebEdit();
	public static WebTable alisTable = new WebTable();
	
	
	public static String getActivePageName(){
		WebElement activePageNameButton = FindWebElements.findWebElementVisibleByClassName("alis-breadcrumbs-item-active");
		activePageName = activePageNameButton.getText();
		return 	activePageName;
	}
	
    public static void selectAlisMenuItem(String menuPath){
        String[] menuPathAsArray = menuPath.split("/");
        for(String item : menuPathAsArray){
            fullXpath = String.format("//span[text()='%s']", item.trim());
            alisMenuItem = FindWebElements.findWebElementVisibleByXpath(fullXpath);
            alisMenuItem.click();
        }
    }
    
    public static void waitLoading(){
        while(FindWebElements.findLoadingIndicator() != null){
           DRIVER.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }
    }
    
    
    
    
    
    
    
    
	
	
	
	
	
	

}
