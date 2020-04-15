package pageobjects;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pageobject.pages.HomePage;

abstract public class BaseTest {

    private WebDriver webDriver;
    protected HomePage homePage;
    protected String testUsername = "john";
    protected String testPassword = "demo";

    @BeforeClass
    public void setUpWebDriver() {
        WebDriverManager.chromiumdriver().setup();
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
