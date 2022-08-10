package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditorPage extends BasePageObject {

	private By editorLocator = By.id("tinymce");
	private By frame = By.tagName("iframe");

	public EditorPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	//Get default text from TinyMCE WYSIWYG Editor
	public String getEditorText() {
		switchToFrame(frame);
			//Switch to iframe first before finding locator of editor
		String EditorText = find(editorLocator).getText();
		log.info("Text from TinyMCE WYSIWYG Editor: " + EditorText);
		return EditorText;
	}
	
	//Change value of TinyMCE WYSIWYG Editor
	public String updateEditorText(String EditorUpdatedText) {
		find(editorLocator).clear();
		enterValue(EditorUpdatedText, editorLocator);
		log.info("Updated value of TinyMCE WYSIWYG Editor: " + EditorUpdatedText);
		return EditorUpdatedText;
	}

}
