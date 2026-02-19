package context;



import org.openqa.selenium.WebDriver;
import pom.PageObjectManager;

public class TestContext {

    private WebDriver driver;
    private PageObjectManager pom;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public PageObjectManager getPom() {
        return pom;
    }

    public void setPom(PageObjectManager pom) {
        this.pom = pom;
    }
}
