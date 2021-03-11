package pageobjects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class OpenNewAccountTest extends BaseTest {

  @BeforeEach
  public void ensureLogin() {
    homePage.login(testUsername, testPassword);
  }

  @Test
  public void canOpenNewAccount() {
    var accountOpenedPage = homePage
        .clickOpenNewAccountLink()
        .openAccount();

    var newAccountId = accountOpenedPage
        .getNewAccountId();
    assertFalse(newAccountId.isBlank());

    var accountsOverviewPage = homePage
        .clickAccountsOverviewLink();

    assertEquals(
        accountsOverviewPage.getBalanceInCents(newAccountId),
        20000
    );
  }
}
