package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class JSErrorPage extends BasePageObject {

	private String pageUrl = "https://the-internet.herokuapp.com/javascript_error";

	public JSErrorPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	// Load JSErrorPage with it's URL
	public void openPage() {
		log.info("Opening page: " + pageUrl);
		openURL(pageUrl);
		log.info("Page opened!");
	}

}