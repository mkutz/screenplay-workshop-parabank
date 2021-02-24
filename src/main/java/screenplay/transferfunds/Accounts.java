package screenplay.transferfunds;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import screenplay.Actor;
import screenplay.BrowseTheWeb;
import screenplay.Question;

import java.util.List;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toList;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;

public class Accounts
    implements Question<List<AccountStatus>> {

  @Override
  public List<AccountStatus> answeredBy(Actor actor) {
    final var webDriver = actor.uses(BrowseTheWeb.class)
        .getWebDriver();

    webDriver.findElement(By.linkText("Accounts Overview"))
        .click();

    final var accountRows = By.cssSelector("#accountTable tbody tr.ng-scope");
    final var accountIdCell = By.cssSelector("td:nth-child(1)");
    final var balanceCell = By.cssSelector("td:nth-child(2)");
    return new WebDriverWait(webDriver, 5)
        .until(visibilityOfAllElementsLocatedBy(accountRows))
        .stream()
        .map(row -> new AccountStatus(
                row.findElement(accountIdCell).getText(),
                parseInt(row.findElement(balanceCell)
                    .getText()
                    .replaceAll("[^-\\d]", ""))
            )
        )
        .collect(toList());
  }
}
