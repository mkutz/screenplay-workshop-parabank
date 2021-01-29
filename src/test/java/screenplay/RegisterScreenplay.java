package screenplay;

import org.junit.jupiter.api.Test;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static screenplay.LoggedIn.areYouLoggedIn;
import static screenplay.Registration.registration;

public class RegisterScreenplay extends BaseScreenplay {

  @Test
  public void canRegister() {
    user.performs(registration()
        .withFullName("Michael", "Kutz")
        .withAddress("155 Bovet Rd #600", "San Mateo",
            "CA", "94402")
        .withPhoneNumber("+1 650-680-1000")
        .withSsn("036-44-1383")
        .withCredentials(randomAlphabetic(12), "Sup3rS3cr3t!")
        .build());

    assertTrue(user.answers(areYouLoggedIn()));
  }
}
