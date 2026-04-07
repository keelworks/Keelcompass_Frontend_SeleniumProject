package testcases;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.pages.HomePage;
import com.pages.LoginPage;

import base.BaseTest;
import utils.ConfigReader;

@Listeners(utils.ExtentTestListener.class)
public class HomePageTest extends BaseTest {

    private static final Logger log = LogManager.getLogger(HomePageTest.class);

    private LoginPage loginPage;
    private HomePage homepage;

    private String email = ConfigReader.getProperty("username");
    private String password = ConfigReader.getProperty("password");

    @BeforeMethod
    public void setup() {
        loginPage = new LoginPage(getDriver());
        homepage = new HomePage(getDriver());

        loginPage.login(email, password);
    }

    @Test
    public void verifyLogoIsDisplayed() {
        Assert.assertTrue(homepage.isLogoDisplayed(), "Logo is not displayed on home page");
    }

    @Test
    public void verifyMostRecentLabelIsDisplayed() {
        Assert.assertTrue(homepage.isMostRecentLabelDisplayed(), "Most Recent label is not displayed");
    }

    @Test
    public void verifyPopularLabelIsDisplayed() {
        Assert.assertTrue(homepage.isPopularLabelDisplayed(), "Popular label is not displayed");
    }

    @Test
    public void verifyAskQuestionLabelDisplayed() {
        homepage.clickAskQuestionButton();
        Assert.assertTrue(homepage.isAskQuestionLabelDisplayed(), "Ask Question label is not displayed after clicking button");
    }

    @Test
    public void verifyDefaultCategoryValue() {
        String actualDefaultCategory = homepage.getSelectedCategory();
        Assert.assertEquals(actualDefaultCategory, "All", "Default category is not 'All'");
    }

    @Test
   public void verifyCategoryPanelOpensAndSelectionWorks() throws InterruptedException {
        // Step 1: Click the dropdown
    	Thread.sleep(5000);
    	homepage.refreshBrowser();
        homepage.clickCategoryDropdown();
    	Thread.sleep(5000);

        // Validation 1: Panel opens
        Assert.assertTrue(homepage.isCategoryPanelDisplayed(), "Category panel did not open");

        // Validation 2: Expected options present
        List<String> expectedCategories = List.of("No Category", "Career Development", "Job Search", "Education");
        Assert.assertTrue(homepage.verifyCategoryOptionsPresent(expectedCategories),
                "Expected category options are missing");
        
        // Step 2: Select a specific category
        homepage.selectCategory("Job Search");

        // Validation 4: Check if selection worked
        String selectedCategory = homepage.getSelectedCategory();
        Assert.assertEquals(selectedCategory, "Job Search", "Selected category did not update correctly");
    }
    
   
    
}