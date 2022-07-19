package com.herokuapp.theinternet;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.EditorPage;
import com.herokuapp.theinternet.pages.WelcomePageObject;

public class EditorTests extends TestUtilities {

	@Test
	public void defaultEditorValueTest() {
		log.info("Starting defaultEditorValueTest");

		// open main page
		WelcomePageObject welcomePage = new WelcomePageObject(driver, log);
		welcomePage.openPage();

		// Scroll to the bottom of the page
		sleepDisplay(5000);
		welcomePage.scrollToBottom();
		sleepDisplay(5000);

		// Click on WYSIWYG Editor link
		EditorPage editorPage = welcomePage.clickWYSIWYGEditorLink();
		sleepDisplay(1500);

		// Get default editor text
		String editorDefaultText = editorPage.getEditorText();
		sleepDisplay(2500);

		// Verification that iFrame contains expected default text
		Assert.assertTrue(editorDefaultText.equals("Your content goes here."),
				"Editor default text is not the expected. It is: " + editorDefaultText);

		// Update value of Editor field value
		String editorUpdatedText = editorPage.updateEditorText("Ronna Lyn");
		sleepDisplay(2500);

		// Verification that iFrame contains expected updated text
		Assert.assertTrue(editorUpdatedText.equals("Ronna Lyn"),
				"Editor updated text is not the expected. It is: " + editorUpdatedText);
	}

}