package com.herokuapp.theinternet.base;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class BrowserDriverFactory {

	private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	private String Browser;
	private Logger log;

	public BrowserDriverFactory(String Browser, Logger log) {
		this.Browser = Browser.toString();
		this.log = log;
	}

	public WebDriver createDriver() {
		// Create Driver
		log.info("Creating Driver: " + Browser);

		switch (Browser) {
		case "Chrome":
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver.set(new ChromeDriver());
			break;

		case "Firefox":
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
			driver.set(new FirefoxDriver());
			break;

		case "ChromeHeadless":
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--headless");
			driver.set(new ChromeDriver(chromeOptions));
			break;

		case "FirefoxHeadless":
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
			FirefoxBinary firefoxBinary = new FirefoxBinary();
			firefoxBinary.addCommandLineOptions("--headless");
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setBinary(firefoxBinary);
			driver.set(new FirefoxDriver(firefoxOptions));
			break;

		case "PhantomJS":
			System.setProperty("phantomjs.binary.path", "src/main/resources/phantomjs.exe");
			driver.set(new PhantomJSDriver());
			break;

		case "HtmlUnit":
			driver.set(new HtmlUnitDriver());
			break;

		default:
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver.set(new ChromeDriver());
			log.info("Cannot start " + Browser + "\nStarting Chrome instead.");
			break;
		}

		return driver.get();
	}

	public WebDriver createChromeWithMobileEmulation(String deviceName) {
		log.info("Starting driver with " + deviceName + " emulation");
		Map<String, String> mobileEmulation = new HashMap<>();
		mobileEmulation.put("deviceName", deviceName);
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		driver.set(new ChromeDriver(chromeOptions));
		return driver.get();
	}
}
