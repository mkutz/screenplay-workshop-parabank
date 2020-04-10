package pageobject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends Page {

    private static final By usernameInput = By.name("username");
    private static final By passwordInput = By.name("password");
    private static final By submitButton = By.cssSelector(".login input.button");

    private static final By registerLink = By.linkText("Register");

    private static final By openNewAccountLink = By.linkText("Open New Account");
    private static final By accountsOverviewLink = By.linkText("Accounts Overview");
    private static final By transferFundsLink = By.linkText("Transfer Funds");
    private static final By logoutLink = By.linkText("Log Out");


    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }


    public void login(String username, String password) {
        webDriver.findElement(usernameInput).sendKeys(username);
        webDriver.findElement(passwordInput).sendKeys(password);
        webDriver.findElement(submitButton).click();
    }

    public void logout() {
        webDriver.findElement(logoutLink).click();
    }

    public boolean isLoggedIn() {
        return webDriver.findElement(logoutLink).isDisplayed();
    }

    public RegisterPage clickRegisterLink() {
        webDriver.findElement(registerLink).click();
        return new RegisterPage(webDriver);
    }

    public OpenNewAccountPage clickOpenNewAccountLink() {
        webDriver.findElement(openNewAccountLink).click();
        return new OpenNewAccountPage(webDriver);
    }

    public AccountsOverviewPage clickAccountsOverviewLink() {
        webDriver.findElement(accountsOverviewLink).click();
        return new AccountsOverviewPage(webDriver);
    }

    public TransferFundsPage clickTransferFundsLink() {
        webDriver.findElement(transferFundsLink).click();
        return new TransferFundsPage(webDriver);
    }
}
