package Utilities;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {
	static String addressMe = "Mr";
	static String experience = "0 - 2";
	static String userName = "Abdul Jamadar";

	public static WebDriver getDriver() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		return driver;
	}

	public static void sendKeys(WebElement el, String str) {
		el.clear();
		el.sendKeys(str);
	}

	public static void fillGoogleForm(WebDriver driver) throws InterruptedException {

		// Navigate to url
		driver.get(
				"https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");

		// Enter username
		WebElement nameTextbox = driver
				.findElement(By.xpath("//span[text()='Name']/ancestor::div[@role='listitem']//input"));
		sendKeys(nameTextbox, userName);

		// Enter text in question BOx
		WebElement questionBox = driver.findElement(By.xpath(
				"//span[text()='Why are you practicing Automation?']/ancestor::div[@role='listitem']//textarea"));
		sendKeys(questionBox, "I want to be the best QA Engineer! " + System.currentTimeMillis());

		// Enter the experience
		WebElement experienceLocator = driver.findElement(By.xpath(
				"//span[text()='How much experience do you have in Automation Testing?']/ancestor::div[@role='listitem']//span[text()='0 - 2']"));
		experienceLocator.click();

		// Select the learnings
		String crioLearnings[] = { "Java", "Selenium", "TestNG" };
		for (String str : crioLearnings) {
			driver.findElement(By.xpath(
					"//span[text()='Which of the following have you learned in Crio.Do for Automation Testing?']/ancestor::div[@role='listitem']//span[text()='"
							+ str + "']"))
					.click();
		}

		// Select your Mr/Mrs/Ms
		WebElement dropdown = driver.findElement(By.xpath(
				"//span[text()='How should you be addressed?']/ancestor::div[@role='listitem']//*[text()='Choose']"));
		dropdown.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@jsname='wQNmvb' and @role='option']//span[text()='Mr']")).click();

		// Enter current date-7 into date textbox.
		LocalDate date = java.time.LocalDate.now();
		String strDate[] = date.minusDays(7).toString().split("-");
		String year = strDate[0];
		String month = strDate[1];
		String day = strDate[2];

		WebElement dateLocator = driver.findElement(By.xpath("//input[@type='date']"));
		sendKeys(dateLocator, day + month + year);

		// Enter current time
		LocalTime time = java.time.LocalTime.now();
		String strTime[] = time.toString().split(":");
		int hour = Integer.parseInt(strTime[0]);
		String format = "AM";
		if (hour > 12) {
			hour -= 12;
			format = "PM";
		}
		if (hour == 0) {
			hour = 12;
			format = "AM";
		}
		sendKeys(driver.findElement(By.cssSelector("input[aria-label=Hour]")), "" + hour);
		sendKeys(driver.findElement(By.cssSelector("input[aria-label=Minute]")), "" + strTime[1]);

		// Select AM/PM time zone
		try {
			driver.findElement(By.xpath("//div[@aria-label='AM or PM']")).click();
			driver.findElement(By.xpath("//div[@role='option']/span[text()='" + format + "']")).click();
		} catch (Exception e) {
			System.out.println("AM/PM dropdown not available!");
		}

		//Click on submit
		driver.findElement(By.xpath("//span[text()='Submit']")).click();

	}
}
