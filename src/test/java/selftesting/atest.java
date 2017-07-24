package selftesting;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import scout.alis.core.ui.LoginPage;
import scout.alis.core.ui.WebEdit;
import scout.alis.core.ui.WebPage;
import scout.testdata.ExcelUtils;
import webdriver.Driver;
import webdriver.FindWebElements;

public class atest {
	
	private static String projectDir = System.getProperty("user.dir");
	private static Properties props = loadProperties();
	
	public static void main(String[] args) throws Exception {


        String sTestCaseName;
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

		
		
    	
        String fullXpath = String.format("//div[div[text()='%s']]", "Address Details");
        WebElement fieldCaption = FindWebElements.findWebElementVisibleByXpath(fullXpath);

        WebElement parentElement = fieldCaption.findElement(By.xpath("./.."));
		
		
		
		
		
		
		String[]dataHeaders = ExcelUtils.getInputDataHeaders(path,sheetName);
		
		String sHeader;
		String sval;
		for(int item=4;item<=dataHeaders.length-1;item++){
			sHeader = dataHeaders[item];
			sval = ExcelUtils.getCellData(iTestCaseRow,sHeader);
			if(!sval.equals(""))
				WebPage.alisTextField.setTextFieldByCaption(sHeader, sval);				
		}
		
		System.out.println("Done :) .....");
		
		/**WebEdit webedit = new WebEdit();		
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
