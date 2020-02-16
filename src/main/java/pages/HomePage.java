package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends Page {

    private By usernameInput = By.name("username");
    private By passwordInput = By.name("password");
    private By submitButton = By.cssSelector(".login input.button");

    private By registerLink = By.linkText("Register");

    private By logoutLink = By.linkText("Log Out");

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
}
