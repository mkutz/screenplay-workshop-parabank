package pageobject.pages;

import org.openqa.selenium.WebDriver;

public abstract class Page {

    protected final WebDriver webDriver;

    public Page(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
}
