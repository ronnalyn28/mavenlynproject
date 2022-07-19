package com.herokuapp.theinternet.base;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

@Listeners({ com.herokuapp.theinternet.base.TestListener.class })

public class BaseTest {
	protected WebDriver driver;
	protected Logger log;

	protected String testSuiteName;
	protected String testName;
	protected String testMethodName;

	@Parameters({ "Browser", "deviceName" })
	@BeforeMethod(alwaysRun = true) // this method will be run before every other methods in @Test
	// alwaysRun means this method will always be run regardless if its included or
	// not in the group/method listed in TestNGGroups.xml
	public void setUp(Method method, @Optional("Chrome") String Browser, @Optional String deviceName, ITestContext ctx) {
		String testName = ctx.getCurrentXmlTest().getName();
		log = LogManager.getLogger(testName);

		BrowserDriverFactory factory = new BrowserDriverFactory(Browser, log);
		if (deviceName != null) {
			driver = factory.createChromeWithMobileEmulation(deviceName);
		} else {
			driver = factory.createDriver();
		}

		// Maximize Browser Window
		driver.manage().window().maximize();

		this.testSuiteName = ctx.getSuite().getName(); // return the value of TestSuite xml in TestSuites folder
		this.testName = testName;
		//this.testMethodName = new Object() {}
			//.getClass()
			//.getEnclosingMethod()
			//.getName();
		this.testMethodName = method.getName(); //will return method name from class
	}

	@AfterMethod(alwaysRun = true) // this method will be run after every other methods in @Test
	// alwaysRun means this method will always be run regardless if its included or
	// not in the group/method listed in TestNGGroups.xml
	public void closeBrowser() {
		// Close Browser
		driver.quit();
		log.info("Closing Browser");
	}

}
