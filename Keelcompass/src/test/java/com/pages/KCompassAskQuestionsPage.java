package com.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ExtentTestManager;

public class KCompassAskQuestionsPage {

	private WebDriver driver;
	private WebDriverWait wait;

	public KCompassAskQuestionsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@FindBy(xpath = "//button[@aria-label='Toggle sidebar']")
	private WebElement toggleSidebar;

	@FindBy(xpath = "//div[@aria-hidden='false']")
	private WebElement expandedSidebar;

	@FindBy(xpath = "//img[@alt='Logo']")
	private WebElement logo;

	@FindBy(xpath = "//button[text()='Ask Question']")
	private WebElement Ask_Question;

	@FindBy(xpath = "//label[contains(.,'Question') and contains(.,'required')]")
	private WebElement askQuestion_Label;

	@FindBy(xpath = "//p[text()='Begin with who, what, where, when, why, or how.']")
	private WebElement questionHelperText;

	@FindBy(xpath = "//h1[text()='Ask Question']")
	private WebElement Ask_Question_Title_Text;

	@FindBy(xpath = "//input[contains(@id,'questionTitle')]")
	private WebElement Enter_Question_Tab;

	@FindBy(xpath = "//label[contains(.,'Description')]")
	private WebElement descriptionLabel;

	@FindBy(xpath = "//p[contains(.,'Provide more details and context to help others answer.')]")
	private WebElement descriptionHelperText;

	@FindBy(xpath = "//*[@id='descriptionEditable']")
	private WebElement descriptionEditable;

	@FindBy(xpath = "//button[@title='Attach file']")
	private WebElement attachFile;

	@FindBy(xpath = "//button[@title='Remove attachment']")
	private WebElement removeAttachment;

	@FindBy(xpath = "//*[@title='Add emoji']")
	private WebElement addEmojiBtn;

	@FindBy(xpath = "//*[@alt='yum']")
	private WebElement emojiYum;

	@FindBy(xpath = "//*[@title='Formatting']")
	private WebElement formattingBtn;

	@FindBy(xpath = "(//div[@class='group/tooltip relative flex items-center justify-center h-full']/button[@type='button' and contains(@class,'relative px-3 py-3 rounded transition')])[5]")
	private WebElement formattingMenu;

	@FindBy(xpath = "//label[text()='Category']")
	private WebElement categoryLabel;

	@FindBy(xpath = "//p[contains(.,'Select one or more categories to get your question better visibility.')]")
	private WebElement categoryHelperText;

	@FindBy(xpath = "//div[@class='relative inline-block']/button[@type='button']")
	private WebElement selectCategoriesDropdown;

	@FindBy(xpath = "(//input[@type='checkbox'])[1]")
	private WebElement selectEducation;

	@FindBy(xpath = "(//input[@type='checkbox'])[2]")
	private WebElement selectPd_Management;

	@FindBy(xpath = "(//input[@type='checkbox'])[3]")
	private WebElement selectPerformance;

	@FindBy(xpath = "//img[@alt='Home']")
	private WebElement homeIcon;
	
	@FindBy(xpath = "//span[contains(text(),'Home')]")
	private WebElement homeText;
	
	@FindBy(xpath = "//h1[contains(text(),'KCompass')]")
	private WebElement kCompassHeader;
	
	@FindBy(xpath = "//p[contains(text(),'Knowledge Base')]")
	private WebElement knowledgeBaseHeader;

	@FindBy(xpath = "//button[text()='Post']")
	private WebElement postButton;

	@FindBy(xpath = "//button[text()='Discard']")
	private WebElement discardButton;

	@FindBy(xpath = "(//button[text()='Discard'])[1]")
	private WebElement discardConfirmationButtonPopup;

	@FindBy(xpath = "//button[text()='Cancel']")
	private WebElement discardCancelButtonPopup;
	
	@FindBy(xpath = "//img[@alt='Profile']")
	private WebElement profileIcon;
	
	@FindBy(xpath = "//button[text()='Logout']")
	private WebElement logoutButton;

	public void toggleSidebarValidation() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(toggleSidebar));
		wait.until(ExpectedConditions.visibilityOf(kCompassHeader));
		ExtentTestManager.getTest().info("KCompass header is visible");
		wait.until(ExpectedConditions.visibilityOf(knowledgeBaseHeader));
		ExtentTestManager.getTest().info("Knowledge Base header is visible");
		toggleSidebar.click();
		Actions actions = new Actions(driver);
		actions.moveToElement(toggleSidebar).build().perform();
		toggleSidebar.click();
	}

	public void homeIconValidation() {
		wait.until(ExpectedConditions.visibilityOf(homeIcon));
		if (homeIcon.isDisplayed()) {
			ExtentTestManager.getTest().info("Home icon is displayed");
			wait.until(ExpectedConditions.elementToBeClickable(homeIcon));
			homeIcon.click();

			wait.until(ExpectedConditions.visibilityOf(homeText));
			ExtentTestManager.getTest().info("Navigated back to Home page successfully");
		} else {
			ExtentTestManager.getTest().info("Home icon is NOT displayed");
		}
	}

	public void logoValidation() {
		wait.until(ExpectedConditions.visibilityOf(logo));
		if (logo.isDisplayed()) {
			ExtentTestManager.getTest().info("Logo is displayed");
		} else {
			ExtentTestManager.getTest().info("Logo is NOT displayed");
		}
	}

	public void askQuestions() {

		wait.until(ExpectedConditions.elementToBeClickable(Ask_Question));
		Ask_Question.click();

	}

	public boolean isAskQuestionTitleDisplayed() {
		try {
			return wait.until(ExpectedConditions.visibilityOf(Ask_Question_Title_Text)).isDisplayed();

		} catch (TimeoutException e) {
			return false;
		}
	}

	public void askQuestionLabelValidation() {
		wait.until(ExpectedConditions.visibilityOf(askQuestion_Label));
		String questionRequiredLabel = askQuestion_Label.getText();
		ExtentTestManager.getTest().info("verify Question label text: " + questionRequiredLabel);

		wait.until(ExpectedConditions.visibilityOf(questionHelperText));
		String questionHelperTextLabel = questionHelperText.getText();
		ExtentTestManager.getTest().info("verify Question Helper text requirements: " + questionHelperTextLabel);

	}

	public void decreptionLabelValidation() {

		wait.until(ExpectedConditions.visibilityOf(descriptionLabel));
		String descriptionLabelText = descriptionLabel.getText();
		ExtentTestManager.getTest().info("verify Description label text: " + descriptionLabelText);

		wait.until(ExpectedConditions.visibilityOf(descriptionHelperText));
		String descriptionLabelHelperText = descriptionHelperText.getText();
		ExtentTestManager.getTest().info("verify Description label text: " + descriptionLabelHelperText);

	}

	// Action: Enter Question
	public void enterQuestion(String text) {
		Enter_Question_Tab.sendKeys(text);
	}

	// Get entered text
	public String getEnteredQuestion() {
		return Enter_Question_Tab.getAttribute("value");
	}

	// Get maxlength attribute
	public String getMaxLength() {
		String maxLength = null;
		try {
			wait.until(ExpectedConditions.visibilityOf(Enter_Question_Tab));
			maxLength = Enter_Question_Tab.getAttribute("maxlength");
		} catch (TimeoutException e) {
			e.getMessage();
		}

		return maxLength;
	}

	// Enter text into the descriptionEditable. Enforce 250 char limit locally.
	public void enterDescription(String text) {
		String safe = text == null ? "" : text;
		if (safe.length() > 250)
			safe = safe.substring(0, 250);
		wait.until(ExpectedConditions.visibilityOf(descriptionEditable));
		descriptionEditable.clear();
		descriptionEditable.sendKeys(safe);
	}

	public String getDescriptionText() {
		wait.until(ExpectedConditions.visibilityOf(descriptionEditable));
		// Try value attribute then textContent
		String v = descriptionEditable.getAttribute("value");
		if (v != null && !v.isEmpty())
			return v;
		String txt = descriptionEditable.getText();
		if (txt != null)
			return txt;
		String inner = descriptionEditable.getAttribute("textContent");
		return inner == null ? "" : inner;
	}

	public String getMaxLengthAttr() {
		wait.until(ExpectedConditions.visibilityOf(descriptionEditable));
		return descriptionEditable.getAttribute("maxlength");
	}

	public boolean hasMaxLength250() {
		String m = getMaxLengthAttr();
		if (m == null)
			return false;
		try {
			return Integer.parseInt(m) == 250;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	// Attach a file - will try to click attach button then send path to file input
	// if present
	public void attachFile() throws AWTException {

		Robot robot = new Robot();
		try {

			wait.until(ExpectedConditions.elementToBeClickable(attachFile));
			attachFile.click();

			// Copy file path to clipboard
			StringSelection selection = new StringSelection("C:\\Users\\user\\KeelCompassProject\\cat.jpg");
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

			robot.delay(6000);
			// Paste (Ctrl + V)
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);

			// Press Enter
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

//			if(selection != null) {
//				ExtentTestManager.getTest().info("File path copied to clipboard and pasted successfully");
//				wait.until(ExpectedConditions.elementToBeClickable(removeAttachment));
//				removeAttachment.click();
//			} else {
//				ExtentTestManager.getTest().info("Failed to copy file path to clipboard");
//			}

		} catch (Exception e) {
			// ignore - maybe clicking not required
			throw new RuntimeException("No file input found to attach file");
		}

	}

	// Emoji and formatting actions - these open widgets in UI;
	public void openEmojiPicker() {
		wait.until(ExpectedConditions.elementToBeClickable(addEmojiBtn));
		addEmojiBtn.click();
		wait.until(ExpectedConditions.elementToBeClickable(emojiYum));
		emojiYum.click();
	}

	public void openFormattingMenu() {
		wait.until(ExpectedConditions.elementToBeClickable(formattingBtn));
		formattingBtn.click();

		List<WebElement> buttons = wait.until(ExpectedConditions.visibilityOfAllElements(formattingMenu));
		for (int i = 0; i < buttons.size(); i++) {
			if (buttons.get(i).equals(formattingMenu)) {

				buttons.get(i).click();
			}
		}
	}

	public void categoryLabelValidation() {
		wait.until(ExpectedConditions.visibilityOf(categoryLabel));
		String categoryLabelText = categoryLabel.getText();
		ExtentTestManager.getTest().info("verify Category label text: " + categoryLabelText);

		wait.until(ExpectedConditions.visibilityOf(categoryHelperText));
		String categoryHelperLabelText = categoryHelperText.getText();
		ExtentTestManager.getTest().info("verify Category label text: " + categoryHelperLabelText);
	}

	public void openDropdown() {
		wait.until(ExpectedConditions.elementToBeClickable(selectCategoriesDropdown));
		selectCategoriesDropdown.click();

		// Values to select
		List<String> valuesToSelect = Arrays.asList("selectPd_Management", "selectPerformance", "selectEducation");
		WebElement checkbox = null;
		for (String value : valuesToSelect) {

			if (value.equals("selectPd_Management")) {
				checkbox = wait.until(ExpectedConditions.elementToBeClickable(selectPd_Management));
			} else if (value.equals("selectPerformance")) {
				checkbox = wait.until(ExpectedConditions.elementToBeClickable(selectPerformance));
			} else if (value.equals("selectEducation")) {
				checkbox = wait.until(ExpectedConditions.elementToBeClickable(selectEducation));
			}

			if (checkbox != null && !checkbox.isSelected()) {
				checkbox.click();
			}
		}
	}

	public void postQuestion() {
		wait.until(ExpectedConditions.elementToBeClickable(postButton));
		postButton.click();
	}

	public void discardQuestion() throws InterruptedException {

		// Get the handle of the parent window
		String parentWindowHandle = driver.getWindowHandle();

		// Perform the action to open the new window
		wait.until(ExpectedConditions.elementToBeClickable(discardButton));
		//Thread.sleep(2000);
		discardButton.click();

		// Use an explicit wait for the new window to open
		wait.until(ExpectedConditions.numberOfWindowsToBe(1));

		// Get all window handles
		Set<String> allWindowHandles = driver.getWindowHandles();

		// Iterate through handles and switch to the child window
		for (String handle : allWindowHandles) {
			if (!handle.equals(parentWindowHandle)) {
				driver.switchTo().window(handle);
				break; // Exit the loop once the child window is found
			}
		}
		// Locate and click the 'Discard' button in the new window
		try {
//			wait.until(ExpectedConditions.elementToBeClickable(discardConfirmationButtonPopup));
//			discardConfirmationButtonPopup.click();
//			ExtentTestManager.getTest().info("Clicked 'Discard' button in the popup.");
			 wait.until(ExpectedConditions.elementToBeClickable(discardCancelButtonPopup));
			 discardCancelButtonPopup.click();
			 ExtentTestManager.getTest().info("Clicked 'Cancel' button in the popup.");
		} catch (Exception e) {
			System.out.println("Could not find or click 'Discard' button in the popup.");
		}
	}
	
	public void profileIconValidationAndLogout() {
		wait.until(ExpectedConditions.visibilityOf(profileIcon));
		if (profileIcon.isDisplayed()) {
			ExtentTestManager.getTest().info("Profile icon is displayed");
			 wait.until(ExpectedConditions.elementToBeClickable(profileIcon));
			 profileIcon.click();
			 ExtentTestManager.getTest().info("Clicked on Profile icon.");
			 wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
			 logoutButton.click();
		} else {
			ExtentTestManager.getTest().info("Profile icon is NOT displayed");
		}
	}
	
	public void browserRefresh() {
		driver.navigate().refresh();
	}
	
}
