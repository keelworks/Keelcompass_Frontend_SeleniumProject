package com.pages;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    // ---------- Page Elements ----------

    @FindBy(xpath = "//h3[contains(text(),'KCompass')]")
    private WebElement kcompassHeader;

    @FindBy(xpath = "//h3[normalize-space()='KCompass']")
    private WebElement logo;

    @FindBy(xpath = "//button[contains(text(),'Most Recent')]")
    private WebElement mostRecentButton;

    @FindBy(xpath = "//button[contains(text(),'Popular')]")
    private WebElement popularButton;

    @FindBy(xpath = "//button[contains(.,'Ask Question')]")
    private WebElement askQuestionButton;

    @FindBy(xpath = "//h1[normalize-space()='Ask Question'] | //button[contains(.,'Ask Question')]")
    private WebElement askQuestionLabel;

    @FindBy(xpath = "//button[.//span[text()='All']]")
    private WebElement categoryDropdown;

    private By applyButtonLocator = By.xpath("//button[contains(.,'Apply')]");
    
    @FindBy(xpath = "//button[contains(.,'Clear all')]")
    private WebElement clearAllLocator;

    // ---------- Visibility Actions ----------

    public boolean isElementVisible(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLogoDisplayed() { return isElementVisible(logo); }
    public boolean isHomePageTitleDisplayed() { return isElementVisible(kcompassHeader); }
    public boolean isPopularLabelDisplayed() { return isElementVisible(popularButton); }
    public boolean isMostRecentLabelDisplayed() { return isElementVisible(mostRecentButton); }
    public boolean isAskQuestionLabelDisplayed() { return isElementVisible(askQuestionLabel); }

    // ---------- Functional Actions ----------

    public void clickAskQuestionButton() {
        wait.until(ExpectedConditions.elementToBeClickable(askQuestionButton)).click();
    }

    

    public List<WebElement> getCategoryOptions() {
        // Targets the labels inside the dropdown list
        return driver.findElements(By.xpath("//div[contains(@class,'absolute')]//label"))
                .stream()
                .filter(WebElement::isDisplayed)
                .collect(Collectors.toList());
    }

    public List<String> getCategoryOptionsText() {
        List<String> texts = getCategoryOptions().stream()
                .map(WebElement::getText)
                .map(String::trim)
                .filter(t -> !t.equalsIgnoreCase("Apply") && !t.equalsIgnoreCase("Clear all") && !t.isEmpty())
                .distinct()
                .collect(Collectors.toList());

        System.out.println("DEBUG: Categories Found: " + texts);
        return texts;
    }

    public void selectCategory(String categoryName) {
        clickCategoryDropdown();
        // Dynamic locator to find the checkbox by the text provided in the parameter
        String xpath = String.format("//label[contains(.,'%s')]/preceding-sibling::input[@type='checkbox']", categoryName);
        WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
        
        // Clicks apply to close the dropdown
        driver.findElement(applyButtonLocator).click();
    }
    
    public String getSelectedCategory() {
        return categoryDropdown.getText().trim();
    }

    public boolean isCategoryPanelDisplayed() {
        try {
            // Check if the Apply button or list is visible
            return driver.findElement(applyButtonLocator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyCategoryOptionsPresent(List<String> expectedOptions) {
        List<String> actual = getCategoryOptionsText();
        return actual.containsAll(expectedOptions);
    }
    
    public void refreshBrowser() {
        driver.navigate().refresh();
    }




    // The "All" button that triggers the dropdown
    @FindBy(xpath = "//button[@type='button'][contains(., 'All')]")
    private WebElement categoryDropdownBtn;

    // The menu that appears after clicking (using the logic from your error log)
    @FindBy(xpath = "//div[contains(@class,'absolute')]//ul | //div[@role='menu']")
    private WebElement categoryMenu;

   

    public void clickCategoryDropdown() {
        // 1. Wait for the button to be clickable and click it
        wait.until(ExpectedConditions.elementToBeClickable(categoryDropdownBtn)).click();
        
        // 2. Wait for the menu to actually appear in the DOM/Visibility
        wait.until(ExpectedConditions.visibilityOf(categoryMenu));
    }
}