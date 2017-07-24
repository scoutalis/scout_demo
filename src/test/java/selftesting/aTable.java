package selftesting;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import scout.alis.core.ui.LoginPage;
import scout.alis.core.ui.WebPage;
import scout.alis.core.ui.WebTable;
import scout.testdata.ExcelUtils;
import webdriver.Driver;
import webdriver.FindWebElements;

public class aTable {

	protected final static WebDriver DRIVER = Driver.instance;
	private static String projectDir = System.getProperty("user.dir");
	private static Properties props = loadProperties();
	private static JavascriptExecutor js =(JavascriptExecutor)DRIVER;
	
	public static void main(String[] args) throws Exception {
		 String sTestCaseName;
	        int iTestCaseRow=-1;
			String sProject = "TFL"; //props.getProperty("Project");		
			String path =  projectDir + "\\testData\\LoginData.xlsx" ;
			String sheetName = "LogIn";
			
			ExcelUtils.setExcelFile(path,sheetName);
			
	        if(sProject.equals("TFL")) {
	            iTestCaseRow = ExcelUtils.getRowContains("LogIn Alis TFL", 1);
	        }
	        else {
	            iTestCaseRow = ExcelUtils.getRowContains("LogIn Alis AF", 1);
	        }		
	                
	        String URL = ExcelUtils.getCellData(iTestCaseRow,"URL");
	        String userName = ExcelUtils.getCellData(iTestCaseRow,"UserName");
	        String password= ExcelUtils.getCellData(iTestCaseRow,"Password");
	        String dataBase= ExcelUtils.getCellData(iTestCaseRow,"DataBase");      
	        
			Driver.getInstance();
			Driver.getBaseUrl(URL);
			
			LoginPage.login(userName, password, dataBase);
			WebPage.waitLoading();
						
			String activePageName = WebPage.getActivePageName();
			Assert.assertEquals(activePageName, "User Dashboard");
					
			
			///////////////////////////////////////////
			//////////////////////////////////////////
			
			path =  projectDir + "\\testData\\clientData.xlsx" ;
			sheetName = "Client_Details";
			ExcelUtils.setExcelFile(path,sheetName);
			
			String Navigate = ExcelUtils.getCellData(iTestCaseRow,"Navigate");
			WebPage.selectAlisMenuItem(Navigate);		
			WebPage.waitLoading();
			
			activePageName = WebPage.getActivePageName();
			Assert.assertEquals(activePageName, "Client Details");
			/*
			String sTable = "Address Details";
			//String sTable = "Contact Details";
			
			    
	        String fullXpath = String.format("//div[div[text()='%s']]", sTable);
	        
	        System.out.println("Address Details");
	        WebElement tableCaption = FindWebElements.findWebElementVisibleByXpath(fullXpath);
	        
	        System.out.println(tableCaption.getAttribute("class"));
	        
	        System.out.println("parentElement");
	        WebElement parentElement = tableCaption.findElement(By.xpath("./.."));
	        System.out.println(parentElement.getAttribute("class"));
	        
	        System.out.println("nextsibling");
	        WebElement nextsibling = tableCaption.findElement(By.xpath("following-sibling::div"));
	        System.out.println(nextsibling.getAttribute("class"));
			
	        
	        
	        List <WebElement> headeritems =  nextsibling.findElements(By.className("v-table-caption-container"));
	        for ( WebElement item: headeritems) {
	        	System.out.println(item.getText());
	        }
			
	        */
	        WebTable table = new WebTable("Address Details");
	        
	        String[] colname = table.getTableHeaders();
	        
	        for(int i=0;i<colname.length;i++)
	        	System.out.println("col: " +colname[i]);
	        
	        table.addRow();
	        
//	        table.EditRow(1);
	        
	        table.DeleteRow(1);
	        
	        
			System.out.println("Done :) .....");


		}
		
		
	    public static Properties loadProperties(){
	        Properties props = new Properties();
	        String propertiesFile = projectDir + "\\testData\\config.properties";
	        try{
	            FileInputStream fs = new FileInputStream(propertiesFile);
	            props.load(fs);
	        }catch (IOException e){
	            System.out.println (e.toString());
	            System.out.println("Could not find file " + propertiesFile);
	        }

	        return props;
	    }

	    private static void doHighlight(WebElement element) throws InterruptedException{
	        for (int i = 0; i < 5; i++) {
	            js.executeScript("arguments[0].style.border='3px solid red'", element);
	            Thread.sleep(200);
	            js.executeScript("arguments[0].style.border='0px'", element);
	        }
	    }
	    
	}