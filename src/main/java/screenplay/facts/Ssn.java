package screenplay.facts;

public class Ssn implements Fact {

    private final String ssn;

    private Ssn(String ssn) {
        this.ssn = ssn;
    }

    public static Ssn ssn(String ssn) {
        return new Ssn(ssn);
    }

    public static Ssn defaultSsn() {
        return new Ssn("036-44-1383");
    }

    public String getSsn() {
        return ssn;
    }
}
