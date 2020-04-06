import org.testng.annotations.Test;
import pages.TransferFundsPage;

public class TransferFundsTest extends BaseTest {

    @Test
    public void transferFunds() {
        homePage.login(testUsername, testPassword);
        String newAccountId = homePage.clickOpenNewAccountLink().openAccount().getNewAccountId();

        TransferFundsPage transferFundsPage = homePage.clickTransferFundsLink();

        transferFundsPage.transfer("10.22", newAccountId);
    }
}
