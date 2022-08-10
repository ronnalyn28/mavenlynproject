package com.herokuapp.theinternet.pages;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CalendarMain extends BasePageObject {

	private String expediaPageUrl = "http://www.expedia.com.ph/";
	private By flightsTabLocator = By.xpath("//a[@aria-controls='wizard-flight-pwa']");
	private By DepartureDateLocator = By.id("d1-btn");
	private By ArrivalDateLocator = By.id("d2-btn");
	
	private By monthLocator = By.xpath(".//h2[@class='uitk-date-picker-month-name uitk-type-medium']");
	private int i;
	private By fwMonthNavigatorLocator = By.xpath("//button[@data-stid='date-picker-paging'][2]");
	
	private By CurrentMonthLocator = By.xpath(".//div[@data-stid='date-picker-month'][1]//button");
		//[1] because there are only 2 months displayed always in the date picker
		//Sample: December 2022 (left) and January 2023 (right)
		//[1] is the month on the left and [2] is the month on the right
	private By doneButtonLocator = By.xpath("//button[@data-stid='apply-date-picker']");
	//TRY SEND KEYS INSTEAD OF DATE PICKER
	public CalendarMain(WebDriver driver, Logger log) {
		super(driver, log);
	}

	// Load Expedia Page with it's URL
	public void openExpediaPage() {
		log.info("Opening page: " + expediaPageUrl);
		openURL(expediaPageUrl);
		log.info("Expedia Page opened!");
	}
	
	public void navigateToFlightsTab() {
		click(flightsTabLocator);
		log.info("Flights tab loaded");
	}
	
	public void clickDepartureField() {
		click(DepartureDateLocator);
		log.info("Departure Field is clicked");
	}	
	
	public void selectDepartureDateonCurrentMonth() {
		List<WebElement> allValidDates = findAll(CurrentMonthLocator);
		for (WebElement date : allValidDates) {
			String dateSelected = date.getAttribute("data-day");
			log.info("Current Date: " + date.getAttribute("aria-label"));
			if (dateSelected.contains("17")) {
				date.click();
				log.info("Selected Departure Date: " + date.getAttribute("aria-label"));
				break;
			}
			}
		}
	
	public void selectArrivalDateonCurrentMonth() {
		List<WebElement> allValidDates = findAll(CurrentMonthLocator);
		for (WebElement date : allValidDates) {
			String dateSelected = date.getAttribute("data-day");
			log.info("Current Date: " + date.getAttribute("aria-label"));
			if (dateSelected.contains("22")) {
				date.click();
				log.info("Selected Arrival Date: " + date.getAttribute("aria-label"));
				break;
			}
			}	
		}
	
	public void selectMonth() {
		for (i=0; i<=3; i++) {
			click(fwMonthNavigatorLocator);	
			//this will be clicked 4x
		}
		List<WebElement> allValidMonths = findAll(monthLocator);
		for (WebElement month : allValidMonths) { //for each month in months
			if (month.getText().contains("December 2022")){
				log.info("Current Month: " + month.getText());
			}
			}
		}
	
	public void selectDepartureDate() {
		List<WebElement> allValidDates = findAll(CurrentMonthLocator);
		for (WebElement date : allValidDates) {
			String dateSelected = date.getAttribute("data-day");
			log.info("Current Date: " + date.getAttribute("aria-label"));
			if (dateSelected.contains("17")) {
				date.click();
				log.info("Selected Departure Date: " + date.getAttribute("aria-label"));
				break;
			}
			}
		}
		
	
	public void selectArrivalDate() {
		List<WebElement> allValidDates = findAll(CurrentMonthLocator);
		for (WebElement date : allValidDates) {
			String dateSelected = date.getAttribute("data-day");
			log.info("Current Date: " + date.getAttribute("aria-label"));
			if (dateSelected.contains("22")) {
				date.click();
				log.info("Selected Arrival Date: " + date.getAttribute("aria-label"));
				break;
			}
			}
	}
	
	public void clickDoneButton() {
		click(doneButtonLocator);
		log.info("Dates are selected and Done button is clicked");
	}
		
	public String getDepartureDate() {
		String departureDate = find(DepartureDateLocator).getText();
		log.info("Actual Departure Date: " + departureDate);
		return departureDate;
	}
	
	public String getArrivalDate() {
		String arrivalDate = find(ArrivalDateLocator).getText();
		log.info("Actual Arrival Date: " + arrivalDate);
		return arrivalDate;
	}
	
}
