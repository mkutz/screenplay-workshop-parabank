package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends Page {

  private static final By firstNameInput =
      By.id("customer.firstName");
  private static final By lastNameInput =
      By.id("customer.lastName");
  private static final By addressStreetInput =
      By.id("customer.address.street");
  private static final By addressCityInput =
      By.id("customer.address.city");
  private static final By addressStateInput =
      By.id("customer.address.state");
  private static final By addressZipCodeInput =
      By.id("customer.address.zipCode");
  private static final By phoneNumberInput =
      By.id("customer.phoneNumber");
  private static final By ssnInput =
      By.id("customer.ssn");
  private static final By usernameInput =
      By.id("customer.username");
  private static final By passwordInput =
      By.id("customer.password");
  private static final By passwordRepeatInput =
      By.id("repeatedPassword");
  private static final By submitButton =
      By.cssSelector("#customerForm input.button");

  public RegisterPage(WebDriver webDriver) {
    super(webDriver);
  }

  public void inputName(String firstName, String lastName) {
    webDriver.findElement(firstNameInput).sendKeys(firstName);
    webDriver.findElement(lastNameInput).sendKeys(lastName);
  }

  public void inputAddress(String street, String city,
                           String state, String zipCode) {
    webDriver.findElement(addressStreetInput).sendKeys(street);
    webDriver.findElement(addressCityInput).sendKeys(city);
    webDriver.findElement(addressStateInput).sendKeys(state);
    webDriver.findElement(addressZipCodeInput).sendKeys(zipCode);
  }

  public void inputPhoneNumber(String phoneNumber) {
    webDriver.findElement(phoneNumberInput).sendKeys(phoneNumber);
  }

  public void inputSsn(String ssn) {
    webDriver.findElement(ssnInput).sendKeys(ssn);
  }

  public void inputCredentials(String username, String password) {
    webDriver.findElement(usernameInput).sendKeys(username);
    webDriver.findElement(passwordInput).sendKeys(password);
    webDriver.findElement(passwordRepeatInput).sendKeys(password);
  }

  public void submit() {
    webDriver.findElement(submitButton).click();
  }
}
