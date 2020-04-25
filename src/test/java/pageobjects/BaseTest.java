package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pageobject.HomePage;

import static io.github.bonigarcia.wdm.WebDriverManager.chromiumdriver;

abstract public class BaseTest {

    private WebDriver webDriver;
    protected HomePage homePage;
    protected String testUsername = "john";
    protected String testPassword = "demo";

    @BeforeSuite
    public void setUpWebDriverBinary() {
        chromiumdriver().setup();
    }

    @BeforeClass
    public void setUpWebDriver() {
        webDriver = new ChromeDriver();
    }

    @BeforeMethod
    public void reset() {
        webDriver.manage().deleteAllCookies();
        webDriver.get("http://parabank.parasoft.com/");
    }

    @BeforeMethod
    public void setUpHomePage() {
        homePage = new HomePage(webDriver);
    }

    @AfterClass
    public void tearDownWebDriver() {
        webDriver.quit();
    }
}
