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
	private static JavascriptExecutor js =(JavascriptExecutor)DRIVER;	
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
		
		//String fullXpath = String.format("//span[span[text()='%s']]", "Add");
		String fullXpath = String.format("//div[span[span[text()='%s']]]", "Add");
		WebElement tableAdd = this.TableObject.findElement(By.xpath(fullXpath)) ;
		tableAdd.click();
		((JavascriptExecutor)DRIVER).executeScript("arguments[0].scrollIntoView(true);", this.TableObject);	
	}
	
	public void EditRow(int RowNumber){
		
		String fullXpath = String.format("//span[span[text()='%s']]", "Edit"); 
		WebElement tableAdd = this.TableObject.findElement(By.xpath(fullXpath)) ;
		tableAdd.click();	
		((JavascriptExecutor)DRIVER).executeScript("arguments[0].scrollIntoView(true);", this.TableObject);	
	}
	
	
	public void DeleteRow(int RowNumber){
		
		String fullXpath = String.format("//span[span[text()='%s']]", "Delete"); 
		WebElement tableAdd = this.TableObject.findElement(By.xpath(fullXpath)) ;
		tableAdd.click();	
		((JavascriptExecutor)DRIVER).executeScript("arguments[0].scrollIntoView(true);", this.TableObject);	
	}
	
	public void insertDataIntonewRow(Object data){
		
	}
	
	public void insertDataToRow(Object data , int row){
		
	}
	
	public String getCellValue(String Header , int Row){
		
		String[] ArrHeaders = this.getTableHeaders();
		int ifound = -1;
		for(int i=0;i<ArrHeaders.length;i++)
			if(ArrHeaders[i].equals(Header)){
				ifound = i;
				break;
			}
							
		
		
		return "d";
	}
	
	public String getCellValue(int column , int Row){
		
		return "";
	}
	
			
	
	private WebElement findTableByCaption(String sCaption)
	{
        String fullXpath = String.format("//div[div[text()='%s']]", sCaption);        
        WebElement tableCaption = FindWebElements.findWebElementVisibleByXpath(fullXpath);        
        //WebElement parentElement = tableCaption.findElement(By.xpath("./.."));
        WebElement nextsibling = tableCaption.findElement(By.xpath("following-sibling::div"));
		
		return nextsibling;
	}
	
	public List<WebElement> getTableRows(){
		String fullXpath = String.format("//tr[text()='%s']", ".*v-table-row.*");
		
		//List<WebElement> list = this.TableObject.findElements(By.className("v-table-row"));
		
		List<WebElement> list = this.TableObject.findElements(By.xpath("//tr[contains(@class, 'v-table-row')]"));
		return list;		
	}
	
	
	
	    
}
