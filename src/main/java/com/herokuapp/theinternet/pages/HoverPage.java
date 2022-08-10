package com.herokuapp.theinternet.pages;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HoverPage extends BasePageObject {

	private String hoverPageUrl = "http://the-internet.herokuapp.com/hovers";

	private By avatarLocator = By.xpath("//div[@class='figure']");
		//using @class xpath to find where avatar is located
	private By viewProfileLinkLocator = By.xpath(".//a[contains(text(),'View profile')]");
		//there are 3 View Profile links in the page, using a dot in ".//a" means in the locator,
		//we didn't get the specific View Profile link
		//we'll only get the specific View Profile link in openUserProfile method below
		//without the dot in ".//a" the locator will be for the View Profile link in first avatar

	public HoverPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	// Load Hovers Page with it's URL
	public void openPage() {
		log.info("Opening page: " + hoverPageUrl);
		openURL(hoverPageUrl);
		log.info("Hovers Page opened!");
	}

	// Open specified user profile
	public void openUserProfile(int i) {
		List<WebElement> avatars = findAll(avatarLocator);
		log.info(avatars);
			//get all avatars , if there are 3 avatars, id would be 0,1,2
		WebElement specifiedUserAvatar = avatars.get(i - 1);
			//i in 'i-1' is the value provided in HoversTests method, sample i=2, then i-1 = 1 (which is the 2nd avatar)
		hoverOverElement(specifiedUserAvatar);
		specifiedUserAvatar.findElement(viewProfileLinkLocator).click();
			//this will click on the View Profile link displayed on the avatar that you hover on
	}

}
