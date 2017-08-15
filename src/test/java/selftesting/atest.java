package selftesting;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import scout.alis.business.LoginPage;
import scout.alis.core.ui.WebComboBox;
import scout.alis.core.ui.WebEdit;
import scout.alis.core.ui.WebPage;
import scout.alis.core.ui.WebTable;
import scout.testdata.ExcelUtils;
import webdriver.Driver;
import webdriver.FindWebElements;

public class atest {
	
	private static String projectDir = System.getProperty("user.dir");
	//private static Properties props = loadProperties();
	
	public static void main(String[] args) throws Exception {


       // String sTestCaseName;
        int iTestCaseRow=-1;
		String sProject = "AF"; //props.getProperty("Project");		
		String path =  projectDir + "\\testData\\LoginData.xlsx" ;
		String sheetName = "LogIn";
		
		ExcelUtils.setExcelFile(path,sheetName);
		
        if(sProject.equals("TFL")) {
            iTestCaseRow = ExcelUtils.getRowContains("LogIn Alis TFL", 1);
        }
        else {
            iTestCaseRow = ExcelUtils.getRowContains("LogIn Alis AF", 1);
        }		
        
        //get Excel data to Array
       //String[][]arr = ExcelUtils.getDataToArray(path, sheetName, 1,1);
        
        
        // get excel data to Map
       // Map<String, String> mapData = ExcelUtils.getDataToHasshTableMap(path, sheetName, 1,4);
        
        
                
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

    	
        /*String fullXpath = String.format("//div[div[text()='%s']]", "Address Details");
        WebElement fieldCaption = FindWebElements.findWebElementVisibleByXpath(fullXpath);

        WebElement parentElement = fieldCaption.findElement(By.xpath("./.."));	*/
		
		
		
		
		
		
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
		if(sProject.equals("TFL")) 
			AddressDetailstable.tableFormat = 1;
		else
			AddressDetailstable.tableFormat = 2;
		
		AddressDetailstable.insertDataIntoNewRow(inputDataAddressDetails);
		
		
		WebTable PaymentArrangementTable = new WebTable("Payment Arrangement");
		PaymentArrangementTable.addRow();
		
		
		/*
		String[]dataHeaders = ExcelUtils.getInputDataHeaders(path,sheetName);		
		String sHeader;
		String sval;
		for(int item=4;item<=dataHeaders.length-1;item++){
			sHeader = dataHeaders[item];
			sval = ExcelUtils.getCellData(iTestCaseRow,sHeader);
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
		
		
		
		
		/*
		WebTable AddressDetailstable = new WebTable("Address Details");
        String[][] data = new String[5][2];
        data[0][0] ="Address Type";
        data[0][1] ="Physical";	    	      	        		 
        data[1][0] ="Address Line 1";
        data[1][1] ="Address Line 1";	
        data[2][0] ="City";
        data[2][1] ="Test";	
        data[3][0] ="State";
        data[3][1] ="IL Illinois";	
        data[4][0] ="Zip Code";
        data[4][1] ="1234";
        AddressDetailstable.insertDataIntoNewRow(data);
        */
        
		/*WebTable PaymentArrangementTable = new WebTable("Payment Arrangement");
		PaymentArrangementTable.addRow();*/
		
		
        /*String[][] data1 = new String[5][2];
        data1[0][0] ="Address Type";
        data1[0][1] ="Physical";	    	      	        		 
        data1[1][0] ="Address Line 1";
        data1[1][1] ="Address Line 1";	
        data1[2][0] ="City";
        data1[2][1] ="Test";	
        data1[3][0] ="State";
        data1[3][1] ="IL Illinois";	
        data1[4][0] ="Zip Code";
        data1[4][1] ="1234";
        PaymentArrangementTable.insertDataIntoNewRow(data1);*/
        
        
        
        
		
		System.out.println("Done :) .....");
		
		/*WebEdit webedit = new WebEdit();		
		webedit.setTXTCaption("First Name");
		webedit.setText("John156324");

		
		WebPage.alisTextField.setTextFieldByCaption("First Name", "John 2307171600");
		WebPage.alisTextField.setTextFieldByCaption("Middle Name", "Maininsured");
		WebPage.alisTextField.setTextFieldByCaption("Last Name", "Fifty");
		WebPage.alisTextField.setDateFieldByCaption("Date Of Birth", "01/01/1965");
		WebPage.alisComboBox.setComboBoxByCaption("Gender", "male");
		WebPage.alisComboBox.setComboBoxByCaption("Tobacco", "No");
		WebPage.alisCheckBox.setCheckboxTrueByLabelName("Non Driver");	*/	

		
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
    

}
