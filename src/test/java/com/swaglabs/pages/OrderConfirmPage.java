package com.swaglabs.pages;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderConfirmPage {

	WebDriver driver;

	public OrderConfirmPage(WebDriver IDriver) {
		driver = IDriver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "continue")
	WebElement continueBtn;

	@FindBy(className = "inventory_item_price")
	List<WebElement> productsPrices;

	@FindBy(className = "summary_tax_label")
	WebElement taxAmount;

	@FindBy(xpath = "//div[contains(@class,'summary_info_label summary_total_label')]")
	WebElement subTotalLabel;

	@FindBy(id = "finish")
	WebElement finishBtn;

	@FindBy(className = "complete-header")
	WebElement confirmationMessage;

	public void completingOrder() throws IOException {

		continueBtn.click();

		double actualSubtotal = calculateActualSubtotal();
		double expectedSubtotal = calculateExpectedSubtotal();

		if (actualSubtotal == expectedSubtotal) {
			System.out.println("The Subtotal that displayed on the page is $" + actualSubtotal
					+ " & this is matching with our Expectedtotal $" + expectedSubtotal + " that we calculated");
		} else {
			System.out.println(
					"The Subtotal is incorrect: Expected: $" + expectedSubtotal + ", but Actual: $" + actualSubtotal);
		}

		finishBtn.click();

		String message = confirmationMessage.getText();
		System.out.println(
				"Woohooo!, we successfully placed an order, I confirm this by the message on the site. Here is the message: "
						+ message);

		TakesScreenshot screenshot = ((TakesScreenshot) driver);

		File source = screenshot.getScreenshotAs(OutputType.FILE);
		File sourcepath = new File("." + "//Screenshots//" + "OrderConfirmation" + ".png");

		FileUtils.copyFile(source, sourcepath);
	}

	private double calculateActualSubtotal() {
		double totalPrice = 0.0;

		for (WebElement productPriceElement : productsPrices) {
			String priceText = productPriceElement.getText().replace("$", "");
			double price = Double.parseDouble(priceText);
			totalPrice += price;
		}

		return totalPrice + getTax();
	}

	public double calculateExpectedSubtotal() {
		return parseSubtotal(subTotalLabel.getText());
	}

	public double getTax() {
		return parseSubtotal(taxAmount.getText());
	}

	public double parseSubtotal(String subtotalText) {
		String numericSubtotalText = subtotalText.replaceAll("[^0-9.]", "");
		return Double.parseDouble(numericSubtotalText);
	}

}
