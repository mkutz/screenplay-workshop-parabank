package screenplay;

import org.openqa.selenium.WebDriver;

public class BrowseTheWeb implements Ability {

  private final WebDriver webDriver;

  public BrowseTheWeb(WebDriver webDriver) {
    this.webDriver = webDriver;
  }

  public WebDriver getWebDriver() {
    return webDriver;
  }

  @Override
  public String toString() {
    return "browse the web with " +
        webDriver.getClass().getSimpleName();
  }
}
