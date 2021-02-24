package screenplay.openaccount;

import screenplay.Fact;

import java.util.Objects;

public class NewAccountId implements Fact {

  private final String accountId;

  public NewAccountId(String accountId) {
    this.accountId = accountId;
  }

  public String getAccountId() {
    return accountId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    NewAccountId that = (NewAccountId) o;
    return Objects.equals(accountId, that.accountId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountId);
  }

  @Override
  public String toString() {
    return this.accountId;
  }
}
