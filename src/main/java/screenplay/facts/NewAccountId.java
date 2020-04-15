package screenplay.facts;

import java.util.Objects;

public class NewAccountId implements Fact {

    private final String newAccountId;

    private NewAccountId(String newAccountId) {
        this.newAccountId = newAccountId;
    }

    public static NewAccountId newAccountId(String newAccountId) {
        return new NewAccountId(newAccountId);
    }

    public String getNewAccountId() {
        return newAccountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewAccountId that = (NewAccountId) o;
        return Objects.equals(newAccountId, that.newAccountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(newAccountId);
    }

    @Override
    public String toString() {
        return "NewAccountId{" +
                "newAccountId='" + newAccountId + '\'' +
                '}';
    }
}
