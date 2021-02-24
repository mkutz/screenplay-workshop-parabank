package screenplay.openaccount;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import screenplay.Actor;
import screenplay.BrowseTheWeb;
import screenplay.Question;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;

public class NewAccountBalance implements Question<Integer> {

  private static final By accountRows =
      By.cssSelector("#accountTable tbody tr.ng-scope");
  private static final By accountIdCell =
      By.cssSelector("td:nth-child(1)");
  private static final By balanceCell =
      By.cssSelector("td:nth-child(2)");

  @Override
  public Integer answeredBy(Actor actor) {
    final var webDriver = actor.uses(BrowseTheWeb.class).getWebDriver();
    final var accountId = actor.remembers(NewAccountId.class)
        .getAccountId();

    webDriver
        .findElement(By.linkText("Accounts Overview"))
        .click();

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
        .map(dollarString -> Integer.parseInt(
            dollarString.replaceAll("[^-\\d]", "")
        ))
        .orElseThrow(() -> new IllegalArgumentException(
            "Account " + accountId + " not in account overview"));
  }

}
