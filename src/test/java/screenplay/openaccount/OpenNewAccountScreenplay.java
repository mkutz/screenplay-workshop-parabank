package screenplay.openaccount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import screenplay.BaseScreenplay;
import screenplay.login.Login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static screenplay.login.Credentials.defaultCredentials;

public class OpenNewAccountScreenplay extends BaseScreenplay {

  static int INITIAL_ACCOUNT_BALANCE = 10000;

  @BeforeEach
  public void ensureLogin() {
    user.learns(defaultCredentials())
        .does(new Login());
  }

  @Test
  public void canOpenNewAccount() {
    user.does(new OpenNewAccount());

    assertEquals(
        user.checks(new NewAccountBalance()),
        INITIAL_ACCOUNT_BALANCE
    );
  }
}
