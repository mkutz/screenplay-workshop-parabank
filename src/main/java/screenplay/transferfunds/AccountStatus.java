package screenplay.transferfunds;

import java.util.Objects;

public class AccountStatus {

  private final String id;
  private final int balance;

  public AccountStatus(String id, int balance) {
    this.id = id;
    this.balance = balance;
  }

  public String getId() {
    return id;
  }

  public int getBalance() {
    return balance;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AccountStatus that = (AccountStatus) o;
    return balance == that.balance && Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, balance);
  }

  @Override
  public String toString() {
    return id + " (" + balance + ")";
  }
}
