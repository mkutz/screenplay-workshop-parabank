package screenplay;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static screenplay.BrowseTheWeb.browseTheWebWith;
import static screenplay.Credentials.defaultCredentials;

abstract public class BaseScreenplay {

  private static WebDriver webDriver;
  protected Actor user;

  @BeforeAll
  static void setUpWebDriver() {
    WebDriverManager.chromiumdriver().setup();
    webDriver = new ChromeDriver();
  }

  @BeforeEach
  public void reset() {
    webDriver.manage().deleteAllCookies();
    webDriver.get("http://parabank.parasoft.com/");
    user = new Actor("John")
        .can(browseTheWebWith(webDriver))
        .learns(defaultCredentials());
  }

  @AfterAll
  static void tearDownWebDriver() {
    webDriver.quit();
  }
}
