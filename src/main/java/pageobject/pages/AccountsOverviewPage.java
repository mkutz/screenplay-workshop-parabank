package pageobject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;

public class AccountsOverviewPage extends Page {

    private static final By balanceCell = By.cssSelector("td:nth-child(2)");
    private static final By accountRows = By.cssSelector("#accountTable tbody tr.ng-scope");
    private static final By mainAccountRow = By.cssSelector("#accountTable tbody tr.ng-scope:nth-child(1)");
    private static final By accountIdCell = By.cssSelector("td:nth-child(1)");

    public AccountsOverviewPage(WebDriver webDriver) {
        super(webDriver);
        new WebDriverWait(webDriver, 5)
                .until(visibilityOfAllElementsLocatedBy(accountRows));
    }

    public List<String> getAccountIdsList() {
        return webDriver.findElements(accountRows).stream()
                .map(accountRow -> accountRow.findElement(accountIdCell).getText())
                .collect(toList());
    }

    public int getAccountBalanceInCents(String accountId) {
        return webDriver.findElements(accountRows).stream()
                .filter(accountRow -> accountId.equals(accountRow.findElement(accountIdCell).getText()))
                .findAny()
                .map(accountRow -> accountRow.findElement(balanceCell).getText())
                .map(AccountsOverviewPage::dollarStringToCents)
                .get();
    }

    public int getAccountBalanceInCents() {
        String balanceText = webDriver.findElement(mainAccountRow)
                .findElement(By.cssSelector("td:nth-child(2)"))
                .getText();
        return dollarStringToCents(balanceText);
    }

    private static int dollarStringToCents(String dollarString) {
        return Integer.parseInt(dollarString.replaceAll("[^\\d]", ""));
    }
}
