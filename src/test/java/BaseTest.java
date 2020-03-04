import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;

import static org.testng.Assert.assertTrue;

abstract public class BaseTest {

    WebDriver webDriver;
    String baseUrl = "http://parabank.parasoft.com/";
    HomePage homePage;
    String testUsername = "screenplayer";
    String testPassword = "Sup3rS3cr3t!";

    @BeforeClass
    public void setUpWebDriver() {
        WebDriverManager.chromiumdriver().setup();
        webDriver = new ChromeDriver();
    }

    @BeforeMethod
    public void goHome() {
        webDriver.manage().deleteAllCookies();
        webDriver.get(baseUrl);
        homePage = new HomePage(webDriver);
        assertTrue(homePage.isAt());
    }

    @AfterClass
    public void tearDownWebDriver() {
        webDriver.quit();
    }
}
