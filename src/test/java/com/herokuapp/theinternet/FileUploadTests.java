package com.herokuapp.theinternet;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.FileUploaderPage;

public class FileUploadTests extends TestUtilities {

	@Test(priority=1)
	public void imageUploadTest() {
		log.info("Starting imageUploadTest");

		// Load File Uploader Page
		FileUploaderPage fileUploaderPage = new FileUploaderPage(driver, log);
		fileUploaderPage.openPage();

		// Click Choose File button -- CAUSES InvalidArgumentException, Choose File is input type in F12
		//fileUploaderPage.clickChooseFileButton();

		// Select a file to upload
		String selectedFileName = "Moscato.jpg";
		fileUploaderPage.selectFile(selectedFileName);
		sleepDisplay(2000);

		// Click Upload button
		fileUploaderPage.clickUploadButton();

		// Get uploaded file
		String uploadedFile = fileUploaderPage.getUploadedFileName();
		sleepDisplay(2000);

		// Verify selected file uploaded
		Assert.assertTrue(uploadedFile.contains(selectedFileName),
				"Our file (" + selectedFileName + ") is not one of the uploaded (" + uploadedFile + ")");
	}

	@Test(priority=2,dataProvider="Files")
	public void uploadWithDataProviderTest(int no, String selectedFileName) {
			//int & string values will get the data returned from Object method with @DataProvider annotation in TestUtilities class
		log.info("Starting uploadWithDataProviderTest #" + no + " for " + selectedFileName);

		// Load File Uploader Page
		FileUploaderPage fileUploaderPage = new FileUploaderPage(driver, log);
		fileUploaderPage.openPage();

		// Select file to upload
		fileUploaderPage.selectFile(selectedFileName);
		sleepDisplay(2000);

		// Click Upload button
		fileUploaderPage.clickUploadButton();

		// Get uploaded files
		String uploadedFiles = fileUploaderPage.getUploadedFileName();
		sleepDisplay(2000);

		// Verify selected file uploaded
		Assert.assertTrue(uploadedFiles.contains(selectedFileName),
				"Our file (" + selectedFileName + ") is not one of the uploaded (" + uploadedFiles + ")");
	}

}