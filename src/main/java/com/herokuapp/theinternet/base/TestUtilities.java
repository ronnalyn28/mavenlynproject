package com.herokuapp.theinternet.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.testng.annotations.DataProvider;

public class TestUtilities extends BaseTest { 
	//this will run BaseTest.java first (w/c is the Parent class)
	protected void sleepDisplay(long m) { //protected means it can run in a child class (LoginTests)
		// using 'm' so you can define milliseconds based on your preference
		try {
			Thread.sleep(m); //this is explicit wait that you should avoid
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Take screenshot
	protected void takeScreenshot(String fileName) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") //user.dir finds the directory of eclipse project
				+ File.separator + "test-output" // test-output is the folder here in the project
				+ File.separator + "screenshots" // Under test-output, a new folder is created 'screenshots'
				+ File.separator + getTodaysDate() //under screenshots, a new folder is created for current date
				+ File.separator + testSuiteName 
				+ File.separator + testName
				+ File.separator + testMethodName 
				+ File.separator + getSystemTime() 
				+ " " + fileName + ".png"; // filename generated
		try {
			FileUtils.copyFile(scrFile, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Today's date in yyyyMMdd format
	private static String getTodaysDate() {
		return (new SimpleDateFormat("yyyyMMdd").format(new Date()));
			//will return current date in the format specified
	}

	// Current time in HHmmssSSS
	private String getSystemTime() {
		return (new SimpleDateFormat("HHmmssSSS").format(new Date()));
		//will return current time in the format specified
	}

	// Get logs from browser console
	//Manual checking of this is by clicking F12 on an open browser and checking for Javascript errors on console
	protected List<LogEntry> getBrowserLogs() {
		LogEntries log = driver.manage().logs().get("browser");
			//get logs of browser
		List<LogEntry> logList = log.getAll();
			//will return list of log entries
		return logList;
	}
	
	@DataProvider(name="Files")
	protected static Object[][] Files() { //will return arrays of arrays of objects
		return new Object[][] {
			{1,"Moscato.jpg"},
			{2,"sampletxt.txt"} //this are the files in src/main/resources, Files folder
		};
	}
}
