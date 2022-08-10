package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HorizontalSliderPage extends BasePageObject {

	private String sliderPageUrl = "http://the-internet.herokuapp.com/horizontal_slider";

	private By rangeLocator = By.id("range"); //this is the value displayed beside the slider
	private By sliderLocator = By.tagName("input");

	public HorizontalSliderPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	// Load Horizontal Slider Page with it's URL
	public void openPage() {
		log.info("Opening page: " + sliderPageUrl);
		openURL(sliderPageUrl);
		log.info("Horizontal Slider Page opened!");
	}

	// Move slider to specified value
	public void setSliderTo(String sliderValue) {
		log.info("Moving slider to " + sliderValue);

		// Method 1: Move slider normal method - this does not work, slider moves til end even tho you set the slider value to 3.5
			// Find the xOffset using given value
			// int width = find(sliderLocator).getSize().getWidth();
					//you can see this when you inspect the slider (sample value is 129x16)
			// double percent = Double.parseDouble(sliderValue); // 5; --5 is the max step
					//double percent will make the %value to to decimal value 
			// int xOffset = (int) (width * percent);

			// Actions action = new Actions(driver);
			// action.dragAndDropBy(find(sliderLocator), xOffset, 0).build().perform();
				//xOffset, 0 - x and y coordinates

		// Method 2: Calculate number of steps
		int totalSteps = (int) (Double.parseDouble(sliderValue) / 0.5);
			//sliderValue is the value returned in HorizontalSliderTests method
			//0.5 is the step value (increment)- you can see this when you inspect element
			//ex. sliderValue is 3.5 / 0.5, then totalSteps is 7

		// Move slider up to specified sliverValue
		pressKey(sliderLocator, Keys.ENTER); //pressing ENTER to activate slider
			// pressKey is method in BasePageObject method
		for (int i = 0; i < totalSteps; i++) { //starting with 0, will continue clicking arrow right until totalSteps value are reached
			pressKey(sliderLocator, Keys.ARROW_RIGHT);
		}
	}

	// Getting slider value
	public String getSliderValue() {
		String sliderValue = find(rangeLocator).getText();
		log.info("Slider value is " + sliderValue);
		return sliderValue;
	}

}
