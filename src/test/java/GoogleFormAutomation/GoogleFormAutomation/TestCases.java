package GoogleFormAutomation.GoogleFormAutomation;

import org.openqa.selenium.WebDriver;

import Utilities.BaseClass;

public class TestCases extends BaseClass {
	
	
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = getDriver();
		fillGoogleForm(driver);
	}
}
