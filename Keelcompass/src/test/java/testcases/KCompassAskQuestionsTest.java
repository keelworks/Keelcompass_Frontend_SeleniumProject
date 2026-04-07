package testcases;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.pages.KCompassAskQuestionsPage;


import base.BaseTest;
import utils.ExtentTestManager;

@Listeners(utils.ExtentTestListener.class)
public class KCompassAskQuestionsTest extends BaseTest {

	//private static final Logger log = LogManager.getLogger(KCompassAskQuestionsTest.class);

	@Test
	public void AskQuestionText() throws InterruptedException {

		WebDriver driver = getDriver();
		LoginTest loginTest = new LoginTest();
		loginTest.loginWithValidCredentials();

		String text = "Need help in understanding the KeelCompass website ,explain with an good examples?";

		KCompassAskQuestionsPage kCompassPage = new KCompassAskQuestionsPage(driver);

		kCompassPage.askQuestions();
		ExtentTestManager.getTest().info("Landed on Q&A Page");
		kCompassPage.logoValidation();
		ExtentTestManager.getTest().info("clicked the Toggle sidebar button");
    
		Assert.assertTrue(kCompassPage.isAskQuestionTitleDisplayed(), "Ask Question");
		kCompassPage.toggleSidebarValidation();

		kCompassPage.askQuestionLabelValidation();

		kCompassPage.decreptionLabelValidation();

		ExtentTestManager.getTest().info("Enter the Question. ");

		int actualTextLength = text.length();

		ExtentTestManager.getTest().info("Entered original text with more then 80 charecters " + actualTextLength);

		// Enter question
		kCompassPage.enterQuestion(text);

		// Get actual entered text
		String enteredText = kCompassPage.getEnteredQuestion();

		int actualLength = enteredText.length();

		ExtentTestManager.getTest().info("Taken Charecters. " + actualLength);

		// Validate max length attribute
		Assert.assertEquals(kCompassPage.getMaxLength(), "80");

		// Validate characters allowed
		Assert.assertTrue(actualLength <= 80, "Field allows more than 80 characters");

		ExtentTestManager.getTest().info("Actual Taken Characters Entered: " + actualLength);

		StringBuilder sb = new StringBuilder();
		// for (int i = 0; i < 300; i++)
		sb.append(
				"Please explain the KeelCompass website with good examples? I am having trouble understanding how to navigate and use the features effectively. Any guidance would be greatly appreciated. Thank you!");
		String longText = sb.toString();

		kCompassPage.enterDescription(longText);
		String entered = kCompassPage.getDescriptionText();
		ExtentTestManager.getTest().info("Entered description text: " + entered);

		try {

			kCompassPage.attachFile();
			Assert.assertTrue(true);
		} catch (Exception e) {
			Assert.fail("Attach file failed: " + e.getMessage());
		}

		kCompassPage.openEmojiPicker();
		kCompassPage.openFormattingMenu();
		kCompassPage.categoryLabelValidation();
		kCompassPage.openDropdown();
		// kCompassPage.postQuestion();
		kCompassPage.discardQuestion();
		//kCompassPage.homeIconValidation();
		kCompassPage.profileIconValidationAndLogout();;
	}
}
