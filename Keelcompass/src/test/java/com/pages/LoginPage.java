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

public class LoginPage {

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	private WebDriver driver;
	private WebDriverWait wait;

	@FindBy(xpath = "//input[@name='email']")
	private WebElement email_Input_Field;

	@FindBy(xpath = "//input[@name='password']")
	private WebElement password_Input_Field;

	@FindBy(xpath = "//h2[contains(text(),'Login')]")
	private WebElement login_Title;

	@FindBy(xpath = "//button[contains(text(),'Login')]")
	private WebElement login_Button;

	@FindBy(xpath = "//button[contains(text(),'Sign Up')]")
	private WebElement Signup_Button;

	@FindBy(xpath = "//a[contains(text(),'Forgot Password?')]")
	private WebElement forgot_password_link;

	public boolean isLoginTitleDisplayed() {
		try {
			return wait.until(ExpectedConditions.visibilityOf(login_Title)).isDisplayed();
		} catch (TimeoutException e) {
			return false;
		}
	}

	public void login(String email, String password) {
		wait.until(ExpectedConditions.elementToBeClickable(email_Input_Field));
		email_Input_Field.clear();
		email_Input_Field.sendKeys(email);

		wait.until(ExpectedConditions.elementToBeClickable(password_Input_Field));
		password_Input_Field.clear();
		password_Input_Field.sendKeys(password);

		wait.until(ExpectedConditions.elementToBeClickable(login_Button));
		login_Button.click();
		
	}

}
