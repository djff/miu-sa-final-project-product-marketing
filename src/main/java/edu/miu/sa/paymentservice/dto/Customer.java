package edu.miu.sa.paymentservice.dtos;


import java.util.List;
import java.util.StringJoiner;

public class Customer {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private List<Address> addresses;
    private List<Payment> payments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Customer.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("email='" + email + "'")
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("addresses=" + addresses)
                .add("payments=" + payments)
                .toString();
    }
}

