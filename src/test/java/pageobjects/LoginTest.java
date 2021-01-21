package pageobjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest {

  @Test
  public void canLogin() {
    homePage.login(testUsername, testPassword);

    assertTrue(homePage.isLoggedIn());
  }
}
