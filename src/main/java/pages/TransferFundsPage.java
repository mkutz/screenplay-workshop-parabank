package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.attributeContains;
import static org.openqa.selenium.support.ui.ExpectedConditions.not;

public class TransferFundsPage extends Page {

    private final By amountInput = By.id("amount");
    private final By fromAccountIdSelect = By.id("fromAccountId");
    private final By toAccountIdSelect = By.id("toAccountId");
    private final By transferButton = By.cssSelector("input[type=submit]");


    public TransferFundsPage(WebDriver webDriver) {
        super(webDriver);
        new WebDriverWait(webDriver, 3)
                .until(not(attributeContains(fromAccountIdSelect, "class", "ng-empty")));
    }


    public void transfer(String amount, String fromAccountId, String toAccountId) {
        webDriver.findElement(amountInput).sendKeys(amount);
        if (null != fromAccountId) {
            new Select(webDriver.findElement(fromAccountIdSelect)).selectByValue(fromAccountId);
        }
        new Select(webDriver.findElement(toAccountIdSelect)).selectByValue(toAccountId);
        webDriver.findElement(transferButton).click();
    }

    public void transfer(String amount, String toAccountId) {
        transfer(amount, null, toAccountId);
    }
}
