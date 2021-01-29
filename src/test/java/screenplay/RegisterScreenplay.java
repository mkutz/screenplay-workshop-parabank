package screenplay;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static screenplay.LoggedIn.areYouLoggedIn;
import static screenplay.Registration.registration;

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
    user.performs(registration());

    assertTrue(user.answers(areYouLoggedIn()));
  }
}
