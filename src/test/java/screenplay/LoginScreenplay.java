package screenplay;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
import static screenplay.questions.LoggedInQuestion.loggedIn;
import static screenplay.tasks.LoginTask.login;

public class LoginScreenplay extends BaseScreenplay {

    @Test
    public void canLogin() {
        user.performs(login());

        assertTrue(user.seesThat(loggedIn()));
    }
}