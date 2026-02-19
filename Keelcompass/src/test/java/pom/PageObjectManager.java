package pom;

import org.openqa.selenium.WebDriver;

import com.keelworks.LoginPage;

public class PageObjectManager {
	private LoginPage loginPage;
	private WebDriver driver;


	public PageObjectManager(WebDriver driver) {

		this.driver = driver;

	}
	public LoginPage getLoginPage() {

		if (loginPage == null) {
			loginPage = new LoginPage(driver);
		}
		return loginPage;

	}
}
