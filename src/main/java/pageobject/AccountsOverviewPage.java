package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;

public class AccountsOverviewPage extends Page {

    private static final By accountRows = By.cssSelector("#accountTable tbody tr.ng-scope");
    private static final By accountIdCell = By.cssSelector("td:nth-child(1)");
    private static final By balanceCell = By.cssSelector("td:nth-child(2)");

    public AccountsOverviewPage(WebDriver webDriver) {
        super(webDriver);
    }

    public int getAccountBalanceInCents(int index) {
        return dollarStringToCents(
                new WebDriverWait(webDriver, 5)
                        .until(visibilityOfAllElementsLocatedBy(accountRows))
                        .get(index)
                        .findElement(balanceCell)
                        .getText()
        );
    }

    public int getAccountBalanceInCents(String accountId) {
        return new WebDriverWait(webDriver, 5)
                .until(visibilityOfAllElementsLocatedBy(accountRows))
                .stream()
                .filter(accountRow -> accountId.equals(accountRow.findElement(accountIdCell).getText()))
                .findAny()
                .map(accountRow -> accountRow.findElement(balanceCell).getText())
                .map(AccountsOverviewPage::dollarStringToCents)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Account %s not in account overview", accountId)));
    }

    private static int dollarStringToCents(String dollarString) {
        return Integer.parseInt(dollarString.replaceAll("[^-\\d]", ""));
    }
}
