package screenplay;

import org.junit.jupiter.api.Test;

import static screenplay.Login.loginWith;

public class LoginScreenplay extends BaseScreenplay {

  @Test
  public void canLogin() {
    user.performs(loginWith(testUsername, testPassword));
  }
}
