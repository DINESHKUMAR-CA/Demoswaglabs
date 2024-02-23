package com.swaglabs.testcases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.swaglabs.pages.BaseTest;
import com.swaglabs.pages.CartPage;
import com.swaglabs.pages.CheckoutPage;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.pages.LogoutPage;
import com.swaglabs.pages.OrderConfirmPage;
import com.swaglabs.pages.ProductPage;
import com.swaglabs.utility.ReadExcelFile;

public class LogoutPageTest extends BaseTest {
	
	String excelFileName = System.getProperty("user.dir")+ "\\TestData\\SwagLabsPortalData.xlsx";
	
	@Test
	public void logingoutPortal() throws InterruptedException, IOException {
		
		LoginPage page = new LoginPage(driver);
		String personname = ReadExcelFile.getCellValue(excelFileName, "LoginData", 1, 0);
		String userpwd = ReadExcelFile.getCellValue(excelFileName, "LoginData", 1, 1);
		page.loginToPortal(personname, userpwd);
		
		ProductPage pp = new ProductPage(driver);
		pp.productToCartAndSorting();
		
		CartPage cPage = new CartPage(driver);
		cPage.navigatingToCartpage();
		
		CheckoutPage cp = new CheckoutPage(driver);
		String firstName = ReadExcelFile.getCellValue(excelFileName, "UserInfo", 1, 0);
		String lastName = ReadExcelFile.getCellValue(excelFileName, "UserInfo", 1, 1);
		String zipcode = ReadExcelFile.getCellValue(excelFileName, "UserInfo", 1, 2);
		cp.userInformations(firstName, lastName, zipcode);
		
		OrderConfirmPage confirmPage = new OrderConfirmPage(driver);
		confirmPage.completingOrder();
		
		LogoutPage logout = new LogoutPage(driver);
		logout.logingOutFromPortal();
		
	}
}
