package ru.stqa.jft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void ContactCreationTests() {
        app.getNavigationHelper().goToNewContact();
        app.getContactHelper().createContact(new ContactData("Jan", "Kowalski", "Zielona 7", "123543123", "kowalski@poczta.pl", "test1"));
        app.getContactHelper().returnToHomepage();
    }

}
