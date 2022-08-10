package com.herokuapp.theinternet;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.DropdownPage;
import com.herokuapp.theinternet.pages.WelcomePageObject;

public class DropdownTests extends TestUtilities {

	@Test
	public void selectOptionTwoTest() {
		log.info("Starting selectOptionTwoTest of Dropdown");

		// Load Main Page
		WelcomePageObject welcomePage = new WelcomePageObject(driver, log);
		welcomePage.openPage();

		// Load Dropdown Page
		DropdownPage dropdownPage = welcomePage.clickDropdownLink();

		// Select Option2 from the dropdown
		dropdownPage.selectOption(2); // 2 means value is 2 of dropdown option

		// Sleep for 1.5secs - to slow down display
		sleepDisplay(1500);
	
		// Verify that Option2 is selected
		String selectedOption = dropdownPage.getSelectedOption();
		Assert.assertTrue(selectedOption.equals("Option 2"), 
				"Option 2 is not selected. The selected one is " + selectedOption);
	}

}
