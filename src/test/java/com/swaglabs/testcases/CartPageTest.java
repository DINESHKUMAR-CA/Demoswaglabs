package com.swaglabs.testcases;

import org.testng.annotations.Test;

import com.swaglabs.pages.BaseTest;
import com.swaglabs.pages.CartPage;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.pages.ProductPage;
import com.swaglabs.utility.ReadExcelFile;

public class CartPageTest extends BaseTest {
	
	String excelFileName = System.getProperty("user.dir")+"\\TestData\\SwagLabsPortalData.xlsx";
	@Test
	public void jumpingToCartPage() throws InterruptedException {
		
		LoginPage page = new LoginPage(driver);
		String personname = ReadExcelFile.getCellValue(excelFileName, "LoginData", 1, 0);
		String userpwd = ReadExcelFile.getCellValue(excelFileName, "LoginData", 1, 1);	
		page.loginToPortal(personname, userpwd);
		
		ProductPage pp = new ProductPage(driver);
		pp.productToCartAndSorting();
		
		CartPage cPage = new CartPage(driver);
		cPage.navigatingToCartpage();
	}
}
