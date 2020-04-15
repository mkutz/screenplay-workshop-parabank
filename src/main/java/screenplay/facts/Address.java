package screenplay.facts;

import java.util.Objects;

public class Address implements Fact {

    private final String street;
    private final String city;
    private final String zipCode;
    private final String state;

    private Address(String street, String city, String zipCode, String state) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.state = state;
    }

    public static Address address(String street, String city, String zipCode, String state) {
        return new Address(street, city, zipCode, state);
    }

    public static Address defaultAddress() {
        return new Address("155 Bovet Rd #600", "San Mateo", "CA", "94402");
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) &&
                Objects.equals(city, address.city) &&
                Objects.equals(zipCode, address.zipCode) &&
                Objects.equals(state, address.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, city, zipCode, state);
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
