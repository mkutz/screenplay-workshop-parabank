package screenplay;

import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class BrowseTheWeb implements Ability {

  private final WebDriver webDriver;

  public BrowseTheWeb(WebDriver webDriver) {
    this.webDriver = webDriver;
  }

  static BrowseTheWeb browseTheWebWith(WebDriver webDriver) {
    return new BrowseTheWeb(webDriver);
  }

  public WebDriver getWebDriver() {
    return webDriver;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BrowseTheWeb that = (BrowseTheWeb) o;
    return Objects.equals(webDriver, that.webDriver);
  }

  @Override
  public int hashCode() {
    return Objects.hash(webDriver);
  }

  @Override
  public String toString() {
    return "browse the web with " + webDriver.getClass().getSimpleName();
  }
}
