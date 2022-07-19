package com.herokuapp.theinternet;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.HorizontalSliderPage;

public class HorizontalSliderTests extends TestUtilities {

	@Test
	public void sliderTest() {
		log.info("Starting sliderTest");

		// Open HorizontalSliderPage
		HorizontalSliderPage horizontalSliderPage = new HorizontalSliderPage(driver, log);
		horizontalSliderPage.openPage();
		takeScreenshot("horizontalSliderPage opened");

		String sliderValue = "3.5";
			// min/max value that can be set can be seen when you inspect element

		// Set slider value
		horizontalSliderPage.setSliderTo(sliderValue);
		sleepDisplay(2000);
		takeScreenshot("Setting Slider Value");

		// Verify slider value
		String actualSliderValue = horizontalSliderPage.getSliderValue();
		Assert.assertTrue(actualSliderValue.equals(sliderValue), "Range is not correct. It is: " + actualSliderValue);
	}

}
