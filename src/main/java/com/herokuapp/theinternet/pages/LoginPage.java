package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePageObject {	
	private By usernameLocator = By.id("username");
	private By passwordLocator = By.id("password");
	private By loginButtonLocator = By.tagName("button");
	private By errorMessageLocator = By.cssSelector("#flash");
	
	public LoginPage(WebDriver driver, Logger log) {
		super(driver, log);
	}
	
	//Execute Login
	public SecureAreaPage positiveLogIn (String username, String password) {
		log.info("Executing Positive Login with username [" + username + "] and password [" + password + "]");
		enterValue(username, usernameLocator); //usernamePos is the parameter, usernameLocator is the locator
		enterValue(password, passwordLocator);
		click(loginButtonLocator);
		return new SecureAreaPage (driver, log);
	}
	

	public void negativeLogIn(String username, String password) {
		log.info("Executing Negative Login with username [" + username + "] and password [" + password + "]");
		enterValue(username, usernameLocator);
		enterValue(password, passwordLocator);
		click(loginButtonLocator);
	}

	/** Wait for error message to be visible on the page */
	public void waitForErrorMessage() {
		waitForVisibilityOf(errorMessageLocator, 5);
	}

	public String getErrorMessageText() {
		return find(errorMessageLocator).getText();
	}

}