package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WelcomePageObject extends BasePageObject {
	private String welcomeURL = "https://the-internet.herokuapp.com/";
	private By formAuthenticationLinkLocator = By.linkText("Form Authentication");
	private By checkboxesLinkLocator = By.linkText("Checkboxes");
	private By dropdownLinkLocator = By.linkText("Dropdown");
	private By jsAlertsLinkLocator = By.linkText("JavaScript Alerts");
	private By multipleWindowsLinkLocator = By.linkText("Multiple Windows");
	private By editorLinkLocator = By.linkText("WYSIWYG Editor");
	private By keyPressesLinkLocator = By.linkText("Key Presses");
	private By dragAndDropLinkLocator = By.linkText("Drag and Drop");

	public WelcomePageObject(WebDriver driver, Logger log) {
		super(driver, log); // super means will pass driver and log from the extended Class - BasePageObject
	}

	// Open Welcome Page
	public void openPage() {
		log.info("Opening Page: " + welcomeURL);
		openURL(welcomeURL); // openURL is the method in BasePageObject
	}

	// Open Login Page via Form Authentication Link on Welcome Page
	public LoginPage clickFormAuthenticationLink() {
		log.info("Clicking Form Authentication link on Welcome Page");
		click(formAuthenticationLinkLocator); // click is the method in BasePageObject
		return new LoginPage(driver, log);
	}

	// Open Checkboxes Page via Checkboxes Link on Welcome Page
	public CheckboxesPage clickCheckboxesLink() {
		log.info("Clicking Checkboxes link on Welcome Page");
		click(checkboxesLinkLocator);
		return new CheckboxesPage(driver, log);
	}

	// Open Dropdown Page via Dropdown Link on Welcome Page
	public DropdownPage clickDropdownLink() {
		log.info("Clicking Dropdown link on Welcome Page");
		click(dropdownLinkLocator);
		return new DropdownPage(driver, log);
	}

	// Open JavaScript Alerts Page via JavaScript Alerts Link on Welcome Page
	public JSAlertsPage clickJSAlertsLink() {
		log.info("Clicking JavaScript Alerts link on Welcome Page");
		click(jsAlertsLinkLocator);
		return new JSAlertsPage(driver, log);
	}

	// Open Multiple Windows Page via Multiple Windows Alerts Link on Welcome Page
	public WindowsPage clickMultipleWindowsLink() {
		log.info("Clicking Multiple Windows link on Welcome Page");
		click(multipleWindowsLinkLocator);
		return new WindowsPage(driver, log);
	}

	// Open WYSIWYG Editor Page via WYSIWYG Editor Link on Welcome Page
	public EditorPage clickWYSIWYGEditorLink() {
		log.info("Clicking WYSIWYG Editor link on Welcome Page");
		click(editorLinkLocator);
		return new EditorPage(driver, log);
	}

	// Open Key Presses Page via Key Presses Link on Welcome Page
	public KeyPressesPage clickKeyPressesLink() {
		log.info("Clicking Key Presses link on Welcome Page");
		click(keyPressesLinkLocator);
		return new KeyPressesPage(driver, log);
	}

	// Open Drag and Drop Page via Drag and Drop Link on Welcome Page
	public DragAndDropPage clickDragAndDropLink() {
		log.info("Clicking Drag and Drop on Welcome Page");
		click(dragAndDropLinkLocator);
		return new DragAndDropPage(driver, log);
	}
}
