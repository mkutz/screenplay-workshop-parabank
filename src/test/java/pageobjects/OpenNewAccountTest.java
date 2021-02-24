package pageobjects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class OpenNewAccountTest extends BaseTest {

  static int INITIAL_ACCOUNT_BALANCE = 10000;

  @BeforeEach
  public void ensureLogin() {
    homePage.login(testUsername, testPassword);
  }

  @Test
  public void canOpenNewAccount() {
    var openNewAccountPage = homePage.clickOpenNewAccountLink();
    var accountOpenedPage = openNewAccountPage.openAccount();

    var newAccountId = accountOpenedPage.getNewAccountId();
    assertFalse(newAccountId.isBlank());

    var accountsOverviewPage = homePage.clickAccountsOverviewLink();

    assertEquals(
        INITIAL_ACCOUNT_BALANCE,
        accountsOverviewPage.getBalanceInCents(newAccountId)
    );
  }
}
