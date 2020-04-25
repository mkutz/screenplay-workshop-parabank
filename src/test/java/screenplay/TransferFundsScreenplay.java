package screenplay;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screenplay.facts.AccountBalances;

import static org.testng.Assert.assertEquals;
import static screenplay.questions.AccountBalancesQuestion.currentAccountBalances;
import static screenplay.tasks.LoginTask.login;
import static screenplay.tasks.OpenNewAccountTask.openNewAccount;
import static screenplay.tasks.TransferFundsTask.transferFunds;

public class TransferFundsScreenplay extends BaseScreenplay {

    @BeforeMethod
    public void ensureLogin() {
        user.performs(login());
    }

    @Test
    public void testTransferFunds() {
        int fromAccountIndex = 0;
        int toAccountIndex = 1;
        int amountInCents = 1000;
        user.performs(openNewAccount());
        AccountBalances originalBalances = user.seesThat(currentAccountBalances());

        user.performs(transferFunds(fromAccountIndex, toAccountIndex, amountInCents));
        AccountBalances balances = user.seesThat(currentAccountBalances());

        assertEquals(balances.getAccountBalance(fromAccountIndex),
                originalBalances.getAccountBalance(fromAccountIndex) - amountInCents);
        assertEquals(balances.getAccountBalance(toAccountIndex),
                originalBalances.getAccountBalance(toAccountIndex) + amountInCents);
    }
}
