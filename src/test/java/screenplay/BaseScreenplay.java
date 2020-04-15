package screenplay;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static screenplay.abilities.BrowseTheWeb.browseTheWebWith;
import static screenplay.facts.Credentials.defaultCredentials;

public abstract class BaseScreenplay {

    private WebDriver webDriver;
    protected Actor user;

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
