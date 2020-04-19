package pageobject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.attributeContains;
import static org.openqa.selenium.support.ui.ExpectedConditions.not;

public class TransferFundsPage extends Page {

    private static final By amountInput = By.id("amount");
    private static final By fromAccountIdSelect = By.id("fromAccountId");
    private static final By toAccountIdSelect = By.id("toAccountId");
    private static final By transferButton = By.cssSelector("input[type=submit]");


    public TransferFundsPage(WebDriver webDriver) {
        super(webDriver);
        new WebDriverWait(webDriver, 3)
                .until(not(attributeContains(fromAccountIdSelect, "class", "ng-empty")));
    }


    public void transfer(int amountInCents, String fromAccountId, String toAccountId) {
        webDriver.findElement(amountInput).sendKeys(Integer.toString(amountInCents / 100));
        if (null != fromAccountId) {
            new Select(webDriver.findElement(fromAccountIdSelect)).selectByValue(fromAccountId);
        }
        new Select(webDriver.findElement(toAccountIdSelect)).selectByValue(toAccountId);
        webDriver.findElement(transferButton).click();
    }
}
