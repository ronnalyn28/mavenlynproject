package com.herokuapp.theinternet.pages;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckboxesPage extends BasePageObject {
		//BEHAVIOR SHOULD WORK THE SAME WAY FOR RADIO BUTTON
		//SAMPLE XPATH FOR SPECIFIC RADIOBUTTON IS $x("//input[@type='radio'][2]")
	private By checkbox = By.xpath("//input[@type='checkbox']");
	private By checkbox2 = By.xpath("//input[@type='checkbox'][2]");
			//By.cssSelector("#checkboxes(2)");

	public CheckboxesPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	// Get list of all checkboxes and select if unchecked
	public void selectAllCheckboxes() {
		log.info("Checking all unchecked checkboxes");
		List<WebElement> checkboxes = findAll(checkbox); // find all element checkbox
		for (WebElement checkbox : checkboxes) { // means for each checkbox in checkboxes
			if (!checkbox.isSelected()) { // means if checkbox is not selected
				checkbox.click(); // then click, if already selected, ignore
			}
		}
	}

	// Verify that all available checkboxes are checked. If at least one unchecked, return false
	public boolean areAllCheckboxesChecked() {
		log.info("Verifying that all checkboxes are checked");
		List<WebElement> checkboxes = findAll(checkbox); // findAll is a method in BasePageObject
		for (WebElement checkbox : checkboxes) {
			if (!checkbox.isSelected()) { // if at least 1 checkbox is not selected, return false
				return false;
			}
		}
		return true;
	}

	// Get list of all checkboxes and unselect 2nd checkbox
	public void selectOneCheckbox() {
		log.info("Unselecting 2nd checkbox");
		click(checkbox2);
	}

	// Verify that 2nd checkbox is selected
	public boolean isThe2ndCheckboxChecked() {
			log.info("Verifying that the 2nd Checkbox is unchecked");
			return find(checkbox2).isSelected();
	}

}
