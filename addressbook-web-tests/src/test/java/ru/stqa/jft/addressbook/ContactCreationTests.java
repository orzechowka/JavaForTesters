package ru.stqa.jft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void ContactCreationTests() {
        goToNewContact();
        fillContactForm(new ContactData("Jan", "Kowalski", "Zielona 7", "123543123", "kowalski@poczta.pl"));
        submitContactCreation();
        returnToHomepage();
    }

}
