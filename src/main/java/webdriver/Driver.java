package webdriver;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;




public class Driver {

    public static WebDriver instance = null;
    private static WebElement element = null;
    
    private static String projectDir = System.getProperty("user.dir");
    private static Properties props = loadProperties();

    private Driver(){}

    private static Properties loadProperties(){
        Properties props = new Properties();
        //String propertiesFile = projectDir + "\\src\\main\\webdrivers\\config.properties";
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

    public static void getInstance(){

        String browser = props.getProperty("browser");
        if(instance == null){
            if(browser.equalsIgnoreCase("chrome")){
                System.setProperty("webdriver.chrome.driver", projectDir + "\\src\\main\\webdrivers\\chromedriver.exe");
                instance = new ChromeDriver();
            }
            else if(browser.equalsIgnoreCase("firefox")){
                System.setProperty("webdriver.gecko.driver", projectDir + "\\src\\main\\webdrivers\\geckodriver.exe");
                instance = new FirefoxDriver();
            }
            else if(browser.equalsIgnoreCase("ie")){
                System.setProperty("webdriver.ie.driver", projectDir + "\\src\\main\\webdrivers\\IEDriverServer.exe");
                instance = new InternetExplorerDriver();
            }else if(browser.equalsIgnoreCase("edge")){
                System.setProperty("webdriver.edge.driver", projectDir + "\\src\\main\\webdrivers\\MicrosoftWebDriver.exe");
                instance = new EdgeDriver();
            }

            instance.manage().window().maximize();
            instance.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }

    }

    public static void getBaseUrl(String sURL){
        //instance.get(props.getProperty("baseUrl"));
    	instance.get(sURL);
    }

    public static void closeBrowser(){
        instance.quit();
    }    
      
    
}