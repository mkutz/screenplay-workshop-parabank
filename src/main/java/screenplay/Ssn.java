package screenplay;

import java.util.Objects;

public class Ssn implements Fact {

  private final String ssn;

  public Ssn(String ssn) {
    this.ssn = ssn;
  }

  public String getSsn() {
    return ssn;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Ssn ssn1 = (Ssn) o;
    return Objects.equals(ssn, ssn1.ssn);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ssn);
  }

  @Override
  public String toString() {
    return ssn;
  }
}
