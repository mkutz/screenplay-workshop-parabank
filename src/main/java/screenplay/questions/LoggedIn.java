package screenplay.questions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import screenplay.Actor;
import screenplay.abilities.BrowseTheWeb;

public class LoggedIn implements Question<Boolean> {

    private LoggedIn() {
    }

    public static LoggedIn loggedIn() {
        return new LoggedIn();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        WebDriver webDriver = actor.getAbility(BrowseTheWeb.class).getWebDriver();
        return webDriver.findElement(By.linkText("Log Out")).isDisplayed();
    }
}
