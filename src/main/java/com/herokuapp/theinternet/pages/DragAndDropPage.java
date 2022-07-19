package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DragAndDropPage extends BasePageObject {

	public DragAndDropPage (WebDriver driver, Logger log) {
		super(driver, log);
	}
	
	private By columnA = By.id("column-a");
	private By columnB = By.id("column-b");
	
	// Drag A box and drop it on B box
	public void dragAtoB() {
		log.info("Drag and Drop A box on B box");
		performDragAndDrop(columnA, columnB); //drag from-to; 1st element=From, 2nd element=To
	}

	// Getting Column A Text
	public String getColumnAText() {
		String columnAtext = find(columnA).getText();
		log.info("Column A Text: " + columnAtext);
		return columnAtext;
	}

	// Getting Column B Text
	public String getColumnBText() {
		String columnBtext = find(columnB).getText();
		log.info("Column B Text: " + columnBtext);
		return columnBtext;
	}

}
