package scout.alis.core.ui;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
	
	
	// 1 - ALIS 6
	// 2 - ALIS 7
	public int tableFormat = 1;
	

	public WebTable(String txt_Caption) {
		this.TXT_Caption = txt_Caption;
		this.TableObject = findTableByCaption(txt_Caption);
	}
	
///////////////////////////////////////////////////////////	
	public String[] getTableHeaders(){
		
		String[]headerArray = null;
        //6631
		List <WebElement> headeritems=null;
		
		switch (this.tableFormat) {
        case 1:
        	headeritems =  this.TableObject.findElements(By.className("v-table-caption-container"));
        	break;
        case 2:
        	headeritems =  this.TableObject.findElements(By.tagName("th"));
        	break;
        default:	
        	headeritems =  this.TableObject.findElements(By.className("v-table-caption-container"));
        	break;
		}
		
		/*
		headeritems =  this.TableObject.findElements(By.className("v-table-caption-container"));        
		//7.0
		if(headeritems.size()==0) headeritems =  this.TableObject.findElements(By.tagName("th"));
        */
        
        headerArray = new String[headeritems.size()];
        int iheader =0;
        for ( WebElement item: headeritems) {
        	headerArray[iheader] =item.getText();
        	iheader++;
        }        
		return headerArray;
	}
///////////////////////////////////////////////////////////	
	public void addRow(){
		
		//String fullXpath = String.format("//span[span[text()='%s']]", "Add");
		String fullXpath = String.format(".//div[span[span[text()='%s']]]", "Add");
		WebElement tableAdd = this.TableObject.findElement(By.xpath(fullXpath)) ;
		tableAdd.click();
		((JavascriptExecutor)DRIVER).executeScript("arguments[0].scrollIntoView(true);", this.TableObject);	
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
///////////////////////////////////////////////////////////	
	public void EditRow(int RowNumber){
		
		String fullXpath = String.format(".//span[span[text()='%s']]", "Edit"); 
		WebElement tableAdd = this.TableObject.findElement(By.xpath(fullXpath)) ;
		tableAdd.click();	
		((JavascriptExecutor)DRIVER).executeScript("arguments[0].scrollIntoView(true);", this.TableObject);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
///////////////////////////////////////////////////////////	
	public void DeleteRow(int RowNumber){
		
		String fullXpath = String.format(".//span[span[text()='%s']]", "Delete"); 
		WebElement tableAdd = this.TableObject.findElement(By.xpath(fullXpath)) ;
		tableAdd.click();			
		((JavascriptExecutor)DRIVER).executeScript("arguments[0].scrollIntoView(true);", this.TableObject);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
///////////////////////////////////////////////////////////	
	public void insertDataIntoNewRow(Map<String, String> inputData){
		
		this.addRow();
		int row = this.getTableRows().size();
		this.insertDataToRow(inputData, row);
		
	}

///////////////////////////////////////////////////////////	
	public void insertDataIntoNewRow(String[][] inputData){
	
		this.addRow();
		int row = this.getTableRows().size();
		this.insertDataToRow(inputData, row);
	
	}
	
///////////////////////////////////////////////////////////	
	public void insertDataToRow(Map<String, String> inputData , int row){	

		for(String key: inputData.keySet()){
			//System.out.println(key + " - " + inputData.get(key));
			this.setCellValue(key, row, inputData.get(key).toString());
		}			
	}
///////////////////////////////////////////////////////////	
	public void insertDataToRow(String[][] inputData , int row){	
	
		int Lin = inputData.length;
		
		for(int i=0;i<Lin;i++){
			//System.out.println(inputData[i][0] + " - " + inputData[i][1]);
			this.setCellValue(inputData[i][0], row, inputData[i][1]);
		}
	
	}	
///////////////////////////////////////////////////////////	
	public String getCellValue(String headerName , int rowNumber){
		
		String[] ArrHeaders = this.getTableHeaders();
		int ifound = -1;
		for(int i=0;i<ArrHeaders.length;i++)
			if(ArrHeaders[i].equals(headerName)){
				ifound = i;
				break;
			}
		
		if(ifound>-1){
			List<WebElement> tableRows = this.getTableRows();
			
			if (rowNumber>0 && rowNumber<=tableRows.size()){			
				WebElement tableRow = tableRows.get(rowNumber-1);
				List<WebElement> tableCells = tableRow.findElements(By.xpath(".//td[contains(@class, 'v-table-cell-content')]"));			
				return tableCells.get(ifound).findElement(By.tagName("input")).getAttribute("value");
			}
		}		
		return null;
	}
	
///////////////////////////////////////////////////////////
	public String getCellValue(int columnNumber , int rowNumber){
		
		String[] ArrHeaders = this.getTableHeaders();
		
		if(columnNumber>=0 && columnNumber <= ArrHeaders.length+1){
			List<WebElement> tableRows = this.getTableRows();
			if (rowNumber>0 && rowNumber<=tableRows.size()){
				WebElement tableRow = tableRows.get(rowNumber-1);
				List<WebElement> tableCells = tableRow.findElements(By.xpath(".//td[contains(@class, 'v-table-cell-content')]"));			
				return tableCells.get(columnNumber-1).findElement(By.tagName("input")).getAttribute("value");			
			}		
			
		}
				
		return "";
	}

	
///////////////////////////////////////////////////////////	
	public void setCellValue(String headerName , int rowNumber,String value){
	
		String[] ArrHeaders = this.getTableHeaders();
		int ifound = -1;
		
		for(int i=0;i<ArrHeaders.length;i++)
			if(ArrHeaders[i].equals(headerName)){
			ifound = i;
			break;
		}
		
		if(ifound>-1){
			List<WebElement> tableRows = this.getTableRows();			
			if (rowNumber>0 && rowNumber<=tableRows.size()){			
				WebElement tableRow = tableRows.get(rowNumber-1);

				List<WebElement> tableCells = null;
/*				
				tableCells = tableRow.findElements(By.xpath(".//td[contains(@class, 'v-table-cell-content')]"));
				
				if(tableCells.size()==0) tableCells = this.TableObject.findElements(By.xpath(".//tbody[contains(@class, 'v-grid-body')]//tr[contains(@class, 'v-grid-row')]//td[contains(@class, 'v-grid-cell')]"));
*/
				
				switch (this.tableFormat) {
		        case 1:
		        	tableCells = tableRow.findElements(By.xpath(".//td[contains(@class, 'v-table-cell-content')]"));
		        	break;
		        case 2:
		        	tableCells = this.TableObject.findElements(By.xpath(".//tbody[contains(@class, 'v-grid-body')]//tr[contains(@class, 'v-grid-row')]//td[contains(@class, 'v-grid-cell')]"));
		        	break;
		        default:	
		        	tableCells = tableRow.findElements(By.xpath(".//td[contains(@class, 'v-table-cell-content')]"));
		        	break;
				}					
				
				
				String tableCellClass = tableCells.get(ifound).findElement(By.tagName("input")).getAttribute("class");

				if(tableCellClass.contains("v-textfield")){
				WebEdit alisTextField = new WebEdit(tableCells.get(ifound).findElement(By.tagName("input")));
				alisTextField.setText(value);
				}
				else if (tableCellClass.contains("v-filterselect-input")){
					WebComboBox alisComboBox = new WebComboBox(tableCells.get(ifound).findElement(By.tagName("input")));
					alisComboBox.selectComboBoxItem(value);
				}
			}
		}
		
	}	
			
///////////////////////////////////////////////////////////
	private WebElement findTableByCaption(String sCaption)
	{
        String fullXpath = String.format("//div[div[text()='%s']]", sCaption);        
        WebElement tableCaption = FindWebElements.findWebElementVisibleByXpath(fullXpath);        
        //WebElement parentElement = tableCaption.findElement(By.xpath("./.."));
        WebElement nextsibling = tableCaption.findElement(By.xpath("following-sibling::div"));
		
		return nextsibling;
	}
///////////////////////////////////////////////////////////
	private List<WebElement> getTableRows(){
		//String fullXpath = String.format("//tr[text()='%s']", ".*v-table-row.*");		
		//List<WebElement> list = this.TableObject.findElements(By.className("v-table-row"));
		/*
		//ALIS 6631
		List<WebElement> list = this.TableObject.findElements(By.xpath(".//tr[contains(@class, 'v-table-row')]"));
		//ALIS 7.0
		if(list.size()==0) list = this.TableObject.findElements(By.xpath(".//tbody[contains(@class, 'v-grid-body')]//tr[contains(@class, 'v-grid-row')]"));
*/
		List<WebElement> list = null;
		switch (this.tableFormat) {
        case 1:
        	list = this.TableObject.findElements(By.xpath(".//tr[contains(@class, 'v-table-row')]"));
        	break;
        case 2:
        	list = this.TableObject.findElements(By.xpath(".//tbody[contains(@class, 'v-grid-body')]//tr[contains(@class, 'v-grid-row')]"));
        	break;
        default:	
        	list = this.TableObject.findElements(By.xpath(".//tr[contains(@class, 'v-table-row')]"));
        	break;
		}
		
		
		return list;		
	}
	
	
	
	    
}
