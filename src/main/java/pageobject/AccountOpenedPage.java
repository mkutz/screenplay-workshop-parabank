package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class AccountOpenedPage extends Page {

  private static final By newAccountLink = By.id("newAccountId");


  public AccountOpenedPage(WebDriver webDriver) {
    super(webDriver);
    new WebDriverWait(webDriver, 3)
        .ignoring(NoSuchElementException.class)
        .until(elementToBeClickable(newAccountLink));
  }


  public AccountDetailsPage goToNewAccount() {
    webDriver.findElement(newAccountLink).click();
    return new AccountDetailsPage(webDriver);
  }

  public String getNewAccountId() {
    return webDriver.findElement(newAccountLink).getText();
  }
}
