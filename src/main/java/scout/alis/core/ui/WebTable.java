package scout.alis.core.ui;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import webdriver.Driver;

public class WebTable {
	
	protected final static WebDriver DRIVER = Driver.instance;
	private static JavascriptExecutor js =(JavascriptExecutor)DRIVER;
	private static WebElement element = null;
	private static String fullXpath = null;
	
	private String TXT_Caption="";
	private String TXT_id;
	

	public WebTable(String txt_Caption) {
		this.TXT_Caption = txt_Caption;
	}
	
/*	public String[] getTableHeaders(){
		
		return ;
	}
	
	
	private WebElement findTableByCaption(String sCaption)
	{
		String fullXpath = String.format("//div[div[text()='%s']]", sTable);	
	}*/
}
