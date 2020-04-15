package screenplay.facts;

import java.util.Objects;

public class MainAccountId implements Fact {

    private final String mainAccountId;

    private MainAccountId(String mainAccountId) {
        this.mainAccountId = mainAccountId;
    }

    public static MainAccountId mainAccountId(String mainAccountId) {
        return new MainAccountId(mainAccountId);
    }

    public String getMainAccountId() {
        return mainAccountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MainAccountId that = (MainAccountId) o;
        return Objects.equals(mainAccountId, that.mainAccountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mainAccountId);
    }

    @Override
    public String toString() {
        return "MainAccountId{" +
                "mainAccountId='" + mainAccountId + '\'' +
                '}';
    }
}
