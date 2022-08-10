package com.herokuapp.theinternet;


import org.testng.annotations.Test;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.MySqlMain;

public class MySqlTests extends TestUtilities {
	
	@Test
	public void sqlTest() {

	// Connect to DB
	MySqlMain mySqlMain = new MySqlMain(driver, log);
	mySqlMain.connectDB();
	takeScreenshot("MySqlMain Connection");
	
	mySqlMain.executeQuery();
	takeScreenshot("Query Executed");
	
	//Close DB connection
	mySqlMain.closeDB();
	takeScreenshot("DB Connection Closed");

	
	}
}
