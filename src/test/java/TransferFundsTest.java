import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AccountsOverviewPage;
import pages.TransferFundsPage;

public class TransferFundsTest extends BaseTest {

    @Test
    public void transferFunds() {
        homePage.login(testUsername, testPassword);
        String newAccountId = homePage.clickOpenNewAccountLink().openAccount().getNewAccountId();

        TransferFundsPage transferFundsPage = homePage.clickTransferFundsLink();

        transferFundsPage.transfer("10.22", newAccountId);

        AccountsOverviewPage accountsOverviewPage = homePage.clickAccountsOverviewLink();

        Assert.assertEquals(accountsOverviewPage.getAccount(newAccountId).getBalanceInCents(), 2500 + 1022);
    }
}
