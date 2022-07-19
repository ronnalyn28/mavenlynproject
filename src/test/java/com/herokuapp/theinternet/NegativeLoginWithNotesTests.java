package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeLoginWithNotesTests {
	
	@Test(priority = 1, groups = {"NegativeLogin", "PositiveLogin"})
		//priority = 1 means this will be executed first
		//comment @Test out if you just want to run one method
	public void incorrectUsernameTest(){
		// Display on Console
		System.out.println("Staring Test of Incorrect Username.");

		// Create Driver for Firefox
		System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		// FirefoxDriver() is import org.openqa.selenium.firefox.FirefoxDriver;;
		
		// Maximize Browser Window
		driver.manage().window().maximize();
		
		// Load Page
		String url = "https://the-internet.herokuapp.com/login";
		driver.get(url);
		System.out.println("Login Page is opened.");

		// Sleep for 1.5secs - to slow down display
		sleepDisplay(1500);

		// Enter Incorrect Username
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("ronnaumbal");

		// Enter Correct Password
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("SuperSecretPassword!");

		// Sleep for 1.5secs - to slow down display
		sleepDisplay(1500);

		// Click Login Button
		WebElement loginButton = driver.findElement(By.tagName("button"));
		loginButton.click();
		
		// Page Verification - Checking Error Message Displayed
		WebElement IncorrectUserNameMsg = driver.findElement(By.cssSelector("#flash"));
		String expectedErrorMsg = "Your username is invalid!";
		String actualErrorMsg = IncorrectUserNameMsg.getText();
		Assert.assertTrue(actualErrorMsg.contains(expectedErrorMsg),
			"Actual Error Message is not the same as Expected.\nActual Error Message: " + actualErrorMsg
					+ "\nExpected Error Message: " + expectedErrorMsg);
		
		// Close Browser 
		driver.quit();
		
	}

	@Test(priority = 2, groups = {"NegativeLogin"})
	//@Test(priority = 2, enabled = false)
		//priority = 2 means this will be executed 2nd
		//enabled = false means this method is disabled and will not be executed
	public void incorrectPasswordTest(){
		// Display on Console
		System.out.println("Staring Test of Incorrect Password.");

		// Create Driver for Chrome
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		// Maximize Browser Window
		driver.manage().window().maximize();
		
		// Load Page
		String url = "https://the-internet.herokuapp.com/login";
		driver.get(url);
		System.out.println("Login Page is opened.");

		// Sleep for 1.5secs - to slow down display
		sleepDisplay(1500);

		// Enter Correct Username
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");

		// Enter Incorrect Password
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("Incorrect@1234!");

		// Sleep for 1.5secs - to slow down display
		sleepDisplay(1500);

		// Click Login Button
		WebElement loginButton = driver.findElement(By.tagName("button"));
		loginButton.click();
		
		// Page Verification - Checking Error Message Displayed
		WebElement IncorrectPasswordMsg = driver.findElement(By.cssSelector("#flash"));
		String expectedErrorMsg = "Your password is invalid!";
		String actualErrorMsg = IncorrectPasswordMsg.getText();
		Assert.assertTrue(actualErrorMsg.contains(expectedErrorMsg),
			"Actual Error Message is not the same as Expected.\nActual Error Message: " + actualErrorMsg
					+ "\nExpected Error Message: " + expectedErrorMsg);
		
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
