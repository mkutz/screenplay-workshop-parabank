package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class AccountsOverviewPage extends Page {

    By accountRows = By.cssSelector("#accountTable tbody tr");

    public AccountsOverviewPage(WebDriver webDriver) {
        super(webDriver);
    }

    public List<String> getAccountIdsList() {
        return webDriver.findElements(accountRows).stream()
                .map(accountRow -> accountRow.findElement(By.tagName("td")).getText())
                .collect(toList());
    }
}
