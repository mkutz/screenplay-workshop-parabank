package screenplay;

import org.openqa.selenium.By;

public class Registration implements Task {

  public static Registration registration() {
    return new Registration();
  }

  @Override
  public void performedBy(Actor actor) {
    final var webDriver = actor.uses(BrowseTheWeb.class)
        .getWebDriver();

    webDriver
        .findElement(By.linkText("Register"))
        .click();

    final var fullName = actor.remembers(FullName.class);
    webDriver
        .findElement(By.id("customer.firstName"))
        .sendKeys(fullName.getFirstName());
    webDriver
        .findElement(By.id("customer.lastName"))
        .sendKeys(fullName.getLastName());

    final var address = actor.remembers(Address.class);
    webDriver
        .findElement(By.id("customer.address.street"))
        .sendKeys(address.getStreet());
    webDriver
        .findElement(By.id("customer.address.city"))
        .sendKeys(address.getCity());
    webDriver
        .findElement(By.id("customer.address.state"))
        .sendKeys(address.getState());
    webDriver
        .findElement(By.id("customer.address.zipCode"))
        .sendKeys(address.getZipCode());

    final var phoneNumber = actor.remembers(PhoneNumber.class);
    webDriver
        .findElement(By.id("customer.phoneNumber"))
        .sendKeys(phoneNumber.getPhoneNumber());

    final var ssn = actor.remembers(Ssn.class);
    webDriver
        .findElement(By.id("customer.ssn"))
        .sendKeys(ssn.getSsn());

    final var credentials = actor.remembers(Credentials.class);
    webDriver
        .findElement(By.id("customer.username"))
        .sendKeys(credentials.getUsername());
    webDriver
        .findElement(By.id("customer.password"))
        .sendKeys(credentials.getPassword());
    webDriver
        .findElement(By.id("repeatedPassword"))
        .sendKeys(credentials.getPassword());

    webDriver
        .findElement(By.cssSelector("#customerForm input.button"))
        .click();
  }
}
