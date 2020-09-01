package pageobjects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageobject.AccountsOverviewPage;
import pageobject.OpenNewAccountPage;
import pageobject.TransferFundsPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferFundsTest extends BaseTest {

    @BeforeEach
    public void ensureLogin() {
        homePage.login(testUsername, testPassword);
    }

    @Test
    public void transferFunds() {
        int fromAccountIndex = 0;
        int toAccountIndex = 1;
        int amountInCents = 1000;

        OpenNewAccountPage openNewAccountPage = homePage.clickOpenNewAccountLink();
        openNewAccountPage.openAccount();

        AccountsOverviewPage accountsOverviewPage = homePage.clickAccountsOverviewLink();
        int originalFromBalance = accountsOverviewPage.getAccountBalanceInCents(fromAccountIndex);
        int originalToBalance = accountsOverviewPage.getAccountBalanceInCents(toAccountIndex);

        TransferFundsPage transferFundsPage = homePage.clickTransferFundsLink();
        transferFundsPage.transfer(amountInCents, fromAccountIndex, toAccountIndex);

        homePage.clickAccountsOverviewLink();

        assertEquals(accountsOverviewPage.getAccountBalanceInCents(fromAccountIndex),
                originalFromBalance - amountInCents);
        assertEquals(accountsOverviewPage.getAccountBalanceInCents(toAccountIndex),
                originalToBalance + amountInCents);
    }
}
