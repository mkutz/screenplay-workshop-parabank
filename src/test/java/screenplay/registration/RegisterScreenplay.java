package screenplay.registration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import screenplay.BaseScreenplay;
import screenplay.login.Credentials;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static screenplay.login.LoggedIn.areYouLoggedIn;
import static screenplay.registration.Registration.registration;

public class RegisterScreenplay extends BaseScreenplay {

  @BeforeEach
  void setUp() {
    user.learns(new FullName("John", "Demo"))
        .learns(new Address("155 Bovet Rd #600", "San Mateo",
            "CA", "94402"))
        .learns(new PhoneNumber("+1 650-680-1000"))
        .learns(new Ssn("036-44-1383"))
        .learns(Credentials.uniqueCredentials());
  }

  @Test
  void canRegister() {
    user.does(registration());

    assertTrue(user.checks(areYouLoggedIn()));
  }
}
