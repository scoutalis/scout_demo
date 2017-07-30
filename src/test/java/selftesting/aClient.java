package selftesting;

//import library.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import scout.alis.business.LoginPage;
import scout.alis.core.ui.WebButton;
import scout.alis.core.ui.WebComboBox;
import scout.alis.core.ui.WebEdit;
import scout.alis.core.ui.WebPage;
import scout.alis.core.ui.WebTable;
import scout.testdata.ExcelUtils;
import webdriver.Driver;
import webdriver.FindWebElements;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class aClient {

	//x
	protected static WebDriver DRIVER; 
	private static String projectDir = System.getProperty("user.dir");
	//private static Properties props = loadProperties();
	//private static JavascriptExecutor js =(JavascriptExecutor)DRIVER;
	
	public static ExtentReports extent ;
	public static ExtentTest test;
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub		
			
		Driver.getInstance();
		DRIVER = Driver.instance; 
		
		WebDriverWait wait = new WebDriverWait(DRIVER, 60);
		
		extent = new ExtentReports("C:\\Selenuim\\reports\\TestResult.html",true);
		test = extent.startTest("Client");
		
		test.log(LogStatus.INFO, "open browser");


        /*String screenshot_path=Utility.captureScreenshot(DRIVER, "Error");
        String image= test.addScreenCapture(screenshot_path);
        test.log(LogStatus.PASS, "open browser");*/
        
		
		
		String sProject = "TFL"; //props.getProperty("Project");
		
		
			//String sTestCaseName;
	        int iTestCaseRow=-1;	
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
			
			//Driver.getBaseUrl("https://www.google.co.il");
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
			// insert Data using hishtable - Map
			/////////////////////////////////////
			/*
			Map<String, String> mapData = ExcelUtils.getDataToHasshTableMap(path, sheetName, iTestCaseRow,4);
			String sHeader;
			String sval;
					
			for(String key: mapData.keySet()){
				sHeader = key;
				sval = mapData.get(key).toString();
				if(!sval.equals("")){
					String fullXpath = String.format("//div[div[text()='%s']]", sHeader);
			        WebElement fieldCaption = FindWebElements.findWebElementVisibleByXpath(fullXpath);
			        WebElement parentElement = fieldCaption.findElement(By.xpath("./.."));		        
			        WebElement field = parentElement.findElement(By.tagName("input"));
					
					String fieldClass = field.getAttribute("class");

					if(fieldClass.contains("v-textfield")){
					WebEdit alisTextField = new WebEdit(field);
					alisTextField.setText(sval);
					}
					else if (fieldClass.contains("v-filterselect-input")){
						WebComboBox alisComboBox = new WebComboBox(field);
						alisComboBox.selectComboBoxItem(sval);
					}
				}
				
			}	
			
			*/
			
			
			// insert Data using Array[][]
			/////////////////////////////////////
			String[][] inputData = ExcelUtils.getDataToArray(path, sheetName, iTestCaseRow, 4); 

			String sHeader;
			String sval;
			for(int item=0;item<=inputData.length-1;item++){
				sHeader = inputData[item][0];
				sval = inputData[item][1];
				if(!sval.equals("")){
					String fullXpath = String.format("//div[div[text()='%s']]", sHeader);
			        WebElement fieldCaption = FindWebElements.findWebElementVisibleByXpath(fullXpath);
			        WebElement parentElement = fieldCaption.findElement(By.xpath("./.."));		        
			        WebElement field = parentElement.findElement(By.tagName("input"));
					
					String fieldClass = field.getAttribute("class");

					if(fieldClass.contains("v-textfield")){
					WebEdit alisTextField = new WebEdit(field);
					alisTextField.setText(sval);
					}
					else if (fieldClass.contains("v-filterselect-input")){
						WebComboBox alisComboBox = new WebComboBox(field);
						alisComboBox.selectComboBoxItem(sval);
					}
				}
			}
			
			
			sheetName = "Address_Details";
			WebTable AddressDetailstable = new WebTable("Address Details");
			String[][] inputDataAddressDetails = ExcelUtils.getDataToArray(path, sheetName, iTestCaseRow, 1);
			AddressDetailstable.insertDataIntoNewRow(inputDataAddressDetails);
			
			
			WebTable PaymentArrangementTable = new WebTable("Payment Arrangement");		
			PaymentArrangementTable.addRow();		
		
									
		       //Save
			WebButton btn = new WebButton();
			btn.clickAlisSaveButton();
	        wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(By.className("v-loading-indicator"))));

	        // close popup
	        WebElement elemnt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirm.ok")));
	        WebElement notificatrion = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirm.dialog")));
	        String Str =notificatrion.getText();

	        Pattern p = Pattern.compile("[0-9]+");
	        List<String> numbers = new ArrayList<String>();
	        Matcher m = p.matcher(Str);
	        while (m.find()) {
	            //int n = Integer.parseInt(m.group());
	            numbers.add(m.group());
	        }

	        elemnt.click();

	        // Close
	        btn.clickAlisCloseButton();

	        
	        
	        
	        
			extent.endTest(test);
			extent.flush();
			System.out.println("Client Number: "+numbers);
			System.out.println("Done :) .....");
		

	}
/*
	@AfterMethod
	public void tearDown(ITestResult result)
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
	
		String screenshot_path=Utility.captureScreenshot(driver, result.getName());
		String image= logger.addScreenCapture(screenshot_path);
		logger.log(LogStatus.FAIL, "Title verification", image);
	
	
		}
	
		report.endTest(logger);
		report.flush();
	
		DRIVER.get("C:\\Report\\LearnAutomation.html");
	}
	*/
	
	
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
    
}
