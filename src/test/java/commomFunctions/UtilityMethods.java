package commomFunctions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UtilityMethods {
	
	public static WebElement explicitWait(WebElement element, WebDriver driver) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(element));
		return ele;
	}
	
	public static void selectElement(WebElement element, String Text) {
		
		Select select = new Select(element);
		select.selectByVisibleText(Text);
		
		
	}

}
