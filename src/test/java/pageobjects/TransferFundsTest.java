package pageobjects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferFundsTest extends BaseTest {

  @BeforeEach
  public void ensureLogin() {
    homePage.login(testUsername, testPassword);
  }

  @Test
  public void transferFunds() {
    var fromAccountIndex = 0;
    var toAccountIndex = 1;
    var amountInCents = 1000;

    var openNewAccountPage = homePage.clickOpenNewAccountLink();
    openNewAccountPage.openAccount();

    var accountsOverviewPage = homePage.clickAccountsOverviewLink();
    var originalFromBalance = accountsOverviewPage
        .getBalanceInCents(fromAccountIndex);
    var originalToBalance = accountsOverviewPage
        .getBalanceInCents(toAccountIndex);

    var transferFundsPage = homePage.clickTransferFundsLink();
    transferFundsPage
        .transfer(amountInCents, fromAccountIndex, toAccountIndex);

    homePage.clickAccountsOverviewLink();

    assertEquals(
        accountsOverviewPage.getBalanceInCents(fromAccountIndex),
        originalFromBalance - amountInCents
    );
    assertEquals(
        accountsOverviewPage.getBalanceInCents(toAccountIndex),
        originalToBalance + amountInCents
    );
  }
}
