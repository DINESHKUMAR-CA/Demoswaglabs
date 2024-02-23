package com.swaglabs.utility;

import java.time.Duration;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {

	public static WebDriver startApplication(WebDriver driver, String browserName, String appUrl) {

		if (browserName.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();
		} else if (browserName.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
		} else {
			System.out.println("Sorry! We are not supported by the provided browser");
		}

		driver.manage().window().maximize();
		driver.get(appUrl);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		return driver;
	}

	public static void quiteBrowser(WebDriver driver) {
		driver.quit();
	}
}
