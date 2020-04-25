package screenplay.questions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import screenplay.Actor;
import screenplay.abilities.BrowseTheWebAbility;

public class LoggedInQuestion implements Question<Boolean> {

    private LoggedInQuestion() {
    }

    public static LoggedInQuestion loggedIn() {
        return new LoggedInQuestion();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        WebDriver webDriver = actor.usesAbility(BrowseTheWebAbility.class).getWebDriver();
        return webDriver.findElement(By.linkText("Log Out")).isDisplayed();
    }

    @Override
    public String toString() {
        return "LoggedInQuestion{}";
    }
}
