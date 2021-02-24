package screenplay.openaccount;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import screenplay.Actor;
import screenplay.BrowseTheWeb;
import screenplay.Task;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class OpenNewAccount implements Task {

  @Override
  public void performedBy(Actor actor) {
    final var webDriver = actor.uses(BrowseTheWeb.class).getWebDriver();

    webDriver
        .findElement(By.linkText("Open New Account"))
        .click();

    final var fromAccountIdSelect = By.id("fromAccountId");
    new WebDriverWait(webDriver, 3)
        .until(not(attributeContains(fromAccountIdSelect,
            "class", "ng-empty")));
    new Select(webDriver.findElement(By.id("type")))
        .selectByIndex(0);
    new Select(webDriver.findElement(fromAccountIdSelect))
        .selectByIndex(0);
    webDriver
        .findElement(By.cssSelector("input.button"))
        .click();

    final var newAccountLink = new WebDriverWait(webDriver, 3)
        .ignoring(NoSuchElementException.class)
        .until(elementToBeClickable(By.id("newAccountId")));

    actor.learns(new NewAccountId(newAccountLink.getText()));

    newAccountLink.click();
  }
}
