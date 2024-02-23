package com.swaglabs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseTest {

	WebDriver driver;

	public LoginPage(WebDriver lDriver) {

		driver = lDriver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "user-name")
	WebElement personname;

	@FindBy(id = "password")
	WebElement userpwd;

	@FindBy(id = "login-button")
	WebElement loginbtn;

	public void loginToPortal(String username, String password) {
		
		personname.sendKeys(username);
		userpwd.sendKeys(password);
		loginbtn.click();
	}
}
