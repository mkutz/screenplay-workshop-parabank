package screenplay.tasks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import screenplay.Actor;
import screenplay.abilities.BrowseTheWeb;
import screenplay.facts.*;

public class Register implements Task {

    public static Register register() {
        return new Register();
    }

    private Register() {
    }

    @Override
    public void performAs(Actor actor) {
        WebDriver webDriver = actor.getAbility(BrowseTheWeb.class).getWebDriver();

        webDriver.findElement(By.linkText("Register")).click();

        FullName fullName = actor.getFact(FullName.class);
        webDriver.findElement(By.id("customer.firstName")).sendKeys(fullName.getFirstName());
        webDriver.findElement(By.id("customer.lastName")).sendKeys(fullName.getLastName());

        Address address = actor.getFact(Address.class);
        webDriver.findElement(By.id("customer.address.street")).sendKeys(address.getStreet());
        webDriver.findElement(By.id("customer.address.city")).sendKeys(address.getCity());
        webDriver.findElement(By.id("customer.address.state")).sendKeys(address.getState());
        webDriver.findElement(By.id("customer.address.zipCode")).sendKeys(address.getZipCode());

        PhoneNumber phoneNumber = actor.getFact(PhoneNumber.class);
        webDriver.findElement(By.id("customer.phoneNumber")).sendKeys(phoneNumber.getPhoneNumber());

        Ssn ssn = actor.getFact(Ssn.class);
        webDriver.findElement(By.id("customer.ssn")).sendKeys(ssn.getSsn());

        Credentials credentials = actor.getFact(Credentials.class);
        webDriver.findElement(By.id("customer.username")).sendKeys(credentials.getUsername());
        webDriver.findElement(By.id("customer.password")).sendKeys(credentials.getPassword());
        webDriver.findElement(By.id("repeatedPassword")).sendKeys(credentials.getPassword());

        webDriver.findElement(By.cssSelector("#customerForm input.button")).click();
    }

    @Override
    public String toString() {
        return "Register{}";
    }
}
