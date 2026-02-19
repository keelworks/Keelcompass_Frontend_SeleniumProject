package stepdefinitions;

import com.keelworks.LoginPage;

import context.TestContext;

public class loginStepDefinition {
	
	private TestContext context = new TestContext();
	private LoginPage loginPage;

	// ✅ Pico injects this
	public void LoginStepDefinitions(TestContext context) {
		this.context = context;
		this.loginPage = context.getPom().getLoginPage();
	}

}
