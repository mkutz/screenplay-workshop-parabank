package screenplay;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static screenplay.Credentials.defaultCredentials;
import static screenplay.LoggedIn.areYouLoggedIn;
import static screenplay.Login.login;

public class LoginScreenplay extends BaseScreenplay {

  @BeforeEach
  void setUp() {
    user.learns(defaultCredentials());
  }

  @Test
  public void canLogin() {
    user.performs(login());

    assertTrue(user.answers(areYouLoggedIn()));
  }
}
