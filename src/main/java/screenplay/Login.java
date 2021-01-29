package screenplay;

import org.openqa.selenium.By;

import java.util.Objects;

public class Login implements Task {

  private final String username;
  private final String password;

  public Login(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public static Login loginWith(String username, String password) {
    return new Login(username, password);
  }

  @Override
  public void performedBy(Actor actor) {
    final var webDriver = actor.uses(BrowseTheWeb.class)
        .getWebDriver();

    webDriver
        .findElement(By.name("username"))
        .sendKeys(username);
    webDriver
        .findElement(By.name("password"))
        .sendKeys(password);
    webDriver
        .findElement(By.cssSelector(".login input.button"))
        .click();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Login login = (Login) o;
    return Objects.equals(username, login.username) && Objects.equals(password, login.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, password);
  }

  @Override
  public String toString() {
    return "login as " + username;
  }
}