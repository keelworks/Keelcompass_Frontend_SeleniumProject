
package testRunner;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import factory.DriverManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", glue = {"hooks",
		"stepDefinitions"},
		// tags = "@Run",
		plugin = {"pretty", "html:cucumber-reports.html",
				"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}, dryRun = false, monochrome = true)
public class Runner extends AbstractTestNGCucumberTests {
	// @Override
	// @DataProvider(parallel = true)
	// public Object[][] scenarios() {
	// return super.scenarios();
	// }

	@BeforeClass(alwaysRun = true)
	@Parameters("browserType")
	public void beforeClass(@Optional("chrome") String browserType) {
		if (browserType != null && !browserType.equals("param-val-not-found")) {
			System.out.println(browserType);
			DriverManager.setBrowser(browserType);
		}
	}
}