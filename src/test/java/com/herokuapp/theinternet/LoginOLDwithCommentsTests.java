package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.herokuapp.theinternet.base.TestUtilities;

public class LoginOLDwithCommentsTests extends TestUtilities {
	@Parameters({"usernamePos", "passwordPos", "expectedUrl", "expectedSuccessMsg"})
	@Test(priority = 1)
	public void positiveLoginTest(String usernamePos, String passwordPos, String expectedUrl, String expectedSuccessMsg) {
		// Display on Console
		log.info("Starting Positive Login Test.");
		
		// Load Main Page
		String url = "https://the-internet.herokuapp.com";
		driver.get(url);
		log.info("Main Menu is opened.");
		
		// Load Login Page
		driver.findElement(By.linkText("Form Authentication")).click();
		
		// Sleep for 1.5secs - to slow down display
		sleepDisplay(1500);

		// Enter Username and Password
		driver.findElement(By.id("username")).sendKeys(usernamePos);
		driver.findElement(By.id("password")).sendKeys(passwordPos);

		// Sleep for 1.5secs - to slow down display
		sleepDisplay(1500);

		// Click Login Button
		WebElement loginButton = driver.findElement(By.tagName("button"));
		loginButton.click();

		// Sleep for 2secs - to slow down display
		sleepDisplay(2000);

		// Page Verification - Checking URL after login is correct
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual Page URL not the same as Expected.");

		// Page Verification - Checking if Logout button is available
		WebElement logoutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
		Assert.assertTrue(logoutButton.isDisplayed(), "Logout Button is not visible.");

		// Page Verification - Checking Successful Login Message
		WebElement SuccessLogin = driver.findElement(By.cssSelector("#flash"));
		String actualSuccessMsg = SuccessLogin.getText();
		Assert.assertTrue(actualSuccessMsg.contains(expectedSuccessMsg),
				"Actual Login Success Message is not the same as Expected.\nActual Success Message: " + actualSuccessMsg
						+ "\nExpected Success Message: " + expectedSuccessMsg);
			
		log.info("Positive Login Test successful.");
	}

	@Parameters({"usernameNeg", "passwordNeg", "expectedErrorMsg"})
	@Test(priority = 2)
	public void negativeLoginTest(String usernameNeg, String passwordNeg, String expectedErrorMsg) {
		// Display on Console
		log.info("Starting Negative Login Test.");

		// Load Page
		String url = "https://the-internet.herokuapp.com/login";
		driver.get(url);
		log.info("Login Page is opened.");
		
		// Sleep for 1.5secs - to slow down display
		sleepDisplay(1500);

		// Enter Username
		WebElement usernameElement = driver.findElement(By.id("username"));
		usernameElement.sendKeys(usernameNeg);

		// Enter Password
		WebElement passwordElement = driver.findElement(By.id("password"));
		passwordElement.sendKeys(passwordNeg);

		// Sleep for 1.5secs - to slow down display
		sleepDisplay(1500);

		// Click Login Button
		WebElement loginButton = driver.findElement(By.tagName("button"));
		loginButton.click();

		// Sleep for 2secs - to slow down display
		sleepDisplay(2000);

		// Page Verification - Checking Error Message Displayed
		WebElement IncorrectUserNameMsg = driver.findElement(By.cssSelector("#flash"));
		String actualErrorMsg = IncorrectUserNameMsg.getText();
		Assert.assertTrue(actualErrorMsg.contains(expectedErrorMsg),
		"Actual Error Message is not the same as Expected.\nActual Error Message: " + actualErrorMsg
				+ "\nExpected Error Message: " + expectedErrorMsg);
			
		log.info("Negative Login Test successful.");
	}

}