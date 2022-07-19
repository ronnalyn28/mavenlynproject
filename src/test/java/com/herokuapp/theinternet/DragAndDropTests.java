package com.herokuapp.theinternet;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.DragAndDropPage;
import com.herokuapp.theinternet.pages.WelcomePageObject;

public class DragAndDropTests extends TestUtilities {

	@Test
	public void dragAToBTest() {
		log.info("Starting dragAToBTest");
		// Load Main Page
		WelcomePageObject welcomePage = new WelcomePageObject(driver, log);
		welcomePage.openPage();

		// Load Drag And Drop Page
		DragAndDropPage dragAndDropPage = welcomePage.clickDragAndDropLink();
		takeScreenshot("dragAndDropPage opened");
		sleepDisplay(1500);

		// Drag box A and drop it on box B
		dragAndDropPage.dragAtoB();
		takeScreenshot("Box A is dragged to Box B");
		sleepDisplay(1500);

		// Verify correct headers in correct boxes
		String actualColumnAText = dragAndDropPage.getColumnAText();
		Assert.assertTrue(actualColumnAText.equals("B"), "Column A header should be B, but it is: " + actualColumnAText);

		String actualColumnBText = dragAndDropPage.getColumnBText();
		Assert.assertTrue(actualColumnBText.equals("A"), "Column A header should be B, but it is: " + actualColumnBText);
	}

}
