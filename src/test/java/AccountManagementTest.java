import org.testng.annotations.Test;
import pages.AccountOpenedPage;
import pages.AccountsOverviewPage;
import pages.OpenNewAccountPage;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class AccountManagementTest extends BaseTest {

    @Test
    public void canOpenNewAccount() {
        homePage.login(testUsername, testPassword);

        OpenNewAccountPage openNewAccountPage = homePage.clickOpenNewAccountLink();
        AccountOpenedPage accountOpenedPage = openNewAccountPage.openAccount();

        assertFalse(accountOpenedPage.getNewAccountId().isBlank());
    }

    @Test
    public void canSeeAllAccountsInAccountsOverview() {
        homePage.login(testUsername, testPassword);

        AccountsOverviewPage accountsOverviewPage = homePage.clickAccountsOverviewLink();

        assertTrue(accountsOverviewPage.getAccountIdsList().size() > 0);
    }
}
