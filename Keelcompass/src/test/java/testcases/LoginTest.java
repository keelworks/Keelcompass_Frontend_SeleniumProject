package testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import com.pages.HomePage;
import com.pages.LoginPage;
import utils.ConfigReader;
import utils.ExtentTestManager;

@Listeners(utils.ExtentTestListener.class)
public class LoginTest extends BaseTest {

    private static final Logger log = LogManager.getLogger(LoginTest.class);

    @Test
    public void loginWithValidCredentials() {

        WebDriver driver = getDriver();
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        String email = ConfigReader.getProperty("username");
        String password = ConfigReader.getProperty("password");

        
        ExtentTestManager.getTest().info("Navigated to login page. URL: " + driver.getCurrentUrl());
        ExtentTestManager.getTest().info("Entering credentials and clicking Login");

     
        log.info("Logging in with username from config (masked).");
        Assert.assertTrue(loginPage.isLoginTitleDisplayed(), "Login Page is displayed");
        loginPage.login(email, password);

        ExtentTestManager.getTest().info("Login submitted. Validating Home page");

        boolean loaded = homePage.isHomePageTitleDisplayed();
        ExtentTestManager.getTest().info("Home page loaded status: " + loaded);

        Assert.assertTrue(loaded, "Home Page is not properly loaded after login");
        ExtentTestManager.getTest().pass("Home page loaded successfully");
    }
}