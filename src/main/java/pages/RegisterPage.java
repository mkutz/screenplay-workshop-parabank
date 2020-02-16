package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends HomePage {

    private By firstNameInput = By.id("customer.firstName");
    private By lastNameInput = By.id("customer.lastName");
    private By addressStreetInput = By.id("customer.address.street");
    private By addressCityInput = By.id("customer.address.city");
    private By addressStateInput = By.id("customer.address.state");
    private By addressZipCodeInput = By.id("customer.address.zipCode");
    private By phoneNumberInput = By.id("customer.phoneNumber");
    private By ssnInput = By.id("customer.ssn");
    private By usernameInput = By.id("customer.username");
    private By passwordInput = By.id("customer.password");
    private By passwordRepeatInput = By.id("repeatedPassword");
    private By submitButton = By.cssSelector("#customerForm input.button");

    public RegisterPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void inputName(String firstName, String lastName) {
        webDriver.findElement(firstNameInput).sendKeys(firstName);
        webDriver.findElement(lastNameInput).sendKeys(lastName);
    }

    public void inputAddress(String street, String city, String state, String zipCode) {
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
