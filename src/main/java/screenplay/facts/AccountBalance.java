package screenplay.facts;

import java.util.Objects;

public class AccountBalance {

    private final String accountId;
    private final int balanceInCents;

    public AccountBalance(String accountId, int balanceInCents) {
        this.accountId = accountId;
        this.balanceInCents = balanceInCents;
    }

    public String getAccountId() {
        return accountId;
    }

    public int getBalanceInCents() {
        return balanceInCents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountBalance that = (AccountBalance) o;
        return balanceInCents == that.balanceInCents &&
                Objects.equals(accountId, that.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, balanceInCents);
    }

    @Override
    public String toString() {
        return "AccountBalance{" +
                "accountId='" + accountId + '\'' +
                ", balanceInCents=" + balanceInCents +
                '}';
    }
}
