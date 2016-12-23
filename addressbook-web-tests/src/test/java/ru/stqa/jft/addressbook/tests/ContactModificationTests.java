package ru.stqa.jft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;

/**
 * Created by Anna on 2016-12-11.
 */
public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification(){
        app.getNavigationHelper().goToHomepage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToNewContact();
            app.getContactHelper().createContact(new ContactData("Jan", "Kowalski", "Zielona 7", "123543123", "kowalski@poczta.pl", "test1"));
        }
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().initContactModification(before - 1);
        app.getContactHelper().fillContactForm(new ContactData("Jan", "Kowalski", "Zielona 7", "123543123", "kowalski@poczta.pl", null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomepage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before);
    }
}
