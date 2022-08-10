package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class KeyPressesPage extends BasePageObject {
	
	private By body = By.xpath("//body"); //body is the frame of the site where you can press key
	//private By targetTextLocator = By.id("target");
	private By resultTextLocator = By.id("result");

	public KeyPressesPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	// Press given key (on keyboard) while on this page
	public void pressKey(Keys key) {
		log.info("Pressing " + key.name());
		pressKey(body, key);
	}

	// Get result text after pressing on a key
	public String getResultText() {
		String result = find(resultTextLocator).getText();
		log.info("Result text: " + result);
		return result;
	}

}
