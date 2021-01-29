package screenplay;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static screenplay.LoggedIn.areYouLoggedIn;
import static screenplay.Login.login;

public class LoginScreenplay extends BaseScreenplay {

  @Test
  public void canLogin() {
    user.performs(login());

    assertTrue(user.answers(areYouLoggedIn()));
  }
}
