package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.attributeContains;
import static org.openqa.selenium.support.ui.ExpectedConditions.not;

public class OpenNewAccountPage extends Page {

    private static final By accountTypeSelect = By.id("type");
    private static final By fromAccountIdSelect = By.id("fromAccountId");
    private static final By openNewAccountButton = By.cssSelector("input.button");


    public OpenNewAccountPage(WebDriver webDriver) {
        super(webDriver);
        new WebDriverWait(webDriver, 3)
                .until(not(attributeContains(fromAccountIdSelect, "class", "ng-empty")));
    }


    public void selectAccountType(int index) {
        new Select(webDriver.findElement(accountTypeSelect)).selectByIndex(index);
    }

    public void selectFromAccountId(int index) {
        new Select(webDriver.findElement(fromAccountIdSelect)).selectByIndex(index);
    }

    public AccountOpenedPage openAccount() {
        selectAccountType(0);
        selectFromAccountId(0);
        webDriver.findElement(openNewAccountButton).click();
        return new AccountOpenedPage(webDriver);
    }
}
