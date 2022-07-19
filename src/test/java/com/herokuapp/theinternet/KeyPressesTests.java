package com.herokuapp.theinternet;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.KeyPressesPage;
import com.herokuapp.theinternet.pages.WelcomePageObject;

public class KeyPressesTests extends TestUtilities {

	@Test(priority=1)
	public void pressKeyTest() {
		// Load Main Page
		WelcomePageObject welcomePage = new WelcomePageObject(driver, log);
		welcomePage.openPage();

		// Load Key Presses Page
		KeyPressesPage keyPressesPage = welcomePage.clickKeyPressesLink();
		takeScreenshot("keyPressesPage opened"); 

		// Push a keyboard key
		keyPressesPage.pressKey(Keys.ENTER);
		takeScreenshot("ENTER key is pressed"); 
		sleepDisplay(1500);

		// Get Results text
		String result = keyPressesPage.getResultText();

		// Verify Result text is expected
		Assert.assertTrue(result.equals("You entered: ENTER"),
				"result is not expected. \nShould be 'You entered: ENTER', but it is '" + result + "'");
	}

	@Test(priority=2)
	public void pressKeyWithActionsTest() {
		// Load Main Page
		WelcomePageObject welcomePage = new WelcomePageObject(driver, log);
		welcomePage.openPage();

		// Load Key Presses Page
		KeyPressesPage keyPressesPage = welcomePage.clickKeyPressesLink();

		// Push keyboard key
		keyPressesPage.pressKeyWithActions(Keys.F5);
		sleepDisplay(1500);

		// Get Results text
		String result = keyPressesPage.getResultText();

		// Verify Result text is expected
		Assert.assertTrue(result.equals("You entered: F5"),
				"result is not expected. \nShould be 'You entered: ENTER', but it is '" + result + "'");
	}

}