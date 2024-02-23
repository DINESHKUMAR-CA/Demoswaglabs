package com.swaglabs.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends BaseTest {

	WebDriver driver;

	public ProductPage(WebDriver IDriver) {

		driver = IDriver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className = "shopping_cart_badge")
	WebElement cartItemCount;

	@FindBy(className = "inventory_item_name")
	public List<WebElement> productNames;

	@FindBy(className = "inventory_item_price")
	List<WebElement> productPrices;

	@FindBy(xpath = "//button[contains(@id,\"labs-backpack\")]")
	WebElement addToCartBtn1;

	@FindBy(name = "add-to-cart-sauce-labs-bike-light")
	WebElement addToCartBtn2;

	@FindBy(className = "product_sort_container")
	WebElement sortDropdown;

	public void productToCartAndSorting() {

		addToCartBtn1.click();
		addToCartBtn2.click();
		sortDropdown.click();
	}

	public int getProductsCount() {
		try {
			String itemCountText = cartItemCount.getText();
			return Integer.parseInt(itemCountText);
		} catch (Exception e) {
			return 0;
		}
	}

	public boolean isAllProductsPresent() {

		return productNames.size() == 6 && productPrices.size() == 6;
	}

	public void fetchingProductPricesNames() {

		for (int i = 0; i < productNames.size(); i++) {
			String name = productNames.get(i).getText();
			String price = productPrices.get(i).getText();
			System.out.println("The product Name: " + name + " & The product price: " + price);
		}
	}

	public List<String> getProductNames() {

		List<String> names = new ArrayList<>();
		for (WebElement productName : productNames) {
			names.add(productName.getText());
		}
		return names;
	}

	public void sortingProducts() {

		Select sortingDropdown = new Select(sortDropdown);
		sortingDropdown.selectByValue("za");
		System.out.println("The products are sorted to Z-A");

		List<String> currentProductNames = getProductNames();
		boolean isSorted = checkProductSorting(currentProductNames);
		if (isSorted) {
			System.out.println("Products are sorted");
		} else {
			System.out.println("Products are not sorted");
		}
	}

	public boolean checkProductSorting(List<String> names) {
		List<String> sortedNames = new ArrayList<>(names);
		Collections.sort(sortedNames, Collections.reverseOrder());

		boolean sorted = names.equals(sortedNames);

		if (sorted) {
			System.out.println("The products are sorted to \n" + sortedNames);
		} else {
			System.out.println("The products are not sorted");
		}
		return sorted;
	}
}
