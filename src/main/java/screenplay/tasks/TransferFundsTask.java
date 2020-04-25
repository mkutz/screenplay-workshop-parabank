package screenplay.tasks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import screenplay.Actor;
import screenplay.abilities.BrowseTheWebAbility;

import static org.openqa.selenium.support.ui.ExpectedConditions.attributeContains;
import static org.openqa.selenium.support.ui.ExpectedConditions.not;

public class TransferFundsTask implements Task {

    private final int fromAccountIndex;
    private final int toAccountIndex;
    private final String amount;

    private TransferFundsTask(int fromAccountIndex, int toAccountIndex, String amount) {
        this.fromAccountIndex = fromAccountIndex;
        this.toAccountIndex = toAccountIndex;
        this.amount = amount;
    }

    public static TransferFundsTask transferFunds(int fromAccountIndex, int toAccountIndex, String amount) {
        return new TransferFundsTask(fromAccountIndex, toAccountIndex, amount);
    }

    public static TransferFundsTask transferFunds(int fromAccountIndex, int toAccountIndex, int amount) {
        return new TransferFundsTask(fromAccountIndex, toAccountIndex, Integer.toString(amount / 100));
    }

    @Override
    public void performAs(Actor actor) {
        WebDriver webDriver = actor.usesAbility(BrowseTheWebAbility.class).getWebDriver();

        webDriver.findElement(By.linkText("Transfer Funds")).click();

        new WebDriverWait(webDriver, 3)
                .until(not(attributeContains(By.id("fromAccountId"), "class", "ng-empty")));

        webDriver.findElement(By.id("amount")).sendKeys(amount);
        new Select(webDriver.findElement(By.id("fromAccountId"))).selectByIndex(fromAccountIndex);
        new Select(webDriver.findElement(By.id("toAccountId"))).selectByIndex(toAccountIndex);
        webDriver.findElement(By.cssSelector("input[type=submit]")).click();
    }

    @Override
    public String toString() {
        return "TransferFundsTask{" +
                "fromAccountIndex=" + fromAccountIndex +
                ", toAccountIndex=" + toAccountIndex +
                ", amount='" + amount + '\'' +
                '}';
    }
}
