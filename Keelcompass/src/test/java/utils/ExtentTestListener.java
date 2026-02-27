package utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import driverfactory.DriverManager;

public class ExtentTestListener implements ITestListener, ISuiteListener {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    private static String reportPath;

    @Override
    public void onStart(ISuite suite) {
        extent = new ExtentReports();

        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        reportPath = System.getProperty("user.dir") + File.separator + "test-output"
                + File.separator + "ExtentReport_" + time + ".html";

        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        spark.config().setReportName("Automation Test Report");
        spark.config().setDocumentTitle("Test Execution Report");

        extent.attachReporter(spark);

        extent.setSystemInfo("Suite", suite.getName());
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java", System.getProperty("java.version"));
    }

    @Override
    public void onFinish(ISuite suite) {
        if (extent != null) {
            extent.flush();
        }
        System.out.println("Extent report generated at: " + reportPath);
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String className = result.getTestClass().getName();

        ExtentTest extentTest = extent.createTest(testName).assignCategory(className);
        test.set(extentTest);
        ExtentTestManager.setTest(extentTest);

        test.get().info("Test started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Throwable t = result.getThrowable();
        if (t != null) test.get().fail(t);

        // Screenshot attach
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            try {
                String base64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
                test.get().addScreenCaptureFromBase64String(base64, "Failure Screenshot");
            } catch (Exception e) {
                test.get().warning("Could not capture screenshot: " + e.getMessage());
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("Test skipped");
        if (result.getThrowable() != null) test.get().skip(result.getThrowable());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        test.get().warning("Test partially failed (within success percentage).");
    }
}