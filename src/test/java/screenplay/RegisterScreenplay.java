package screenplay;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
import static screenplay.facts.Address.defaultAddress;
import static screenplay.facts.Credentials.randomCredentials;
import static screenplay.facts.FullName.defaultFullName;
import static screenplay.facts.PhoneNumber.defaultPhoneNumber;
import static screenplay.facts.Ssn.defaultSsn;
import static screenplay.questions.LoggedIn.loggedIn;
import static screenplay.tasks.Register.register;

public class RegisterScreenplay extends BaseScreenplay {

    @BeforeMethod
    public void addFacts() {
        user.knows(randomCredentials())
                .knows(defaultFullName())
                .knows(defaultAddress())
                .knows(defaultSsn())
                .knows(defaultPhoneNumber());
    }

    @Test
    public void canRegister() {
        user.perform(register());

        assertTrue(user.seesThat(loggedIn()));
    }
}
