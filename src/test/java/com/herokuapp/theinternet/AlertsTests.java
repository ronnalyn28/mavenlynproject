package com.herokuapp.theinternet;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.JSAlertsPage;
import com.herokuapp.theinternet.pages.WelcomePageObject;

public class AlertsTests extends TestUtilities {

	@Test(priority=1)
	public void jsAlertTests() {
		log.info("Starting jsAlertTests");
		SoftAssert softAssert = new SoftAssert();
			//Soft Assert = executes all asserts before failing test, ex. 'softAssert.assertTrue'
			//Hard Assert = fails test as soon as 1st assert fails, ex. 'Assert.assertTrue'

		// Load Main Page
		WelcomePageObject welcomePage = new WelcomePageObject(driver, log);
		welcomePage.openPage();

		// Load JavaScript Alerts Page
		JSAlertsPage jsAlertsPage = welcomePage.clickJSAlertsLink();

		// Click JS Alert button
		jsAlertsPage.openJSAlert();
		sleepDisplay(1500);

		// Get alert text
		String actualAlertMessage = jsAlertsPage.getAlertText();

		// Click OK button
		jsAlertsPage.acceptAlert();

		// Get Results text
		String JSAlertResult = jsAlertsPage.getResultText();
		sleepDisplay(1500);
		
		// Verifications
		// 1 - Alert text is expected
		softAssert.assertTrue(actualAlertMessage.equals("I am a JS Alert"),
				"Alert message is not the expected. \nShould be 'I am a JS Alert', but it is '" + actualAlertMessage
						+ "'");

		// 2 - Result text is expected
		softAssert.assertTrue(JSAlertResult.equals("You successfully clicked an alert"), "The result is not as expected. "
				+ "\nIt should be 'You successfuly clicked an alert', but it is '" + JSAlertResult + "'");
		softAssert.assertAll(); //will run all asserts. w/o this assertAll, test will not fail even tho asserts fail
	}

	@Test(priority=2)
	public void jsDismissTest() {
		log.info("Starting jsDismissTest");

		// Load Main Page
		WelcomePageObject welcomePage = new WelcomePageObject(driver, log);
		welcomePage.openPage();

		// Load JavaScript Alerts Page
		JSAlertsPage jsAlertsPage = welcomePage.clickJSAlertsLink();

		// Click JS Confirm button
		jsAlertsPage.openJSConfirm();
		sleepDisplay(1500);

		// Get alert text
		String actualAlertMessage = jsAlertsPage.getAlertText();

		// Click Cancel button
		jsAlertsPage.dismissAlert();

		// Get Results text
		String JSConfirmResult = jsAlertsPage.getResultText();
		sleepDisplay(1500);

		// Verifications
		// 1 - Alert text is expected
		Assert.assertTrue(actualAlertMessage.equals("I am a JS Confirm"),
				"Alert message is not the expected. \nShould be 'I am a JS Confirm', but it is '" + actualAlertMessage
						+ "'");

		// 2 - Result text is expected
		Assert.assertTrue(JSConfirmResult.equals("You clicked: Cancel"),
				"result is not expected. \nShould be 'You clicked: Cancel', but it is '" + JSConfirmResult + "'");

		// 2 - Result text is expected
		Assert.assertTrue(JSConfirmResult.equals("You clicked: Cancel"), "The result is not as expected. "
				+ "\nIt should be 'You clicked: Cancel', but it is '" + JSConfirmResult + "'");
	}

	@Test(priority=3)
	public void jsPromptTest() {
		log.info("Starting jsPromptTest");

		// Load Main Page
		WelcomePageObject welcomePage = new WelcomePageObject(driver, log);
		welcomePage.openPage();

		// Load JavaScript Alerts Page
		JSAlertsPage jsAlertsPage = welcomePage.clickJSAlertsLink();

		// Click JS Prompt button
		jsAlertsPage.openJSPrompt();
		sleepDisplay(1500);
		
		// Get alert text
		String actualAlertMessage = jsAlertsPage.getAlertText();

		// Type text into alert
		jsAlertsPage.typeTextIntoAlert("Hello Alert, it's Lyn here@");
		sleepDisplay(2000);
		
		// Get Results text
		String JSPromptResult = jsAlertsPage.getResultText();
		sleepDisplay(1500);
		
		// Verifications
		// 1 - Alert text is expected
		Assert.assertTrue(actualAlertMessage.equals("I am a JS prompt"),
				"Alert message is not the expected. \nShould be 'I am a JS prompt', but it is '" + actualAlertMessage + "'");

		// 2 - Result text is expected
		Assert.assertTrue(JSPromptResult.equals("You entered: Hello Alert, it's Lyn here"), "The result is not as expected. "
				+ "\nIt should be 'You entered: Hello Alert, it's Lyn here', but it is '" + JSPromptResult + "'");
	}
}
