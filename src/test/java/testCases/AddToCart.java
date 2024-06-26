package testCases;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;

import commomFunctions.BaseClass;
import commomFunctions.UtilityMethods;
import pageObjects.AddTocartObjects;

public class AddToCart extends BaseClass {

	@Test
	public static void itemsAddTocart() throws Exception {
		
		PageFactory.initElements(driver, AddTocartObjects.class);
		extentTest = extentReport.createTest("Add to cart");
		extentTest.info("On the landing Page");
		String itemName[] = { "Cucumber", "Brocolli", "Brinjal", "Beans" };
		int count = 0;
		List<WebElement> productName = driver.findElements(By.xpath("//h4[@class='product-name']"));
		for (int i = 0; i < productName.size(); i++) {

			String actualItemNameArray[] = productName.get(i).getText().split("-");
			String actualItemName = actualItemNameArray[0].trim();
			ArrayList<String> productnameList = new ArrayList<String>(Arrays.asList(itemName));
			if (productnameList.contains(actualItemName)) {
				count++;
				Thread.sleep(1500);
				driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
				extentTest.pass("Item added to cart", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenShotb64()).build());
				
				if (count == itemName.length) {
					break;
				}
			}

		}

		Thread.sleep(2000);
		
		AddTocartObjects.goToCart.click();
		AddTocartObjects.proceedTocheckout.click();
		extentTest.info("On the checkout Page");
		Thread.sleep(1500);
		UtilityMethods.explicitWait(AddTocartObjects.placeOrder, driver);
		/*WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(AddTocartObjects.placeOrder));*/
		extentTest.pass("on the checkout page", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenShotb64()).build());
		List<WebElement> totalItemCount = driver.findElements(By.xpath("//*[@class='quantity']"));
		if (totalItemCount.size() == itemName.length) {
			AddTocartObjects.placeOrder.click();
			UtilityMethods.explicitWait(AddTocartObjects.selectCountryPage, driver);
			extentTest.info("On the Place order Page");
			UtilityMethods.selectElement(AddTocartObjects.selectCountry, "India");
			/*Select select = new Select(AddTocartObjects.selectCountry);
			select.selectByVisibleText("India");*/
			Thread.sleep(1000);
			AddTocartObjects.checkButton.click();
			extentTest.pass("on the place order page", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenShotb64()).build());
			AddTocartObjects.proceed.click();

		}

	}

}
