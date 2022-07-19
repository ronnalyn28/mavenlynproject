package com.herokuapp.theinternet;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.CheckboxesPage;
import com.herokuapp.theinternet.pages.WelcomePageObject;

public class CheckboxTests extends TestUtilities {

	@Test(priority=1)
	public void selectAllCheckboxesTest() {
		// Load Main Page
		WelcomePageObject welcomePage = new WelcomePageObject(driver, log);
		welcomePage.openPage();

		// Load Checkboxes Page
		CheckboxesPage checkboxesPage = welcomePage.clickCheckboxesLink();
		
		//Select All Checkboxes
		checkboxesPage.selectAllCheckboxes();
		
		// Sleep for 1.5secs - to slow down display
		sleepDisplay(1500);
		
		//Verify that all checkboxes are selected
		Assert.assertTrue(checkboxesPage.areAllCheckboxesChecked(), "Not all checkboxes are checked.");
	}

	@Test(priority=2)
	public void selectOneCheckboxTest() {

		// Load Main Page
		WelcomePageObject welcomePage = new WelcomePageObject(driver, log);
		// WelcomePageObject is the class created in other package
		welcomePage.openPage();

		// Load Checkboxes Page
		CheckboxesPage checkboxesPage = welcomePage.clickCheckboxesLink();
		
		//Selecting 2nd Checkbox
		checkboxesPage.selectOneCheckbox();
		
		// Sleep for 1.5secs - to slow down display
		sleepDisplay(1500);
		
		//Verify that 2nd checkbox is no longer selected
		Assert.assertFalse(checkboxesPage.isThe2ndCheckboxChecked(), "The 2nd checkbox is no longer checked");
	}
}
