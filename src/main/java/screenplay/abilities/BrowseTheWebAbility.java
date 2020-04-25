package screenplay.abilities;

import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class BrowseTheWebAbility implements Ability {

    private final WebDriver webDriver;

    private BrowseTheWebAbility(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public static BrowseTheWebAbility browseTheWebWith(WebDriver webDriver) {
        return new BrowseTheWebAbility(webDriver);
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrowseTheWebAbility that = (BrowseTheWebAbility) o;
        return Objects.equals(webDriver, that.webDriver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(webDriver);
    }

    @Override
    public String toString() {
        return "BrowseTheWeb{" +
                "webDriver=" + webDriver +
                '}';
    }
}
