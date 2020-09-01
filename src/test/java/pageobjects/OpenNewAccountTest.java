package pageobjects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageobject.AccountOpenedPage;
import pageobject.AccountsOverviewPage;
import pageobject.OpenNewAccountPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class OpenNewAccountTest extends BaseTest {

    @BeforeEach
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
