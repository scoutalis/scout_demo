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
	
	private WebElement webEditObject = null;
	
	public WebEdit(){};
	
	public WebEdit(String FieldCaption){
    	this.TXT_Caption = FieldCaption;
        fullXpath = String.format("//div[div[text()='%s']]", FieldCaption);
        WebElement fieldCaption = FindWebElements.findWebElementVisibleByXpath(fullXpath);

        WebElement parentElement = fieldCaption.findElement(By.xpath("./.."));
        //WebElement inputField = parentElement.findElement(By.tagName("input"));
        
        this.webEditObject = parentElement.findElement(By.tagName("input"));
	}
	
	public WebEdit(WebElement webEdit){
		this.webEditObject = webEdit;
	}	

	
	
	public WebEdit initializeWebEdit(String txt_Caption){
		this.TXT_Caption = txt_Caption;
        fullXpath = String.format("//div[div[text()='%s']]", txt_Caption);
        WebElement fieldCaption = FindWebElements.findWebElementVisibleByXpath(fullXpath);

        WebElement parentElement = fieldCaption.findElement(By.xpath("./.."));
        this.webEditObject = parentElement.findElement(By.tagName("input"));	
        return this;
	}
	
	public void setText(String valueToSet){
		
		this.webEditObject.clear();
        this.webEditObject.sendKeys(valueToSet);		
	}
	
	
   /** public void setTextFieldByCaption(String inputFieldCaption, String valueToSet){
    	
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
    }*/
  
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
