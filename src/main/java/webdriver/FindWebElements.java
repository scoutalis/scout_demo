package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FindWebElements {
	


    private static WebDriver DRIVER = Driver.instance;
    private static WebElement element;
    private static WebDriverWait wait = new WebDriverWait(DRIVER, 3);

    public static WebElement findWebElementVisibleById(String selector){
        try{
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(selector)));
        }catch(TimeoutException e){
            element = null;
        }
        return element;
    }

    public static WebElement findWebElementVisibleByClassName(String selector){
        try{
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(selector)));
        }catch(TimeoutException e){
            element = null;
        }
        return element;
    }

    public static WebElement findWebElementVisibleByCssSelector(String selector){
        try{
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(selector)));
        }catch (TimeoutException e){
            element = null;
        }
        return element;
    }

    public static WebElement findWebElementVisibleByXpath(String selector){
        try{
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(selector)));
        }catch (TimeoutException e){
            element = null;
        }
        return element;
    }

    public static WebElement findLoadingIndicator(){
        try {
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("v-loading-indicator")));
        }catch(TimeoutException e){
            element = null;
        }
        return element;
    }

    public static void waitLoading(){
        while(findLoadingIndicator() != null){
           DRIVER.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }
    }
	

}
