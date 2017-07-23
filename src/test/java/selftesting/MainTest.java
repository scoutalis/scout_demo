package selftesting;

import org.testng.Assert;
import org.testng.annotations.Test;

import scout.alis.core.ui.WebPage;
import webdriver.Driver;

public class MainTest {

  @Test
  public void main() {

	Driver.getInstance();
	Driver.getBaseUrl();	
	Driver.closeBrowser();
    
    
  }
}
