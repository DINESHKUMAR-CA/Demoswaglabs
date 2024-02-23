package com.swaglabs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BaseTest {

	WebDriver driver;

	public CheckoutPage(WebDriver IDriver) {

		driver = IDriver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "checkout")
	WebElement checkoutBtn;

	@FindBy(id = "first-name")
	WebElement userFirstName;

	@FindBy(name = "lastName")
	WebElement userLastName;

	@FindBy(id = "postal-code")
	WebElement postalcode;

	public void userInformations(String firstName, String lastName, String zipcode) {

		checkoutBtn.click();
		userFirstName.sendKeys(firstName);
		userLastName.sendKeys(lastName);
		postalcode.sendKeys(zipcode);

	}
}
