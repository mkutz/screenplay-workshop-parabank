import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    public void canLogin() {
        homePage.login(testUsername, testPassword);

        assertTrue(homePage.isLoggedIn());
    }
}
