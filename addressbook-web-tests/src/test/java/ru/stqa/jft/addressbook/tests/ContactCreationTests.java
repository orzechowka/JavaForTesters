package ru.stqa.jft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void ContactCreationTests() {
        int before = app.getContactHelper().getContactCount();
        app.getNavigationHelper().goToNewContact();
        app.getContactHelper().createContact(new ContactData("Jan", "Kowalski", "Zielona 7", "123543123", "kowalski@poczta.pl", "test1"));
        app.getContactHelper().returnToHomepage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before + 1);

    }

}
