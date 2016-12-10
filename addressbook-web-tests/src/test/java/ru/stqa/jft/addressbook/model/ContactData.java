package ru.stqa.jft.addressbook.model;

public class ContactData {
    private final String name;
    private final String surname;
    private final String address;
    private final String mobileNumber;
    private final String email1;

    public ContactData(String name, String surname, String address, String mobileNumber, String email1) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.email1 = email1;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getEmail1() {
        return email1;
    }
}
