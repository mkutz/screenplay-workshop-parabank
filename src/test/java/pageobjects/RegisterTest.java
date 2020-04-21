package pageobjects;

import org.testng.annotations.Test;
import pageobject.pages.RegisterPage;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.testng.Assert.assertTrue;

public class RegisterTest extends BaseTest {

    @Test
    public void canRegister() {
        RegisterPage registerPage = homePage.clickRegisterLink();
        registerPage.inputName("Michael", "Kutz");
        registerPage.inputAddress("155 Bovet Rd #600", "San Mateo", "CA", "94402");
        registerPage.inputPhoneNumber("+1 650-680-1000");
        registerPage.inputSsn("036-44-1383");
        registerPage.inputCredentials(randomAlphabetic(12), "Sup3rS3cr3t!");
        registerPage.submit();

        assertTrue(homePage.isLoggedIn());
    }
}
