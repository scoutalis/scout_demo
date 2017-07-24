package scout.alis.core.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import webdriver.Driver;
import webdriver.FindWebElements;

public class WebEdit {
	
	protected final static WebDriver DRIVER = Driver.instance;
	private static JavascriptExecutor js =(JavascriptExecutor)DRIVER;
	private static WebElement element = null;
	private static String fullXpath = null;
	
	private String TXT_Caption="";
	private String TXT_id;
	
	
	public WebEdit(){}
	
	public WebEdit(String txt_Caption) {
		this.TXT_Caption = txt_Caption;
	}
	
	
	public void setTXTCaption(String txt_Caption){
		this.TXT_Caption = txt_Caption;
	}
	
	public void setText(String valueToSet){
		
        fullXpath = String.format("//div[div[text()='%s']]", this.TXT_Caption);
        WebElement fieldCaption = FindWebElements.findWebElementVisibleByXpath(fullXpath);
        WebElement inputField = fieldCaption.findElement(By.xpath("following-sibling::input"));
        inputField.clear();
        inputField.sendKeys(valueToSet);		
	}
	
	
    public void setTextFieldByCaption(String inputFieldCaption, String valueToSet){
    	
    	this.TXT_Caption = inputFieldCaption;
        fullXpath = String.format("//div[div[text()='%s']]", this.TXT_Caption);
        WebElement fieldCaption = FindWebElements.findWebElementVisibleByXpath(fullXpath);

        WebElement parentElement = fieldCaption.findElement(By.xpath("./.."));
        		
        //System.out.println(fieldCaption.getAttribute("class"));        
       // WebElement parentElement = (WebElement)js.executeScript("return arguments[0].parentNode;", fieldCaption);
        WebElement inputField = parentElement.findElement(By.tagName("input"));        
        //WebElement inputField = fieldCaption.findElement(By.xpath("following-sibling::input"));
        inputField.clear();
        inputField.sendKeys(valueToSet);
    }
  
    /*public void setDateFieldByCaption(String inputFieldCaption, String valueToSet){

    	this.TXT_Caption = inputFieldCaption;
        fullXpath = String.format("//div[div[text()='%s']]", this.TXT_Caption);
        WebElement fieldCaption = FindWebElements.findWebElementVisibleByXpath(fullXpath);
        WebElement inputField = fieldCaption.findElement(By.xpath("following-sibling::div/input"));
        inputField.clear();
        inputField.sendKeys(valueToSet);
    }*/
    
    
    private static void doHighlight(WebElement element) throws InterruptedException{
        for (int i = 0; i < 5; i++) {
            js.executeScript("arguments[0].style.border='3px solid red'", element);
            Thread.sleep(200);
            js.executeScript("arguments[0].style.border='0px'", element);
        }
    }
   
    
}
