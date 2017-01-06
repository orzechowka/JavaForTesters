package ru.stqa.jft.addressbook.model;

public class ContactData {
    private int id  = Integer.MAX_VALUE;
    private String name;
    private String surname;
    private String address;
    private String mobileNumber;
    private String email1;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withName(String name) {
        this.name = name;
        return this;
    }

    public ContactData withSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }

    public ContactData withEmail1(String email1) {
        this.email1 = email1;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    private String group;


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



    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }
}

