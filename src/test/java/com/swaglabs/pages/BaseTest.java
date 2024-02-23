package com.swaglabs.pages;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.swaglabs.utility.BrowserFactory;
import com.swaglabs.utility.ConfigDataProviders;

public class BaseTest {

	public WebDriver driver;
	public ConfigDataProviders config = new ConfigDataProviders();

	@BeforeClass
	public void setUp() {
		driver = BrowserFactory.startApplication(driver, config.getBrowser(), config.getStagingUrl());
	}

	@AfterClass
	public void tearDown() {
		BrowserFactory.quiteBrowser(driver);
	}
	
	public void captureScreenshot(WebDriver driver, String testName) throws IOException {
		
		TakesScreenshot screenshot = ((TakesScreenshot)driver);
		
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		File sourcepath = new File("."+"//Screenshots//"+testName+".png");
		
		FileUtils.copyFile(source, sourcepath);
	}
}
