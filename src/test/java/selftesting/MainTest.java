package selftesting;

import org.testng.annotations.Test;
import webdriver.Driver;

public class MainTest {

  @Test
  public void main() {

	Driver.getInstance();
	Driver.getBaseUrl();
	Driver.closeBrowser();
    
    
  }
}
