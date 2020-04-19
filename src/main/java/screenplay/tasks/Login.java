package screenplay.tasks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import screenplay.Actor;
import screenplay.abilities.BrowseTheWeb;
import screenplay.facts.Credentials;

public class Login implements Task {

    private Login() {
    }

    public static Login login() {
        return new Login();
    }

    @Override
    public void performAs(Actor actor) {
        WebDriver webDriver = actor.getAbility(BrowseTheWeb.class).getWebDriver();
        Credentials credentials = actor.getFact(Credentials.class);
        webDriver.findElement(By.name("username")).sendKeys(credentials.getUsername());
        webDriver.findElement(By.name("password")).sendKeys(credentials.getPassword());
        webDriver.findElement(By.cssSelector(".login input.button")).click();
    }

    @Override
    public String toString() {
        return "Login{}";
    }
}
