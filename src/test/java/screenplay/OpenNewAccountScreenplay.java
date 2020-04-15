package screenplay;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screenplay.tasks.Login;

import static org.testng.Assert.assertEquals;
import static screenplay.questions.NewAccountBalance.newAccountBalance;
import static screenplay.tasks.Login.login;
import static screenplay.tasks.OpenNewAccount.openNewAccount;

public class OpenNewAccountScreenplay extends BaseScreenplay {

    @BeforeMethod
    public void ensureLogin() {
        user.perform(login());
    }

    @Test
    public void canOpenNewAccount() {
        user.perform(openNewAccount());

        assertEquals(user.seesThat(newAccountBalance()).intValue(), 10000);
    }
}
