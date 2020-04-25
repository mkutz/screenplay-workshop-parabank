package screenplay.tasks;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import screenplay.Actor;
import screenplay.abilities.BrowseTheWebAbility;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static screenplay.facts.NewAccountId.newAccountId;

public class OpenNewAccountTask implements Task {

    private static final By fromAccountIdSelect = By.id("fromAccountId");
    private static final By newAccountLink = By.id("newAccountId");

    private OpenNewAccountTask() {
    }

    public static OpenNewAccountTask openNewAccount() {
        return new OpenNewAccountTask();
    }

    @Override
    public void performAs(Actor actor) {
        WebDriver webDriver = actor.usesAbility(BrowseTheWebAbility.class).getWebDriver();

        webDriver.findElement(By.linkText("Open New Account")).click();
        new WebDriverWait(webDriver, 3)
                .until(not(attributeContains(fromAccountIdSelect, "class", "ng-empty")));

        new Select(webDriver.findElement(By.id("type"))).selectByIndex(0);
        new Select(webDriver.findElement(fromAccountIdSelect)).selectByIndex(0);
        webDriver.findElement(By.cssSelector("input.button")).click();

        new WebDriverWait(webDriver, 3)
                .ignoring(NoSuchElementException.class)
                .until(elementToBeClickable(newAccountLink));

        actor.knows(newAccountId(webDriver.findElement(newAccountLink).getText()));
    }

    @Override
    public String toString() {
        return "OpenNewAccount{}";
    }
}
