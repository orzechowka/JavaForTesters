package ru.stqa.jft.addressbook.model;

public class ContactData {
    private final String id;
    private final String name;
    private final String surname;
    private final String address;
    private final String mobileNumber;
    private final String email1;
    private final String group;


    public ContactData(String name, String surname, String address, String mobileNumber, String email1, String group) {
        this.id = null;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.email1 = email1;
        this.group = group;
    }

    public ContactData(String id, String name, String surname, String address, String mobileNumber, String email1, String group) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.email1 = email1;
        this.group = group;
    }


    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return surname != null ? surname.equals(that.surname) : that.surname == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        return result;
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

    public String getGroup() {
        return group;
    }

    public String getId() {
        return id;
    }
}

