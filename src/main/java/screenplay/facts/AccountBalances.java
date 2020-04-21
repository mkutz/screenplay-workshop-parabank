package screenplay.facts;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class AccountBalances implements Fact {

    List<AccountBalance> balances;

    private AccountBalances(List<AccountBalance> balances) {
        this.balances = balances;
    }

    public static AccountBalances accountBalances(List<AccountBalance> balances) {
        return new AccountBalances(balances);
    }

    public int getAccountBalance(String accountId) {
        return balances.stream()
                .filter(balance -> balance.getAccountId().equals(accountId))
                .findAny().orElseThrow(() -> new IllegalStateException("Unknown account ID " + accountId))
                .getBalanceInCents();
    }

    public List<String> getAccountIds() {
        return balances.stream()
                .map(AccountBalance::getAccountId)
                .collect(toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountBalances that = (AccountBalances) o;
        return Objects.equals(balances, that.balances);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balances);
    }

    @Override
    public String toString() {
        return "AccountsBalance{" +
                "balances=" + balances +
                '}';
    }
}
