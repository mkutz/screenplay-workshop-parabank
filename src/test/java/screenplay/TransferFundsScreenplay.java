package screenplay;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screenplay.facts.AccountBalances;

import static org.testng.Assert.assertEquals;
import static screenplay.tasks.CheckAccountBalances.checkAccountBalances;
import static screenplay.tasks.Login.login;
import static screenplay.tasks.OpenNewAccount.openNewAccount;
import static screenplay.tasks.TransferFunds.transferFunds;

public class TransferFundsScreenplay extends BaseScreenplay {

    @BeforeMethod
    public void ensureLogin() {
        user.performs(login());
    }

    @Test
    public void testTransferFunds() {
        user.performs(openNewAccount());
        user.performs(checkAccountBalances());
        AccountBalances originalBalances = user.remembers(AccountBalances.class);
        String fromAccountId = originalBalances.getAccountIds().get(0);
        String toAccountId = originalBalances.getAccountIds().get(1);

        user.performs(transferFunds(fromAccountId, toAccountId, 1000));
        user.performs(checkAccountBalances());
        AccountBalances balances = user.remembers(AccountBalances.class);

        assertEquals(balances.getAccountBalance(toAccountId),
                originalBalances.getAccountBalance(toAccountId) + 1000);
        assertEquals(balances.getAccountBalance(fromAccountId),
                originalBalances.getAccountBalance(fromAccountId) - 1000);
    }
}
