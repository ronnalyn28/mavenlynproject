package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginYCLTest {

	@Test // import org.testng.annotations.Test
	public void loginYCLTest() {
		// Display on Console
		System.out.println("Starting Login Test of YCL.");

		// Create Driver for Chrome
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
			
		// Maximize Browser Window
		driver.manage().window().maximize();

		// Load Page
		String url = "https://ycl2.plato.dev.ibc.com.au/login.php";
		driver.get(url);
		System.out.println("YCL Login Page is opened.");

		// Sleep for 1.5secs - to slow down display
		sleepDisplay(1500);

		// Enter Username
		WebElement username = driver.findElement(By.id("loginUsername"));
		username.sendKeys("ronna7@getnada.com");

		// Enter Password
		WebElement password = driver.findElement(By.id("loginPassword"));
		password.sendKeys("Temp_Cultural7");

		// Sleep for 1.5secs - to slow down display
		sleepDisplay(1500);

		// Click Login Button
		WebElement loginButton = driver.findElement(By.cssSelector(".btn-primary"));
		loginButton.click();

		// Sleep for 2secs - to slow down display
		sleepDisplay(2000);

		// Page Verification - Checking URL after login is correct
		String expectedUrl = "https://ycl2.plato.dev.ibc.com.au/dashboard.php";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual Page URL not the same as Expected.");
			
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
