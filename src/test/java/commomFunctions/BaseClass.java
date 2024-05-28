package commomFunctions;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	protected static WebDriver driver = null;
	protected static Properties properties = null;
	protected static ExtentReports extentReport = new ExtentReports();
	protected static ExtentTest extentTest;
	protected static ExtentSparkReporter spark;
	protected String reportname = date();

	public static Properties loadProperties() throws Exception {

		FileInputStream fs = new FileInputStream("Configurations.properties");
		properties = new Properties();
		properties.load(fs);
		return properties;

	}

	@SuppressWarnings("deprecation")
	@BeforeSuite
	public void launchBrowser() throws Exception {
		loadProperties();
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: properties.getProperty("browser");
		String userName = System.getProperty("userName") != null ? System.getProperty("userName")
				: properties.getProperty("userName");
		String password = System.getProperty("password") != null ? System.getProperty("password")
				: properties.getProperty("password");
		String url = System.getProperty("url") != null ? System.getProperty("url") : properties.getProperty("url");

		if (browserName.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().clearDriverCache().setup();
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().clearDriverCache().setup();
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().clearDriverCache().setup();
			driver = new EdgeDriver();
		} else {
			System.out.println("Invalid input");
		}

		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		WebElement landingPageLogo = driver.findElement(By.xpath("//div[@class='brand greenLogo']"));
		if (landingPageLogo.isDisplayed()) {
			System.out.println("Navigate to Landing page - Log in Successful");
		} else {
			System.out.println("Invalid log in");
		}

	}
	public static String date() {

		Date date = new Date();
		String reportName = date.toString();
		reportName = reportName.replaceAll(":", ".");
		reportName = reportName.replaceAll(" ", "_");
		reportName = reportName.substring(4);
		return reportName;

	}

	@BeforeTest
	public void reportGeneration() {

		String destination = System.getProperty("user.dir") + "/reports/Report";		
		//destination+="\\Reports";
		String projectName = "TestDemo";
		spark = new ExtentSparkReporter(destination+"_"+projectName+reportname+ ".html");
		extentReport.attachReporter(spark);

	}

	public static String takeScreenShotb64() {

		String base64ScreenshotCode = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		return base64ScreenshotCode;

	}

	/*public static void takeScreenShot(WebDriver driver, String destination) throws IOException {

		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File finaldestination = new File(destination);
		FileUtils.copyFile(source, finaldestination);

	}*/

	@AfterMethod
	public void testFail(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(Status.FAIL, result.getName());
			extentTest.log(Status.FAIL, result.getThrowable());
			extentTest.fail("Test Case failed", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenShotb64()).build());
		}
	}

	@AfterSuite
	public void tearDown() {

		extentReport.flush();
		driver.close();
		driver.quit();

	}
}
