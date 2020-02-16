import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.RegisterPage;

import static java.util.UUID.randomUUID;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class RegisterTest extends BaseTest {

    @Test
    public void canRegister() {
        RegisterPage registerPage = homePage.clickRegisterLink();
        registerPage.inputName("Michael", "Kutz");
        registerPage.inputAddress("155 Bovet Rd #600", "San Mateo", "CA", "94402");
        registerPage.inputPhoneNumber("+1 650-680-1000");
        registerPage.inputSsn("036-44-1383");
        registerPage.inputCredentials(randomUUID().toString().substring(0,11), "Sup3rS3cr3t!");
        registerPage.submit();

        assertTrue(homePage.isLoggedIn());
    }

    @Test
    public void canLogin() {
        String username = randomUUID().toString().substring(0,11);
        String password = "Sup3rS3cr3t!";

        RegisterPage registerPage = homePage.clickRegisterLink();
        registerPage.inputName("Michael", "Kutz");
        registerPage.inputAddress("155 Bovet Rd #600", "San Mateo", "CA", "94402");
        registerPage.inputPhoneNumber("+1 650-680-1000");
        registerPage.inputSsn("036-44-1383");
        registerPage.inputCredentials(username, password);
        registerPage.submit();
        homePage.logout();

        homePage.login(username, password);

        assertTrue(homePage.isLoggedIn());
    }
}
