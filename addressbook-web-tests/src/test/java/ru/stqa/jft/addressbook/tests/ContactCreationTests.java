package ru.stqa.jft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void ContactCreationTests() {
        app.goToNewContact();
        app.fillContactForm(new ContactData("Jan", "Kowalski", "Zielona 7", "123543123", "kowalski@poczta.pl"));
        app.submitContactCreation();
        app.returnToHomepage();
    }

}
