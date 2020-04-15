package screenplay;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
import static screenplay.questions.LoggedIn.loggedIn;
import static screenplay.tasks.Login.login;

public class LoginScreenplay extends BaseScreenplay {

    @Test
    public void canLogin() {
        user.perform(login());

        assertTrue(user.seesThat(loggedIn()));
    }
}