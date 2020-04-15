package screenplay.facts;

import java.util.Objects;

public class PhoneNumber implements Fact {

    private final String phoneNumber;

    private PhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static PhoneNumber phoneNumber(String phoneNumber) {
        return new PhoneNumber(phoneNumber);
    }

    public static PhoneNumber defaultPhoneNumber() {
        return new PhoneNumber("+1 650-680-1000");
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber);
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
