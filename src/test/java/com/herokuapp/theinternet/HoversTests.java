package com.herokuapp.theinternet;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.HoverPage;

public class HoversTests extends TestUtilities {

	@Test
	public void user2ProfileTest() {
		log.info("Starting user2ProfileTest");

		// Load Hovers Page
		HoverPage hoverPage = new HoverPage(driver, log);
		hoverPage.openPage();
		takeScreenshot("hoverPage opened");
		sleepDisplay(1500);

		// Hover on User 2 Profile and click View Profile
		hoverPage.openUserProfile(2);
		takeScreenshot("View Profile link is clicked on User 2");
		sleepDisplay(1500);

		// Verify correct user profile opened
		Assert.assertTrue(hoverPage.getCurrentUrl().contains("/users/2"),
				"Url of opened page is not the expected User 2 page url");
	}

}
