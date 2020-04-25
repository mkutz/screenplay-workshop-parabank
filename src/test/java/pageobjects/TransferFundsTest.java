package pageobjects;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobject.pages.AccountsOverviewPage;
import pageobject.pages.OpenNewAccountPage;
import pageobject.pages.TransferFundsPage;

import static org.testng.Assert.assertEquals;

public class TransferFundsTest extends BaseTest {

    @BeforeMethod
    public void ensureLogin() {
        homePage.login(testUsername, testPassword);
    }

    @Test
    public void transferFunds() {
        int fromAccountIndex = 0;
        int toAccountIndex = 1;
        int amountInCents = 1000;

        OpenNewAccountPage openNewAccountPage = homePage.clickOpenNewAccountLink();
        openNewAccountPage.openAccount().getNewAccountId();

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
