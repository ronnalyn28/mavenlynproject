package com.herokuapp.theinternet;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.herokuapp.theinternet.base.CsvDataProviders;
import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.LoginPage;
import com.herokuapp.theinternet.pages.WelcomePageObject;

public class NegativeLoginUsingCSVTests extends TestUtilities {

	@Test(dataProvider="csvReader", dataProviderClass = CsvDataProviders.class)
	public void negativeLoginTest(Map<String, String> testData) {
		// Getting Data from CSV File - format is String <user-defined name> = testData.get("<header in CSV>");
		String testNum = testData.get("testNum");
		String usernameNeg  = testData.get("usernameNeg");
		String passwordNeg = testData.get("passwordNeg");
		String expectedErrorMsg = testData.get("expectedErrorMessage");
		String testDescription = testData.get("testdescription");
		
		log.info("Starting negativeLoginTest #" + testNum + " for " + testDescription);
		
		// Load Main Page
		WelcomePageObject welcomePage = new WelcomePageObject(driver, log);
		welcomePage.openPage();
		
		// Load Login Page
		LoginPage loginPage = welcomePage.clickFormAuthenticationLink();
		takeScreenshot("loginPage opened");
		
		// Execute Negative Login
		loginPage.negativeLogIn(usernameNeg, passwordNeg);
		takeScreenshot(testNum + " for " + testDescription);
		sleepDisplay(1500);

		// Page Verification - Wait for Error Message
		loginPage.waitForErrorMessage();
		String actualErrorMsg = loginPage.getErrorMessageText();
		Assert.assertTrue(actualErrorMsg.contains(expectedErrorMsg),
		"Actual Error Message is not the same as Expected.\nActual Error Message: " + actualErrorMsg
				+ "\nExpected Error Message: " + expectedErrorMsg);
			
		log.info("Negative Login Test # " + testNum + " successful");
	}
}
