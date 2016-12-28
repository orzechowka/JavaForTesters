package ru.stqa.jft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

/**
 * Created by Anna on 2016-12-11.
 */
public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification(){
        //app.getNavigationHelper().goToHomepage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToNewContact();
            app.getContactHelper().createContact(new ContactData("Jan", "Kowalski", "Zielona 7", "123543123", "kowalski@poczta.pl", "test1"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();

        app.getContactHelper().initContactModification(before.size() - 1);
        ContactData contact = new ContactData(before.get(before.size()- 1).getId(), "Jan", "Kowalski", "Zielona 7", "123543123", "kowalski@poczta.pl", null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomepage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
        before.remove(before.size() - 1);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
