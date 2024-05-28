package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddTocartObjects {
	
	@FindBy(xpath ="//h4[@class='product-name']")
	public static WebElement productName;
	
	@FindBy(xpath ="//div[@class='product-action']/button")
	public static WebElement addToCart;
	
	@FindBy(xpath="//a[@class='cart-icon']")
	public static WebElement goToCart;
	
	@FindBy(xpath = "// button[text()='PROCEED TO CHECKOUT']")
	public static WebElement proceedTocheckout;
	
	@FindBy(xpath="//*[@class='quantity']")
	public static WebElement totalItem;
	
	@FindBy(xpath="//button[text()='Place Order']")
	public static WebElement placeOrder;
	
	@FindBy(xpath="//*[@class='wrapperTwo']/div/select")
	public static WebElement selectCountry;
	
	@FindBy(xpath="//label[text()='Choose Country']")
	public static WebElement selectCountryPage;
	
	@FindBy(xpath="//input[@class='chkAgree']")
	public static WebElement checkButton;
	
	@FindBy(xpath="//button[text()='Proceed']")
	public static WebElement proceed;
	
}
