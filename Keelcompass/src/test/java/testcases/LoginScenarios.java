package testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import com.pages.HomePage;
import com.pages.LoginPage;
import utils.ExcelReader;
import utils.ExtentTestManager;

@Listeners(utils.ExtentTestListener.class)
public class LoginScenarios extends BaseTest {

    private static final Logger log = LogManager.getLogger(LoginScenarios.class);

    @Test(dataProvider = "loginScenario")
    public void loginScenarios(String scenarioname, String username, String password) {

        WebDriver driver = getDriver();
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        ExtentTestManager.getTest().info("Executing scenario: " + scenarioname);
        ExtentTestManager.getTest().info("Navigated to login page. URL: " + driver.getCurrentUrl());
        ExtentTestManager.getTest().info("Executing login with test data from Excel");

        log.info("Starting login scenario with username: {}", username.isEmpty() ? "[blank]" : username);
        Assert.assertTrue(loginPage.isLoginTitleDisplayed(), "Login page is not displayed");

        loginPage.login(username, password);

        boolean homeLoaded = homePage.isHomePageTitleDisplayed();
        ExtentTestManager.getTest().info("Home page loaded status: " + homeLoaded);

        if ("testuser1@gmail.com".equalsIgnoreCase(username) && "Testuser1".equals(password)) {
            Assert.assertTrue(homeLoaded, "Expected successful login, but home page did not load");
            ExtentTestManager.getTest().pass("Valid login passed");
        } else {
            Assert.assertFalse(homeLoaded, "Expected login failure, but home page loaded");
            Assert.assertTrue(loginPage.isLoginTitleDisplayed(), "Expected user to remain on login page");
            ExtentTestManager.getTest().pass("Invalid login scenario passed");
        }
        log.info("Completed login scenario: {}", scenarioname);
    }

    @DataProvider(name = "loginScenario")
    public Object[][] getLoginData() {
        ExcelReader reader = new ExcelReader("/testdata/TestData.xlsx");
        return reader.getSheetData("LoginScenario");
    }
}