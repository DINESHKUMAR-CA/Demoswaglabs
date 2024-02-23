package com.swaglabs.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.swaglabs.pages.BaseTest;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.pages.ProductPage;
import com.swaglabs.utility.ReadExcelFile;

public class ProductPageTest extends BaseTest {

	String excelFileName = System.getProperty("user.dir") + "\\TestData\\SwagLabsPortalData.xlsx";

	@Test
	public void productpageValidation() throws InterruptedException {

		LoginPage page = new LoginPage(driver);
		String personname = ReadExcelFile.getCellValue(excelFileName, "LoginData", 1, 0);
		String userpwd = ReadExcelFile.getCellValue(excelFileName, "LoginData", 1, 1);
		System.out.println("Username--> " + personname + "    Password--> " + userpwd);
		page.loginToPortal(personname, userpwd);

		ProductPage pp = new ProductPage(driver);

		// Geting initial product count on product page
		int initialProductCount = pp.getProductsCount();

		// printing initial cart status
		if (initialProductCount == 0) {
			System.out.println("Initially the cart is empty");
		} else {
			System.out.println("Initially there are " + initialProductCount + " items in the cart");
		}

		// Adding products to the cart
		pp.productToCartAndSorting();

		// Verifying product addition
		int updatedProductCount = pp.getProductsCount();
		System.out.println("Now, the cart contains " + updatedProductCount + " product after adding");

		// Checking the product count has been increased or not
		Assert.assertEquals(updatedProductCount, initialProductCount + 2, "Products are not added to the cart as expected");
		
		//Verifying all the 6 products are present on the product page
		boolean allProductsPresent = pp.isAllProductsPresent();
		Assert.assertTrue(allProductsPresent, "Not all the products are present on the product page");
		
		//If all 6 products are present, then print it's count
		int productCount = pp.productNames.size();
		System.out.println("Yes, all the "+productCount+" products are present on the product page");
		
		pp.fetchingProductPricesNames();
		pp.sortingProducts();

	}

}
