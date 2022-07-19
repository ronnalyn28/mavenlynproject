package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveWithNotesTests {

	@Test // import org.testng.annotations.Test
	public void loginTest() {
		// Display on Console
		System.out.println("Staring loginTest.");

		// Create Driver for Chrome
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
			// WebDriver is import org.openqa.selenium.WebDriver;
			// ChromeDriver() is import org.openqa.selenium.chrome.ChromeDriver;

		// Maximize Browser Window
		driver.manage().window().maximize();

		// Load Page
		String url = "https://the-internet.herokuapp.com/login";
		driver.get(url);
		System.out.println("Login Page is opened.");

		// Sleep for 1.5secs - to slow down display
		sleepDisplay(1500);

		// Enter Username
		WebElement username = driver.findElement(By.id("username"));
			// id("username") - "username" is the id of field you get in f12 (Username field)
			// WebElement is import org.openqa.selenium.WebElement;
			// By is import org.openqa.selenium.By;
		username.sendKeys("tomsmith");

		// Enter Password
		WebElement password = driver.findElement(By.id("password"));
			// id("password") - "password" is the id of field you get in f12 (PW Field)
		password.sendKeys("SuperSecretPassword!");

		// Sleep for 1.5secs - to slow down display
		sleepDisplay(1500);

		// Click Login Button
		WebElement loginButton = driver.findElement(By.tagName("button"));
			// tagName("button")- "button" came from F12 can be used if there is only 1 button on page (Login button)
		loginButton.click();

		// Sleep for 2secs - to slow down display
		sleepDisplay(2000);

		// Page Verification - Checking URL after login is correct
		String expectedUrl = "https://the-internet.herokuapp.com/secure";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual Page URL not the same as Expected.");
			// Assert is import org.testng.Assert;
			// This compares actual url that loads vs expected, and if fails, msg is displayed

		// Page Verification - Checking if Logout button is available
		WebElement logoutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
			// xpath("//a[@class='button secondary radius']" - value of "//a[@class='button secondary radius']"
					// is the value you created (using Ranorex) in F12-Console for Logout Button
		Assert.assertTrue(logoutButton.isDisplayed(), "Logout Button is not visible.");
			// Checks if Logout Button is displayed, if not, msg is displayed

		// Page Verification - Checking Successful Login Message
		WebElement SuccessLogin = driver.findElement(By.cssSelector("#flash"));
			// cssSelector("#flash")- #flash is the css copied using Ranorex (Success Msg after Login)
		String expectedSuccessMsg = "You logged into a secure area!";
		String actualSuccessMsg = SuccessLogin.getText();
			// Assert.assertEquals(actualSuccessMsg, expectedSuccessMsg, "Actual Login
			// Success Message is not the same as Expected.");
		Assert.assertTrue(actualSuccessMsg.contains(expectedSuccessMsg),
				"Actual Login Success Message is not the same as Expected.\nActual Success Message: " + actualSuccessMsg
						+ "\nExpected Success Message: " + expectedSuccessMsg);
			// Better to use this instead of .assertEquals since success toastr has x icon to close the alert

		System.out.println("Login successful.");
		
		// Close Browser 
		driver.quit();
		
	}

	
	private void sleepDisplay(long m) {
		// using 'm' so you can define milliseconds based on your preference
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
