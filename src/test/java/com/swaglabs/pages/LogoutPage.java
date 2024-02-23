package com.swaglabs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogoutPage {

	WebDriver driver;

	public LogoutPage(WebDriver IDriver) {

		this.driver = IDriver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "back-to-products")
	WebElement backToHomeBtn;

	@FindBy(id = "react-burger-menu-btn")
	WebElement burgerMenu;

	@FindBy(id = "logout_sidebar_link")
	WebElement logoutBtn;

	public void logingOutFromPortal() {

		backToHomeBtn.click();
		burgerMenu.click();
		logoutBtn.click();
		
		System.out.println("We successfully logged out from portal");
	}
}
