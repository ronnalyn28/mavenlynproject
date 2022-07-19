package com.herokuapp.theinternet;

import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.LoginPage;
import com.herokuapp.theinternet.pages.SecureAreaPage;
import com.herokuapp.theinternet.pages.WelcomePageObject;

public class LoginTests extends TestUtilities {
	@Parameters({ "usernamePos", "passwordPos", "expectedUrl", "expectedSuccessMsg" })
	@Test(priority = 1, enabled = true)
	public void positiveLoginTest(String usernamePos, String passwordPos, String expectedUrl,
			String expectedSuccessMsg) {
		// Load Main Page
		WelcomePageObject welcomePage = new WelcomePageObject(driver, log);
		// WelcomePageObject is the class created in other package
		welcomePage.openPage(); // .openPage() is the method created in WelcomePageObject class
		takeScreenshot("WelcomePage opened"); // this filename is needed for takeScreenshot method in TestUtilities

		// Load Login Page
		LoginPage loginPage = welcomePage.clickFormAuthenticationLink();
		takeScreenshot("LoginPage opened");

		// Add new cookie
		Cookie ck = new Cookie("username", usernamePos, "the-internet.herokuapp.com", "/", null);
			//new Cookie (name, value, domain, path, expiry)
			//"the-internet.herokuapp.com", "/" - means will add cookie after the-internet.herokuapp.com
		loginPage.setCookie(ck);

		// Execute Positive Login
		SecureAreaPage secureAreaPage = loginPage.positiveLogIn(usernamePos, passwordPos);
		takeScreenshot("Correct crendentials provided");

		// Sleep for 1.5secs - to slow down display
		sleepDisplay(1500);

		// Get cookies
		String username = secureAreaPage.getCookie("username");
		log.info("Username cookie: " + username);
		String session = secureAreaPage.getCookie("rack.session");
			//cookies can be found when you're on a site, click f12 > Application > Cookies
		log.info("Session cookie: " + session);

		// Page Verification - Checking URL after login is correct
		Assert.assertEquals(secureAreaPage.getCurrentUrl(), expectedUrl, "Actual Page URL not the same as Expected.");

		// Page Verification - Checking if Logout button is available
		Assert.assertTrue(secureAreaPage.isLogOutButtonVisible(), "Logout Button is not visible.");

		// Page Verification - Checking Successful Login Message
		String actualSuccessMsg = secureAreaPage.getSuccessMessageText();
		Assert.assertTrue(actualSuccessMsg.contains(expectedSuccessMsg),
				"Actual Login Success Message is not the same as Expected.\nActual Success Message: " + actualSuccessMsg
						+ "\nExpected Success Message: " + expectedSuccessMsg);

		takeScreenshot("User logged in successfully");
	}

	@Parameters({ "usernameNeg", "passwordNeg", "expectedErrorMsg" })
	@Test(priority = 2, enabled = false)
	public void negativeLoginTest(String usernameNeg, String passwordNeg, String expectedErrorMsg) {
		// Display on Console
		log.info("Starting Negative Login Test.");

		// Load Main Page
		WelcomePageObject welcomePage = new WelcomePageObject(driver, log);
		// WelcomePageObject is the class created in other package
		welcomePage.openPage(); // .openPage() is the method created in WelcomePageObject class

		// Load Login Page
		LoginPage loginPage = welcomePage.clickFormAuthenticationLink();

		// Execute Negative Login
		loginPage.negativeLogIn(usernameNeg, passwordNeg);

		// Sleep for 1.5secs - to slow down display
		sleepDisplay(1500);

		// Page Verification - Wait for Error Message
		loginPage.waitForErrorMessage();
		String actualErrorMsg = loginPage.getErrorMessageText();
		Assert.assertTrue(actualErrorMsg.contains(expectedErrorMsg),
				"Actual Error Message is not the same as Expected.\nActual Error Message: " + actualErrorMsg
						+ "\nExpected Error Message: " + expectedErrorMsg);

		log.info("Negative Login Test successful.");
	}

}