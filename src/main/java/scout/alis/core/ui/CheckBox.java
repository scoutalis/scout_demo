package scout.alis.core.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import webdriver.Driver;
import webdriver.FindWebElements;

public class CheckBox {
	
	protected final static WebDriver DRIVER = Driver.instance;
	private static WebElement element = null;
	private static String fullXpath = null;
	
    public static void clickChecboxByLabelName(String labelName){
        fullXpath = String.format("//span[contains(@class, 'v-checkbox')][label[.='%s']]", labelName);
        WebElement checkBoxWithLabel = FindWebElements.findWebElementVisibleByXpath(fullXpath);
        WebElement checkBoxInputField = checkBoxWithLabel.findElement(By.tagName("input"));
        checkBoxInputField.click();
    }

    public static void clickCheckbox(WebElement checkbox){
        WebElement checkBoxInputField = checkbox.findElement(By.tagName("input"));
        checkBoxInputField.click();
    }

    public static void setCheckboxTrueByLabelName(String labelName){
        fullXpath = String.format("//span[contains(@class, 'v-checkbox')][label[.='%s']]", labelName);
        WebElement checkBoxWithLabel = FindWebElements.findWebElementVisibleByXpath(fullXpath);
        WebElement checkBoxInputField = checkBoxWithLabel.findElement(By.tagName("input"));
        if(checkBoxInputField.getAttribute("checked") == null){
            clickChecboxByLabelName(labelName);
        }
    }

    public static void setCheckboxTrue(WebElement checkbox){
        WebElement checkBoxInputField = checkbox.findElement(By.tagName("input"));
        if(checkBoxInputField.getAttribute("checked") == null){
            checkBoxInputField.click();
        }
    }

    public static void setCheckboxFalseByLabelName(String labelName){
        fullXpath = String.format("//span[contains(@class, 'v-checkbox')][label[.='%s']]", labelName);
        WebElement checkBoxWithLabel = FindWebElements.findWebElementVisibleByXpath(fullXpath);
        WebElement checkBoxInputField = checkBoxWithLabel.findElement(By.tagName("input"));
        if(checkBoxInputField.getAttribute("checked") != null){
            clickChecboxByLabelName(labelName);
        }
    }

    public static void setCheckboxFalse(WebElement checkbox){
        WebElement checkBoxInputField = checkbox.findElement(By.tagName("input"));
        if(checkBoxInputField.getAttribute("checked") != null){
            checkBoxInputField.click();
        }
    }

    public static Boolean isCheckboxSetTrueByLabelName(String labelName){
        fullXpath = String.format("//span[contains(@class, 'v-checkbox')][label[.='%s']]", labelName);
        WebElement checkBoxWithLabel = FindWebElements.findWebElementVisibleByXpath(fullXpath);
        WebElement checkBoxInputField = checkBoxWithLabel.findElement(By.tagName("input"));
        if(checkBoxInputField.getAttribute("checked") == null){
            return false;
        }
        return true;
    }

    public static Boolean isCheckboxSetTrue(WebElement checkbox){
        WebElement checkBoxInputField = checkbox.findElement(By.tagName("input"));
        if(checkBoxInputField.getAttribute("checked") == null){
            return false;
        }
        return true;
    }

    public static Boolean isCheckboxSetFalseByLabelName(String labelName){
        fullXpath = String.format("//span[contains(@class, 'v-checkbox')][label[.='%s']]", labelName);
        WebElement checkBoxWithLabel = FindWebElements.findWebElementVisibleByXpath(fullXpath);
        WebElement checkBoxInputField = checkBoxWithLabel.findElement(By.tagName("input"));
        if(checkBoxInputField.getAttribute("checked") != null){
            return false;
        }
        return true;
    }

}
