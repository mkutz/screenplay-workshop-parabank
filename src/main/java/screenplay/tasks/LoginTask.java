package screenplay.tasks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import screenplay.Actor;
import screenplay.abilities.BrowseTheWebAbility;
import screenplay.facts.Credentials;

public class LoginTask implements Task {

    private LoginTask() {
    }

    public static LoginTask login() {
        return new LoginTask();
    }

    @Override
    public void performAs(Actor actor) {
        WebDriver webDriver = actor.usesAbility(BrowseTheWebAbility.class).getWebDriver();
        Credentials credentials = actor.remembers(Credentials.class);
        webDriver.findElement(By.name("username")).sendKeys(credentials.getUsername());
        webDriver.findElement(By.name("password")).sendKeys(credentials.getPassword());
        webDriver.findElement(By.cssSelector(".login input.button")).click();
    }

    @Override
    public String toString() {
        return "Login{}";
    }
}
