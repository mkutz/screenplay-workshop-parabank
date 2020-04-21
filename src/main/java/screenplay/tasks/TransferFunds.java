package screenplay.tasks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import screenplay.Actor;
import screenplay.abilities.BrowseTheWeb;

import static org.openqa.selenium.support.ui.ExpectedConditions.attributeContains;
import static org.openqa.selenium.support.ui.ExpectedConditions.not;

public class TransferFunds implements Task {

    private final String fromAccountId;
    private final String toAccountId;
    private final String amount;

    private TransferFunds(String fromAccountId, String toAccountId, String amount) {
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
    }

    public static TransferFunds transferFunds(String fromAccountId, String toAccountId, String amount) {
        return new TransferFunds(fromAccountId, toAccountId, amount);
    }

    public static TransferFunds transferFunds(String fromAccountId, String toAccountId, int amount) {
        return new TransferFunds(fromAccountId, toAccountId, Integer.toString(amount / 100));
    }

    @Override
    public void performAs(Actor actor) {
        WebDriver webDriver = actor.usesAbility(BrowseTheWeb.class).getWebDriver();

        webDriver.findElement(By.linkText("Transfer Funds")).click();

        new WebDriverWait(webDriver, 3)
                .until(not(attributeContains(By.id("fromAccountId"), "class", "ng-empty")));

        webDriver.findElement(By.id("amount")).sendKeys(amount);
        new Select(webDriver.findElement(By.id("fromAccountId"))).selectByValue(fromAccountId);
        new Select(webDriver.findElement(By.id("toAccountId"))).selectByValue(toAccountId);
        webDriver.findElement(By.cssSelector("input[type=submit]")).click();
    }

    @Override
    public String toString() {
        return "TransferFunds{" +
                "fromAccountId='" + fromAccountId + '\'' +
                ", toAccountId='" + toAccountId + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
