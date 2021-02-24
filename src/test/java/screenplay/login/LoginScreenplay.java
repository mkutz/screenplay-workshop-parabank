package screenplay.login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import screenplay.BaseScreenplay;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static screenplay.login.Credentials.defaultCredentials;
import static screenplay.login.LoggedIn.areYouLoggedIn;

public class LoginScreenplay extends BaseScreenplay {

  @BeforeEach
  void setUp() {
    user.learns(defaultCredentials());
  }

  @Test
  public void canLogin() {
    user.does(new Login());

    assertTrue(user.checks(areYouLoggedIn()));
  }
}
