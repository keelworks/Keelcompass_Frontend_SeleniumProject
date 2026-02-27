package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import driverfactory.DriverManager;
import utils.ConfigReader;

public class BaseTest {

	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		// Initialize browser based on config.properties (or DriverManager.setBrowser()
		// if you use it)
		DriverManager.initBrowser();

		// Navigate to base URL
		String url = ConfigReader.getProperty("url");
		DriverManager.getDriver().get(url);
	}

	public WebDriver getDriver() {
		return DriverManager.getDriver();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		DriverManager.quitDriver(); // uses the method you added above
	}
}