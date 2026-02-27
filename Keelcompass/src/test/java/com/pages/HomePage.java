package com.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	private WebDriver driver;
	private WebDriverWait wait;

	@FindBy(xpath = "//h1[contains(text(),'KCompass')]")
	private WebElement kcompass_Header;

	@FindBy(xpath = "//span[contains(text(),'Home')]")
	private WebElement home_Button;

	@FindBy(xpath = "//h2[contains(text(),'Posts')]")
	private WebElement posts_Heading;

	@FindBy(xpath = "//button[contains(text(),'Most Recent')]")
	private WebElement most_Recent_Button;

	@FindBy(xpath = "//button[contains(text(),'Popular')]")
	private WebElement Popular_Button;


	public boolean isHomePageTitleDisplayed() {
		try {
			return wait.until(ExpectedConditions.visibilityOf(kcompass_Header)).isDisplayed();
		} catch (TimeoutException e) {
			return false;
		}
	}


}
