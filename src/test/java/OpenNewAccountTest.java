import org.testng.annotations.Test;
import pages.AccountOpenedPage;
import pages.AccountsOverviewPage;
import pages.OpenNewAccountPage;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class OpenNewAccountTest extends BaseTest {

    @Test
    public void canOpenNewAccount() {
        homePage.login(testUsername, testPassword);

        OpenNewAccountPage openNewAccountPage = homePage.clickOpenNewAccountLink();
        AccountOpenedPage accountOpenedPage = openNewAccountPage.openAccount();

        String newAccountId = accountOpenedPage.getNewAccountId();
        assertFalse(newAccountId.isBlank());

        AccountsOverviewPage accountsOverviewPage = homePage.clickAccountsOverviewLink();

        assertTrue(accountsOverviewPage.getAccountIdsList().contains(newAccountId));
    }
}
