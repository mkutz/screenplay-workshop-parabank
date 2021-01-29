package screenplay;

import java.util.Objects;

public class Credentials implements Fact {

  private static final Credentials DEFAULT_CREDENTIALS =
      credentials("john", "demo");
  private final String username;
  private final String password;

  public Credentials(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public static Credentials credentials(
      String username, String password) {
    return new Credentials(username, password);
  }

  public static Credentials defaultCredentials() {
    return DEFAULT_CREDENTIALS;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Credentials that = (Credentials) o;
    return Objects.equals(username, that.username) && Objects.equals(password, that.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, password);
  }

  @Override
  public String toString() {
    return "credentials of " + username;
  }
}
