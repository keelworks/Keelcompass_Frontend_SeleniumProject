package factory;



	import java.time.Duration;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.edge.EdgeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;

	import utils.ConfigReader;
	import utils.LoggerFactory;

	public class DriverManager {
		private static final ThreadLocal<WebDriver> testdriver = new ThreadLocal<>();
		private static final ThreadLocal<String> driverbrowser = new ThreadLocal<>();

		private static WebDriver driver;

		public static void initBrowser() {

			String browserType = null;
			BrowserOptions browserOptions = new BrowserOptions();

			browserType = driverbrowser.get();

			if (browserType == null) {

				browserType = ConfigReader.getProperty("browser");
				System.out.println("its null");
			}

			switch (browserType) {
				case "chrome" :
					testdriver.set(new ChromeDriver(browserOptions.chromeOption()));
					break;
				case "edge" :
					testdriver.set(new EdgeDriver(browserOptions.edgeOption()));
					break;
				case "firefox" :
					testdriver
							.set(new FirefoxDriver(browserOptions.firefoxOption()));
					break;
				default :
					LoggerFactory.getLogger()
							.error("Unexpected value for browser: {}", browserType);
					throw new IllegalStateException(
							"Unexpected value for browserType: " + browserType);
			}
			driver = testdriver.get();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
			driver.manage().window().maximize();

		}

		public static WebDriver getDriver() {
			return testdriver.get();
		}

		public static void setBrowser(String browser) {
			driverbrowser.set(browser);
		}

	}


