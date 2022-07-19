package com.herokuapp.theinternet;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ExceptionTests {
	
	@Test(priority = 1, enabled=false)
	public void timeoutTest() { //For testing of catching Exceptions
		// Create Driver for Chrome
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
			
		// Maximize Browser Window
		driver.manage().window().maximize();		
		System.out.println("Starting Test of Timeout.");
		
		// Load Page
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");

		// Sleep for 2secs - to slow down display
		sleepDisplay(2000);
		
		// Click Start Button
		WebElement startButton = driver.findElement(By.xpath("//div[@id='start']/button"));
		startButton.click();

		// Find Finish element - has id on f12 but no visible on page
		WebElement finishElement = driver.findElement(By.id("finish"));
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2)); //this is the better explicit wait
			//2 is the timeout/delay/wait in seconds
		try {
			wait.until(ExpectedConditions.visibilityOf(finishElement));
				//Waiting until element is visible before getting the value below
		} catch (TimeoutException exception) {
			System.out.println("Exception catched:" + exception.getMessage());
				//Displaying exception
			sleepDisplay(2000);
		}
		
		// Close Browser 
		driver.quit();
		
	}
	
	@Test(priority = 2, enabled=false)
	public void elementNotVisTest() { //Web Element is available but not displayed
		// Create Driver for Chrome
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
			
		// Maximize Browser Window
		driver.manage().window().maximize();		
		System.out.println("Starting Test of ElemtentNotVisibleException.");

		// Load Page
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");

		// Sleep for 2secs - to slow down display
		sleepDisplay(2000);
		
		// Click Start Button
		WebElement startButton = driver.findElement(By.xpath("//div[@id='start']/button"));
		startButton.click();

		// Find Finish element - has id on f12 but no visible on page
		WebElement finishElement = driver.findElement(By.id("finish"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			//10 is the timeout/delay/wait in seconds
		wait.until(ExpectedConditions.visibilityOf(finishElement));
			//Waiting until element is visible before getting the value below
				//But element is already present in the beginning, just not visible
		
		String finishText = finishElement.getText();
		Assert.assertTrue(finishText.contains("Hello World!"), 
				"Finish Text: " + finishText);
		
		// Sleep for 2secs - to slow down display
		sleepDisplay(2000);
		
		// Close Browser 
		driver.quit();
		
	}

	@Test(priority = 3, enabled=false)
	public void noSuchElementTest() { //Web Element is not available yet
		// Create Driver for Chrome
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
			
		// Maximize Browser Window
		driver.manage().window().maximize();		
		System.out.println("Starting Test of NoSuchElementException.");

		// Load Page
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");

		// Sleep for 2secs - to slow down display
		sleepDisplay(2000);
		
		// Click Start Button
		WebElement startButton = driver.findElement(By.xpath("//div[@id='start']/button"));
		startButton.click();

		// Find Finish element - ID is not yet available in f12
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			//10 is the timeout in seconds
		WebElement finishElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish")));
		
		String finishText = finishElement.getText();
		Assert.assertTrue(finishText.contains("Hello World!"), 
				"Finish Text: " + finishText);
		
		// Sleep for 2secs - to slow down display
		sleepDisplay(2000);
		
		// Close Browser 
		driver.quit();
		
	}
	
	@Test(priority=4, enabled=false)
	public void staleElementRefTest() { //For Checking when Web Element is deleted
		// Create Driver for Chrome
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
					
		// Maximize Browser Window
		driver.manage().window().maximize();		
		System.out.println("Starting Test of StateElementReferenceException.");

		// Load Page
		driver.get("https://the-internet.herokuapp.com/dynamic_controls");

		// Sleep for 2secs - to slow down display
		sleepDisplay(2000);
				
		// View checkBox and Click Remove Button - checkBox Element will then be deleted
		WebElement checkBox = driver.findElement(By.id("checkbox"));
		WebElement removeButton = driver.findElement(By.xpath("//button[contains(text(),'Remove')]"));
		removeButton.click();
		
		//Verification - checkBox is removed after clicking Remove
		//Assert.assertFalse(checkBox.isDisplayed()); this will show the StaleElementReferenceException
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOf(checkBox)), "Checkbox is still visible.");
			//wait until checkBox is no longer visible
		
		Assert.assertTrue(wait.until(ExpectedConditions.stalenessOf(checkBox)), "Checkbox is still visible.");
			//wait until checkBox element is deleted, this is just another way
		
		//Click Add button once displayed
		WebElement addButton = driver.findElement(By.xpath("//button[contains(text(),'Add')]"));
		addButton.click();
		
		//Verification - checkBox is displayed again after clicking Add
		checkBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkbox")));
			//We need to assign new reference to same element coz this checkBox element was prev deleted fr the page
			//This is needed to refresh checkBox Element's value
		Assert.assertTrue(checkBox.isDisplayed(), "Checkbox is not visible.");
		
		
		// Close Browser 
		driver.quit();
		
	}
	
	@Test(priority=5, enabled=true)
	public void disabledElementTest() { //For Checking when Element is disabled then enabled
		// Create Driver for Chrome
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
					
		// Maximize Browser Window
		driver.manage().window().maximize();		
		System.out.println("Starting Test of DisabledElementException.");

		// Load Page
		driver.get("https://the-internet.herokuapp.com/dynamic_controls");

		// Sleep for 2secs - to slow down display
		sleepDisplay(2000);
				
		// Click Enable button to Enable the field available
		WebElement inputField = driver.findElement(By.xpath("//input[@type='text']"));
		WebElement enableButton = driver.findElement(By.xpath("//button[contains(text(),'Enable')]"));
		enableButton.click();
		
		//Verification - Wait for input field to be enabled then provide value
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(inputField));
		Assert.assertTrue(inputField.isEnabled(), "Input field is still disabled.");
		
		//Type into the Input field
		inputField.sendKeys("This is a Ronna value");
		Assert.assertEquals(inputField.getAttribute("value"), "This is a Ronna value");
			//getAttribute because where getting the value of Input field
		
		// Close Browser 
		driver.quit();
		
	}
	
	private void sleepDisplay(long m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}