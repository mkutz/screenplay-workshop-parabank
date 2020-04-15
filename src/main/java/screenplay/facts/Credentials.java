package screenplay.facts;

import java.util.Objects;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class Credentials implements Fact {

    private final String username;
    private final String password;

    private Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static Credentials credentials(String username, String password) {
        return new Credentials(username, password);
    }

    public static Credentials randomCredentials() {
        return new Credentials(randomAlphabetic(12), "Sup3rS3cr3t!");
    }

    public static Credentials defaultCredentials() {
        return new Credentials("john", "demo!");
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
        return Objects.equals(username, that.username) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    public String toString() {
        return "Credentials{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
