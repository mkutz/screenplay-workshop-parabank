package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;

public class AccountsOverviewPage extends Page {

    By accountRows = By.cssSelector("#accountTable tbody tr.ng-scope");

    public AccountsOverviewPage(WebDriver webDriver) {
        super(webDriver);
        new WebDriverWait(webDriver, 5)
                .until(visibilityOfAllElementsLocatedBy(accountRows));
    }

    public List<String> getAccountIdsList() {
        return webDriver.findElements(accountRows).stream()
                .map(accountRow -> accountRow.findElement(By.tagName("td")).getText())
                .collect(toList());
    }
}
