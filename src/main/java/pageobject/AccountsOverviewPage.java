package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;

public class AccountsOverviewPage extends Page {

  private static final By accountRows =
      By.cssSelector("#accountTable tbody tr.ng-scope");
  private static final By accountIdCell =
      By.cssSelector("td:nth-child(1)");
  private static final By balanceCell =
      By.cssSelector("td:nth-child(2)");

  public AccountsOverviewPage(WebDriver webDriver) {
    super(webDriver);
  }

  public int getBalanceInCents(int index) {
    return dollarStringToCents(
        new WebDriverWait(webDriver, 5)
            .until(visibilityOfAllElementsLocatedBy(accountRows))
            .get(index)
            .findElement(balanceCell)
            .getText()
    );
  }

  public int getBalanceInCents(String accountId) {
    return new WebDriverWait(webDriver, 5)
        .until(visibilityOfAllElementsLocatedBy(accountRows))
        .stream()
        .filter(accountRow ->
            accountRow.findElement(accountIdCell).getText()
                .equals(accountId)
        )
        .findAny()
        .map(accountRow ->
            accountRow.findElement(balanceCell).getText()
        )
        .map(AccountsOverviewPage::dollarStringToCents)
        .orElseThrow(() -> new IllegalArgumentException(
            "Account " + accountId + " not in account overview"));
  }

  private static int dollarStringToCents(String dollarString) {
    return Integer.parseInt(
        dollarString.replaceAll("[^-\\d]", "")
    );
  }
}
