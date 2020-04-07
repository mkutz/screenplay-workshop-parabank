package facts;

public class Account {

    private final String id;
    private final int balanceInCents;
    private final int availableAmountInCents;

    public Account(String id, Integer balanceInCents, Integer availableAmountInCents) {
        this.id = id;
        this.balanceInCents = balanceInCents;
        this.availableAmountInCents = availableAmountInCents;
    }

    public String getId() {
        return id;
    }

    public int getBalanceInCents() {
        return balanceInCents;
    }

    public int getAvailableAmountInCents() {
        return availableAmountInCents;
    }
}
