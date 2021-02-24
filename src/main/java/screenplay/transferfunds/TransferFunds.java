package screenplay.transferfunds;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import screenplay.Actor;
import screenplay.BrowseTheWeb;
import screenplay.Task;

import static org.openqa.selenium.support.ui.ExpectedConditions.attributeContains;
import static org.openqa.selenium.support.ui.ExpectedConditions.not;

public class TransferFunds implements Task {

  private final String from;
  private final String to;
  private final int amountInCents;

  public TransferFunds(String from, String to, int amountInCents) {
    this.from = from;
    this.to = to;
    this.amountInCents = amountInCents;
  }

  @Override
  public void performedBy(Actor actor) {
    final var webDriver = actor.uses(BrowseTheWeb.class)
        .getWebDriver();

    final var fromAccountIdSelect = By.id("fromAccountId");

    webDriver.findElement(By.linkText("Transfer Funds")).click();
    new WebDriverWait(webDriver, 3)
        .until(not(attributeContains(fromAccountIdSelect, "class", "ng-empty")));

    webDriver.findElement(By.id("amount"))
        .sendKeys(Integer.toString(amountInCents / 100));
    new Select(webDriver.findElement(fromAccountIdSelect))
        .selectByVisibleText(from);
    new Select(webDriver.findElement(By.id("toAccountId")))
        .selectByVisibleText(to);

    webDriver.findElement(By.cssSelector("input[type=submit]"))
        .click();
  }
}
