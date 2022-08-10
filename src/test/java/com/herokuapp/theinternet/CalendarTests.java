package com.herokuapp.theinternet;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.CalendarMain;

public class CalendarTests extends TestUtilities {
	
	@Test (priority = 1, enabled = true)
	public void selectDateFromDefault () {
		// Load Expedia Page
		CalendarMain calendarMain = new CalendarMain(driver, log);
		calendarMain.openExpediaPage();
		
		// Navigate to Flights Tab
		calendarMain.navigateToFlightsTab();
		
		// Click Departure Field
		calendarMain.clickDepartureField();
		
		// Select Departure and Arrival Dates
		calendarMain.selectDepartureDateonCurrentMonth();
		calendarMain.selectArrivalDateonCurrentMonth();
		sleepDisplay(1500);
		
		// Click Done Button
		calendarMain.clickDoneButton();
		
		// Get Departure and Arrival Display
		String actualDeparture = calendarMain.getDepartureDate();
		String actualArrival = calendarMain.getArrivalDate();
				
		// Verify that Selected Dates are correct
		Assert.assertTrue(actualDeparture.equals("17 Aug"),
				"Departure Label is not the expected. \nShould be '17 Aug', but it is '" + actualDeparture + "'");
		Assert.assertTrue(actualArrival.equals("22 Aug"),
				"Arrival Label is not the expected. \nShould be '22 Aug', but it is '" + actualArrival + "'");
	}
	
	@Test (priority = 2, enabled = true)
	public void selectDateFromDecember () {
		// Load Expedia Page
		CalendarMain calendarMain = new CalendarMain(driver, log);
		calendarMain.openExpediaPage();
		
		// Navigate to Flights Tab
		calendarMain.navigateToFlightsTab();
		
		// Click Departure Field and Select Month
		calendarMain.clickDepartureField();
		sleepDisplay(1500);
		calendarMain.selectMonth();
				
		// Select Departure and Arrival Dates
		calendarMain.selectDepartureDate();
		calendarMain.selectArrivalDate();
		sleepDisplay(1500);
		
		// Click Done Button
		calendarMain.clickDoneButton();
		
		// Get Departure and Arrival Display
		String actualDeparture = calendarMain.getDepartureDate();
		String actualArrival = calendarMain.getArrivalDate();
				
		// Verify that Selected Dates are correct
		Assert.assertTrue(actualDeparture.equals("17 Dec"),
				"Departure Label is not the expected. \nShould be '17 Dec', but it is '" + actualDeparture + "'");
		Assert.assertTrue(actualArrival.equals("22 Dec"),
				"Arrival Label is not the expected. \nShould be '22 Dec', but it is '" + actualArrival + "'");
				
	}

}
