import org.testng.annotations.Test;
import pages.AccountOpenedPage;
import pages.OpenNewAccountPage;

import static org.testng.Assert.assertFalse;

public class AccountManagementTest extends BaseTest {

    @Test
    public void canOpenNewAccount() {
        homePage.login(testUsername, testPassword);

        OpenNewAccountPage openNewAccountPage = homePage.clickOpenNewAccountLink();
        AccountOpenedPage accountOpenedPage = openNewAccountPage.openAccount();

        assertFalse(accountOpenedPage.getNewAccountId().isBlank());
    }
}
