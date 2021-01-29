package screenplay;

import org.openqa.selenium.By;

public class Registration implements Task {

  private final String firstName;
  private final String lastName;
  private final String street;
  private final String city;
  private final String state;
  private final String zipCode;
  private final String phoneNumber;
  private final String ssn;
  private final String username;
  private final String password;

  private Registration(
      String firstName, String lastName,
      String street, String city,
      String state, String zipCode,
      String phoneNumber, String ssn,
      String username, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.street = street;
    this.city = city;
    this.state = state;
    this.zipCode = zipCode;
    this.phoneNumber = phoneNumber;
    this.ssn = ssn;
    this.username = username;
    this.password = password;
  }

  public static Builder registration() {
    return new Builder();
  }

  @Override
  public void performedBy(Actor actor) {
    final var webDriver = actor.uses(BrowseTheWeb.class)
        .getWebDriver();

    webDriver
        .findElement(By.linkText("Register"))
        .click();

    webDriver
        .findElement(By.id("customer.firstName"))
        .sendKeys(firstName);
    webDriver
        .findElement(By.id("customer.lastName"))
        .sendKeys(lastName);
    webDriver
        .findElement(By.id("customer.address.street"))
        .sendKeys(street);
    webDriver
        .findElement(By.id("customer.address.city"))
        .sendKeys(city);
    webDriver
        .findElement(By.id("customer.address.state"))
        .sendKeys(state);
    webDriver
        .findElement(By.id("customer.address.zipCode"))
        .sendKeys(zipCode);
    webDriver
        .findElement(By.id("customer.phoneNumber"))
        .sendKeys(phoneNumber);
    webDriver
        .findElement(By.id("customer.ssn"))
        .sendKeys(ssn);
    webDriver
        .findElement(By.id("customer.username"))
        .sendKeys(username);
    webDriver
        .findElement(By.id("customer.password"))
        .sendKeys(password);
    webDriver
        .findElement(By.id("repeatedPassword"))
        .sendKeys(password);
    webDriver
        .findElement(By.cssSelector("#customerForm input.button"))
        .click();
  }

  public static final class Builder {
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String phoneNumber;
    private String ssn;
    private String username;
    private String password;

    public static Builder aRegistration() {
      return new Builder();
    }

    public Builder withFullName(
        String firstName, String lastName) {
      this.firstName = firstName;
      this.lastName = lastName;
      return this;
    }

    public Builder withAddress(
        String street, String city,
        String state, String zipCode) {
      this.street = street;
      this.city = city;
      this.state = state;
      this.zipCode = zipCode;
      return this;
    }

    public Builder withPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
      return this;
    }

    public Builder withSsn(String ssn) {
      this.ssn = ssn;
      return this;
    }

    public Builder withCredentials(
        String username, String password) {
      this.username = username;
      this.password = password;
      return this;
    }

    public Registration build() {
      return new Registration(firstName, lastName, street, city,
          state, zipCode, phoneNumber, ssn, username, password);
    }
  }
}
