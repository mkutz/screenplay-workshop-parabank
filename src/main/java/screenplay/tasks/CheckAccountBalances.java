package screenplay.tasks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import screenplay.Actor;
import screenplay.abilities.BrowseTheWeb;
import screenplay.facts.AccountBalance;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;
import static screenplay.facts.AccountBalances.accountBalances;

public class CheckAccountBalances implements Task {

    private CheckAccountBalances() {
    }

    public static CheckAccountBalances checkAccountBalances() {
        return new CheckAccountBalances();
    }

    @Override
    public void performAs(Actor actor) {
        WebDriver webDriver = actor.usesAbility(BrowseTheWeb.class).getWebDriver();

        if (!webDriver.getCurrentUrl().endsWith("parabank/overview.htm")) {
            webDriver.findElement(By.linkText("Accounts Overview")).click();
        }

        List<WebElement> accountRows = new WebDriverWait(webDriver, 5)
                .until(visibilityOfAllElementsLocatedBy(By.cssSelector("#accountTable tbody tr.ng-scope")));

        actor.knows(accountBalances(accountRows.stream()
                .map(accountRow -> new AccountBalance(getAccountId(accountRow), getAccountBalance(accountRow)))
                .collect(toList())
        ));
    }

    private static String getAccountId(WebElement accountRow) {
        return accountRow.findElement(By.cssSelector("td:nth-child(1)")).getText();
    }

    private static Integer getAccountBalance(WebElement accountRow) {
        return dollarStringToCents(accountRow.findElement(By.cssSelector("td:nth-child(2)")).getText());
    }

    private static int dollarStringToCents(String dollarString) {
        return Integer.parseInt(dollarString.replaceAll("[^-\\d]", ""));
    }

    @Override
    public String toString() {
        return "CheckAccountBalances{}";
    }
}
