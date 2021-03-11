package pageobjects;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject.HomePage;

abstract public class BaseTest {

  private static WebDriver webDriver;
  protected HomePage homePage;
  protected String testUsername = "john";
  protected String testPassword = "demo";

  @BeforeAll
  static void setUpWebDriver() {
    WebDriverManager.chromiumdriver().setup();
    webDriver = new ChromeDriver();
  }

  @BeforeEach
  public void reset() {
    webDriver.manage().deleteAllCookies();
    webDriver.get("http://parabank.parasoft.com/");
    homePage = new HomePage(webDriver);
  }

  @AfterAll
  static void tearDownWebDriver() {
    webDriver.quit();
  }
}
