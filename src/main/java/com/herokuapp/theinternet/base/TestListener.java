package com.herokuapp.theinternet.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
	Logger log;
	String testName;
	String testMethodName;

	@Override //onTestStart means will display this on console when test started
	public void onTestStart(ITestResult result) {
		this.testMethodName = result.getMethod().getMethodName();
		log.info("Starting " + testMethodName);
	}

	@Override //onTestSuccess means will display on console when test is successful
	public void onTestSuccess(ITestResult result) {
		log.info("Test " + testMethodName + " Passed");
	}

	@Override //onTestFailure means will display on console when test failed
	public void onTestFailure(ITestResult result) {
		log.info("Test " + testMethodName + " Failed");
	}

	@Override //onTestSkipped means will display on console when test is skipped
	public void onTestSkipped(ITestResult result) {
		log.info("Test " + testMethodName + "Skipped");

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override //onStart [means this will run before your method with @BeforeMethod]
	public void onStart(ITestContext context) {
		this.testName = context.getCurrentXmlTest().getName();
		this.log = LogManager.getLogger(testName);
		log.info("TEST " + testName + " STARTED");
	}

	@Override //onFinish [means this will run after all other methods as well method with @AfterMethod are done]
	public void onFinish(ITestContext context) {
		log.info("ALL " + testName + " FINISHED");
	}
}
