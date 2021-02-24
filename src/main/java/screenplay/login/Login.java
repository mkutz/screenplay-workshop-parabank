package screenplay.login;

import org.openqa.selenium.By;
import screenplay.Actor;
import screenplay.BrowseTheWeb;
import screenplay.Task;

public class Login implements Task {

  @Override
  public void performedBy(Actor actor) {
    final var webDriver = actor.uses(BrowseTheWeb.class)
        .getWebDriver();
    final var credentials = actor.remembers(Credentials.class);

    webDriver
        .findElement(By.name("username"))
        .sendKeys(credentials.getUsername());
    webDriver
        .findElement(By.name("password"))
        .sendKeys(credentials.getPassword());
    webDriver
        .findElement(By.cssSelector(".login input.button"))
        .click();
  }

  @Override
  public String toString() {
    return "login";
  }
}