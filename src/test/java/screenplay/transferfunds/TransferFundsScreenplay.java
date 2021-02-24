package screenplay.transferfunds;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import screenplay.BaseScreenplay;
import screenplay.login.Login;
import screenplay.openaccount.OpenNewAccount;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static screenplay.login.Credentials.defaultCredentials;

public class TransferFundsScreenplay extends BaseScreenplay {

  @BeforeEach
  public void ensureLogin() {
    user.learns(defaultCredentials())
        .does(new Login());
  }

  @Test
  public void transferFunds() {
    var amountInCents = 1000;

    user.does(new OpenNewAccount());

    final var original = user.checks(new Accounts());
    final var fromAccount = original.get(0);
    final var toAccount = original.get(1);

    user.does(new TransferFunds(
        fromAccount.getId(),
        toAccount.getId(),
        amountInCents)
    );

    final var result = user.checks(new Accounts());

    assertEquals(
        original.get(0).getBalance() - amountInCents,
        result.get(0).getBalance());
    assertEquals(
        original.get(1).getBalance() + amountInCents,
        result.get(1).getBalance());
  }
}
