package pageobjects;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobject.pages.AccountOpenedPage;
import pageobject.pages.AccountsOverviewPage;
import pageobject.pages.OpenNewAccountPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class OpenNewAccountTest extends BaseTest {

    @BeforeMethod
    public void ensureLogin() {
        homePage.login(testUsername, testPassword);
    }

    @Test
    public void canOpenNewAccount() {
        OpenNewAccountPage openNewAccountPage = homePage.clickOpenNewAccountLink();
        AccountOpenedPage accountOpenedPage = openNewAccountPage.openAccount();

        String newAccountId = accountOpenedPage.getNewAccountId();
        assertFalse(newAccountId.isBlank());

        AccountsOverviewPage accountsOverviewPage = homePage.clickAccountsOverviewLink();

        assertEquals(accountsOverviewPage.getAccountBalanceInCents(newAccountId), 10000);
    }
}
