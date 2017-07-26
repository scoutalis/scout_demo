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
	
	private WebElement comboBoxObject = null;
	private String TXT_Caption="";
	
	public WebComboBox(){};
	
	public WebComboBox(String FieldCaption){
        fullXpath = String.format("//div[div[text()='%s']]", FieldCaption);
        WebElement fieldCaption = FindWebElements.findWebElementVisibleByXpath(fullXpath);
        WebElement parentElement = fieldCaption.findElement(By.xpath("./.."));       
        this.comboBoxObject = parentElement.findElement(By.tagName("input"));
	}
	
	public WebComboBox(WebElement comboBox){
		this.comboBoxObject = comboBox;
	}

	public WebComboBox initializeWebEdit(String txt_Caption){
		this.TXT_Caption = txt_Caption;
        fullXpath = String.format("//div[div[text()='%s']]", txt_Caption);
        WebElement fieldCaption = FindWebElements.findWebElementVisibleByXpath(fullXpath);
        WebElement parentElement = fieldCaption.findElement(By.xpath("./.."));
        this.comboBoxObject = parentElement.findElement(By.tagName("input"));	
        return this;
	}	
	
	public void selectComboBoxItem(String optionValue){
    	this.comboBoxObject.clear();
    	this.comboBoxObject.sendKeys(optionValue);
        WebElement filterList = FindWebElements.findWebElementVisibleById("VAADIN_COMBOBOX_OPTIONLIST");
        filterList.click(); 
	}

}
