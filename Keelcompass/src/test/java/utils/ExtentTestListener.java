package utils;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.ISuite;
import org.testng.ISuiteListener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
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
        String methodName = result.getMethod().getMethodName();
        Object[] params = result.getParameters();

        String testName = methodName;
        if (params != null && params.length > 0 && params[0] != null) {
            testName = methodName + " - " + params[0].toString();
        }

        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
        ExtentTestManager.setTest(extentTest);

        extentTest.info("Test started: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest extentTest = test.get();
        if (extentTest != null) {
            extentTest.pass("Test passed");
        }
        cleanupThreadLocal();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest extentTest = test.get();

        if (extentTest != null) {
            Throwable throwable = result.getThrowable();
            if (throwable != null) {
                extentTest.fail(throwable);
            } else {
                extentTest.fail("Test failed");
            }

            WebDriver driver = DriverManager.getDriver();
            if (driver != null) {
                try {
                    String base64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
                    extentTest.addScreenCaptureFromBase64String(base64, "Failure Screenshot");
                } catch (Exception e) {
                    extentTest.warning("Could not capture screenshot: " + e.getMessage());
                }
            }
        } else {
            System.out.println("ExtentTest is null in onTestFailure for: " + result.getName());
        }

        cleanupThreadLocal();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest extentTest = test.get();
        if (extentTest != null) {
            extentTest.skip("Test skipped");
            if (result.getThrowable() != null) {
                extentTest.skip(result.getThrowable());
            }
        }
        cleanupThreadLocal();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ExtentTest extentTest = test.get();
        if (extentTest != null) {
            extentTest.warning("Test partially failed (within success percentage).");
        }
    }

    private void cleanupThreadLocal() {
        test.remove();
        ExtentTestManager.removeTest();
    }
}