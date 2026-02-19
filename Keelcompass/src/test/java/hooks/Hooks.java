package hooks;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import context.TestContext;
import factory.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import pom.PageObjectManager;
import utils.LoggerFactory;

public class Hooks {

	PageObjectManager pom;
	private TestContext context;

	public Hooks(TestContext context) {
		this.context = context;
	}

	@Before(order = 1)
	public void setup() throws IOException {

		context.setDriver(DriverManager.getDriver());
		context.setPom(new PageObjectManager(context.getDriver()));

	}

	@Before(order = 0)
	public void Setup() throws IOException {

		DriverManager.initBrowser();
		pom = new PageObjectManager(DriverManager.getDriver());
		//pom.getLaunchPage().launchApplication();

	}
	public PageObjectManager getPom() {
		return pom;
	}

	@After
	public void tearDown(Scenario scenario) {
		if (DriverManager.getDriver() != null) {
			DriverManager.getDriver().quit();
		}
		LoggerFactory.getLogger().info("DONE tearDown()..");
	}

	@AfterStep
	public void takeScreenShot(Scenario scenario) {
		if (scenario.isFailed()) {
			TakesScreenshot takesScreenshot = (TakesScreenshot) DriverManager
					.getDriver();
			byte[] screenShot = takesScreenshot
					.getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenShot, "image/png", scenario.getName());
			Allure.addAttachment(scenario.getName(),
					new ByteArrayInputStream(screenShot));
		}
	}
}