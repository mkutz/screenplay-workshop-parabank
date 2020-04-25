package screenplay;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static screenplay.questions.NewAccountBalanceQuestion.newAccountBalance;
import static screenplay.tasks.LoginTask.login;
import static screenplay.tasks.OpenNewAccountTask.openNewAccount;

public class OpenNewAccountScreenplay extends BaseScreenplay {

    @BeforeMethod
    public void ensureLogin() {
        user.performs(login());
    }

    @Test
    public void canOpenNewAccount() {
        user.performs(openNewAccount());

        assertEquals(user.seesThat(newAccountBalance()).intValue(), 10000);
    }
}
