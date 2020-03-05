package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends Page {

    private static By usernameInput = By.name("username");
    private static By passwordInput = By.name("password");
    private static By submitButton = By.cssSelector(".login input.button");

    private static By registerLink = By.linkText("Register");

    private static By openNewAccountLink = By.linkText("Open New Account");
    private static By accountsOverviewLink = By.linkText("Accounts Overview");
    private static By transferFundsLink = By.linkText("Transfer Funds");
    private static By billPayLink = By.linkText("Bill Pay");
    private static By findTransactionsLink = By.linkText("Find Transactions Link");
    private static By updateContactInfoLink = By.linkText("Update Contract Info");
    private static By requestLoanLink = By.linkText("Request Loan");
    private static By logoutLink = By.linkText("Log Out");


    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }


    public boolean isAt() {
        return webDriver.getTitle().equals("ParaBank | Welcome | Online Banking");
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
}
