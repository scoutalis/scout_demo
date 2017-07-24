package scout.alis.core.ui;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import webdriver.Driver;
import webdriver.FindWebElements;

public class WebTable {
	
	protected final static WebDriver DRIVER = Driver.instance;
	private WebElement TableObject= null ;
	
	private String TXT_Caption="";
	private String TXT_id;
	

	public WebTable(String txt_Caption) {
		this.TXT_Caption = txt_Caption;
		this.TableObject = findTableByCaption(txt_Caption);
	}
	
	public String[] getTableHeaders(){
		
		String[]headerArray = null;
        List <WebElement> headeritems =  this.TableObject.findElements(By.className("v-table-caption-container"));
        
        headerArray = new String[headeritems.size()];
        int iheader =0;
        for ( WebElement item: headeritems) {
        	headerArray[iheader] =item.getText();
        	iheader++;
        }        
		return headerArray;
	}
	
	public void addRow(){
		
		String fullXpath = String.format("//span[span[text()='%s']]", "Add"); 
		WebElement tableAdd = this.TableObject.findElement(By.xpath(fullXpath)) ;
		tableAdd.click();		
	
	}
	
	public void EditRow(int RowNumber){
		
		String fullXpath = String.format("//span[span[text()='%s']]", "Edit"); 
		WebElement tableAdd = this.TableObject.findElement(By.xpath(fullXpath)) ;
		tableAdd.click();		
	
	}
	
	
	public void DeleteRow(int RowNumber){
		
		String fullXpath = String.format("//span[span[text()='%s']]", "Delete"); 
		WebElement tableAdd = this.TableObject.findElement(By.xpath(fullXpath)) ;
		tableAdd.click();		
	
	}
	
	
	private WebElement findTableByCaption(String sCaption)
	{
        String fullXpath = String.format("//div[div[text()='%s']]", sCaption);        
        WebElement tableCaption = FindWebElements.findWebElementVisibleByXpath(fullXpath);        
        //WebElement parentElement = tableCaption.findElement(By.xpath("./.."));
        WebElement nextsibling = tableCaption.findElement(By.xpath("following-sibling::div"));
		
		return nextsibling;
	}
	
	
	
	    
}
