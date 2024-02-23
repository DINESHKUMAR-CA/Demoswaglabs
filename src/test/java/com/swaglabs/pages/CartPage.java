package com.swaglabs.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BaseTest {

	WebDriver driver;

	public CartPage(WebDriver IDriver) {

		driver = IDriver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "shopping_cart_container")
	WebElement cartIcon;

	@FindBy(className = "cart_list")
	WebElement cartList;

	@FindBy(className = "cart_item")
	List<WebElement> cartItems;

	@FindBy(id = "continue-shopping")
	WebElement continueShopping;

	@FindBy(id = "add-to-cart-sauce-labs-fleece-jacket")
	WebElement jacketaddToCartBtn;

	public void navigatingToCartpage() {

		cartIcon.click();

		if (isProductsInCart()) {
			System.out.println(
					"The products are present in the cart\nThere are " + getProductsCountInCart() + " products");
		} else {
			System.out.println("Sorry! No products are found");
		}

		continueShopping.click();
		jacketaddToCartBtn.click();

		cartIcon.click();

		System.out.println("After adding another item, the number of products on cartpage is changed to "
				+ getProductsCountInCart());
	}

	public boolean isProductsInCart() {
		return cartList.isDisplayed() && cartItems.size() > 0;
	}

	public int getProductsCountInCart() {
		return cartItems.size();
	}

}
