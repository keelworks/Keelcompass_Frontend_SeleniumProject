package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import io.qameta.allure.Allure;
import driverfactory.DriverManager;
import java.io.ByteArrayInputStream;


public class TestListener implements ITestListener{
	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver =DriverManager.getDriver();
        String testName = result.getMethod().getMethodName();
        System.out.println("Test failed: " + testName + ". Capturing screenshot...");
        try {
            // Cast the WebDriver to TakesScreenshot
            TakesScreenshot ts = (TakesScreenshot) driver;
            // Get the screenshot as bytes
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(testName, new ByteArrayInputStream(screenshot));
        } catch (Exception e) {
            Reporter.log("Error capturing screenshot: " + e.getMessage());
        }
    }
}