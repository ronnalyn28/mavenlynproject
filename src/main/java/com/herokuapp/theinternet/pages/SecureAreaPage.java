package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecureAreaPage extends BasePageObject {
	private By logOutButton = By.xpath("//a[@class='button secondary radius']");
	private By successMessage = By.cssSelector("#flash");

	public SecureAreaPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	//Page Verification - Checking if Logout button is available on the page
	public boolean isLogOutButtonVisible() {
		return find(logOutButton).isDisplayed();
	}

	//Page Verification - Checking Successful Login Message
	public String getSuccessMessageText() {
		return find(successMessage).getText();
	}
}
