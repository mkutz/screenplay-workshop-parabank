package screenplay.questions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import screenplay.Actor;
import screenplay.abilities.BrowseTheWeb;
import screenplay.facts.NewAccountId;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;

public class NewAccountBalance implements Question<Integer> {

    private NewAccountBalance() {
    }


    public static NewAccountBalance newAccountBalance() {
        return new NewAccountBalance();
    }

    @Override
    public Integer answeredBy(Actor actor) {
        WebDriver webDriver = actor.usesAbility(BrowseTheWeb.class).getWebDriver();

        String newAccountId = actor.remembers(NewAccountId.class).getNewAccountId();

        webDriver.findElement(By.linkText("Accounts Overview")).click();
        new WebDriverWait(webDriver, 5)
                .until(visibilityOfAllElementsLocatedBy(By.cssSelector("#accountTable tbody tr.ng-scope")));


        return webDriver.findElements(By.cssSelector("#accountTable tbody tr.ng-scope")).stream()
                .filter(accountRow -> newAccountId.equals(accountRow.findElement(By.cssSelector("td:nth-child(1)")).getText()))
                .findAny()
                .map(accountRow -> accountRow.findElement(By.cssSelector("td:nth-child(2)")).getText())
                .map(NewAccountBalance::dollarStringToCents)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Account %s not in account overview", newAccountId)));
    }

    private static int dollarStringToCents(String dollarString) {
        return Integer.parseInt(dollarString.replaceAll("[^\\d]", ""));
    }
}