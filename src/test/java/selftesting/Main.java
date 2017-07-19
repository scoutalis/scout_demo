package selftesting;


import webdriver.Driver;

public class Main {

	public static void main(String[] args) {
		
		Driver.getInstance();
		Driver.getBaseUrl();
		Driver.closeBrowser();

	}

}
