package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FileUploaderPage extends BasePageObject {

	private String pageUrl = "http://the-internet.herokuapp.com/upload";

	private By choseFileFieldLocator = By.id("file-upload");
	private By uploadButtonLocator = By.id("file-submit");
	private By uploadedFilesLocator = By.id("uploaded-files"); // this is displayed after successfully uploading

	public FileUploaderPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	// Open FileUploaderPage with its URL
	public void openPage() {
		log.info("Opening page: " + pageUrl);
		openURL(pageUrl);
		log.info("Upload Page opened!");
	}

	/*
	 * Click the Choose File button public void clickChooseFileButton() {
	 * log.info("Clicking the Choose File button"); click(choseFileFieldLocator); }
	 * &
	 */ // can't do click because choseFileFieldLocator is an input field, not button

	// Click the Upload button
	public void clickUploadButton() {
		log.info("Clicking the Upload button");
		click(uploadButtonLocator);
	}

	// Select files to upload
	public void selectFile(String uploadedfileName) {
		log.info("Selecting '" + uploadedfileName + "' file from Files folder");
		// String filePath = "C:\\Users\\Ronna Lyn D. Umbal\\Downloads\\Alpha & Beta Test.jpg";
			// Selecting file from outside path
		String filePath = System.getProperty("user.dir") + "//src//main//resources//files//" + uploadedfileName;
			// selecting file from java resource folder
		enterValue(filePath, choseFileFieldLocator); // method from BasePageObject
		log.info("File is selected");
	}

	// Get names of uploaded files
	public String getUploadedFileName() {
		String UploadedFileName = find(uploadedFilesLocator).getText();
		log.info("Uploaded file: " + UploadedFileName);
		return UploadedFileName;
	}

}
