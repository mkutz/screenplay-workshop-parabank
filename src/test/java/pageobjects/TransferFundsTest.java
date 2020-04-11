package pageobjects;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobject.pages.AccountsOverviewPage;
import pageobject.pages.TransferFundsPage;

import static org.testng.Assert.assertEquals;

public class TransferFundsTest extends BaseTest {

    @BeforeMethod
    public void login() {
        homePage.login(testUsername, testPassword);
    }

    @Test
    public void transferFunds() {
        String newAccountId = homePage.clickOpenNewAccountLink().openAccount().getNewAccountId();
        AccountsOverviewPage accountsOverviewPage = homePage.clickAccountsOverviewLink();
        String mainAccountId = accountsOverviewPage.getAccountIdsList().get(0);
        int originalFromBalance = accountsOverviewPage.getAccountBalanceInCents(mainAccountId);
        int originalToBalance = accountsOverviewPage.getAccountBalanceInCents(newAccountId);
        int transferAmountInCents = 1000;

        TransferFundsPage transferFundsPage = homePage.clickTransferFundsLink();
        transferFundsPage.transfer(transferAmountInCents, mainAccountId, newAccountId);

        homePage.clickAccountsOverviewLink();
        assertEquals(accountsOverviewPage.getAccountBalanceInCents(newAccountId),
                originalToBalance + transferAmountInCents);
        assertEquals(accountsOverviewPage.getMainAccountBalanceInCents(),
                originalFromBalance - transferAmountInCents);
    }
}
