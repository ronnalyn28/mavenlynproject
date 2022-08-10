package com.herokuapp.theinternet;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.NewWindowPage;
import com.herokuapp.theinternet.pages.WelcomePageObject;
import com.herokuapp.theinternet.pages.WindowsPage;

public class WindowsTests extends TestUtilities {

	@Test
	public void newWindowTest() {
		log.info("Starting newWindowTest");

		// open main page
		WelcomePageObject welcomePage = new WelcomePageObject(driver, log);
		welcomePage.openPage();

		// Load Multiple Windows page
		WindowsPage windowsPage = welcomePage.clickMultipleWindowsLink();
		sleepDisplay(2000);

		// Click 'Click Here' link to open new window
		windowsPage.openNewWindow();
		sleepDisplay(3000);
		
		// Find the new window page and switch to it
		NewWindowPage newWindowPage = windowsPage.switchToNewWindowPage();
		String pageSource = newWindowPage.getCurrentPageSource();

		// Verification that new page contains expected text in source
		Assert.assertTrue(pageSource.contains("New Window"), "New page source doesn't contain expected text");
	}
}
