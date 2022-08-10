package com.herokuapp.theinternet;

import java.util.List;

import org.openqa.selenium.logging.LogEntry;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.JSErrorPage;

public class JSErrorTests extends TestUtilities {

	@Test
	public void jsErrorTest() { //Checking for Javascript errors on console (f12)
		log.info("Starting jsErrorTest");
		SoftAssert softAssert = new SoftAssert();

		// Open JSErrorPage
		JSErrorPage jSErrorPage = new JSErrorPage(driver, log);
		jSErrorPage.openPage();
		takeScreenshot("jSErrorPage opened"); 

		// Get logs
		List<LogEntry> logs = getBrowserLogs();
		takeScreenshot("Browser logs"); 

		// Verifying there are no JavaScript errors in console
		for (LogEntry logEntry : logs) { //for every log execute below
			if (logEntry.getLevel().toString().equals("SEVERE")) { //means - if severe log, fail test
				softAssert.fail("Severe error: " + logEntry.getMessage());
			} //this test will fail because error is severe
		}
		softAssert.assertAll();
	}

}
