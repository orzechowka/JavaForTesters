package ru.stqa.jft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void ContactCreationTests() {
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getNavigationHelper().goToNewContact();
        ContactData contact = new ContactData("Jan", "Kowalski", "Zielona 7","123543123", "kowalski@poczta.pl", "test1");
                app.getContactHelper().createContact(contact);
        app.getContactHelper().returnToHomepage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    }

}
