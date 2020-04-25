package screenplay;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import static io.github.bonigarcia.wdm.WebDriverManager.chromiumdriver;
import static screenplay.abilities.BrowseTheWebAbility.browseTheWebWith;
import static screenplay.facts.Credentials.defaultCredentials;

public abstract class BaseScreenplay {

    private WebDriver webDriver;
    protected Actor user;

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
    public void setupActor() {
        user = new Actor("Micha")
                .can(browseTheWebWith(webDriver))
                .knows(defaultCredentials());
    }

    @AfterClass
    public void tearDownWebDriver() {
        webDriver.quit();
    }
}
