package com.swaglabs.testcases;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.swaglabs.pages.BaseTest;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.utility.ReadExcelFile;

public class LoginTest extends BaseTest {

	String excelFileName = System.getProperty("user.dir") + "\\TestData\\SwagLabsPortalData.xlsx";

	@Test(priority = 1, dataProvider = "LoginDataProvider")
	public void verifyLogin(String personname, String userpwd) throws IOException {

		LoginPage page = new LoginPage(driver);
		page.loginToPortal(personname, userpwd);

		if (personname.equals("visual_user") && userpwd.equals("secret_sauce")) {
			System.out.println("Woohoooo!! We successfully logged into portal with username: " + personname
					+ " & userpassword: " + userpwd);
		} else {
			captureScreenshot(driver, "VerifyLogin");
			System.out.println("Oh..Noooo!! The test is failed");
		}
	}

	@DataProvider(name = "LoginDataProvider")
	public String[][] getLoginData() {

		int totalRows = ReadExcelFile.getRowCount(excelFileName, "LoginData");
		String[][] data = new String[totalRows - 1][2];

		for (int i = 1; i < totalRows; i++) {
			data[i - 1][0] = ReadExcelFile.getCellValue(excelFileName, "LoginData", i, 0); // Username
			data[i - 1][1] = ReadExcelFile.getCellValue(excelFileName, "LoginData", i, 1); // Password
		}
		return data;
	}

}
