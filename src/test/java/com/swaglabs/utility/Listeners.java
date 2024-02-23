package com.swaglabs.utility;

import java.io.File;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners implements ITestListener {

	static ExtentSparkReporter htmlReporter;
	static ExtentReports reports;
	static ExtentTest test;

	public void configureReport() {

		htmlReporter = new ExtentSparkReporter("ListenersReport.html");
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);

		reports.setSystemInfo("Machine", "DineshkumarPC");
		reports.setSystemInfo("OS", "Windows 11");

		htmlReporter.config().setDocumentTitle("Listeners Report");
		htmlReporter.config().setReportName("My first report");
		htmlReporter.config().setTheme(Theme.DARK);
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("The test method successfully executed " + result.getName());
		test = reports.createTest(result.getName());
		test.log(Status.PASS,
				MarkupHelper.createLabel("Name of the passed testcase is: " + result.getName(), ExtentColor.GREEN));
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("The name of the failed method is: " + result.getName());
		test = reports.createTest(result.getName());
		test.log(Status.FAIL,
				MarkupHelper.createLabel("Name of the failed testcase is: " + result.getName(), ExtentColor.RED));

		String screenShotPath = System.getProperty("user.dir") + "\\Screenshots\\" + result.getName() + ".png";

		File screenShotFile = new File(screenShotPath);
		if (screenShotFile.exists()) {
			test.fail("Capatured screenshot already exists: " + test.addScreenCaptureFromPath(screenShotPath));
		}
	}

	public void onTestSkip(ITestResult result) {
		System.out.println("The name of the skipped test is: " + result.getName());
		test = reports.createTest(result.getName());
		test.log(Status.SKIP,
				MarkupHelper.createLabel("Name of the skipped testcase is: " + result.getName(), ExtentColor.YELLOW));
	}

	public void onStart(ITestContext result) {
		configureReport();
		System.out.println("The onStart method is invoked");
	}

	public void onFinish(ITestContext context) {
		System.out.println("The onFinish method is invoked");
		reports.flush();
	}
}
