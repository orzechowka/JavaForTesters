package ru.stqa.jft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTests extends TestBase {

    @Test
    public void ContactCreationTests() {
        Set<ContactData> before = app.contact().all();
        app.goTo().newContact();
        ContactData contact = new ContactData().withName("Jan").withSurname("Kowalski").withAddress("Zielona 7").withMobileNumber("123543123").withEmail1("kowalski@poczta.pl").withGroup("test1");
        app.contact().create(contact);
        app.contact().returnToHomepage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);
    }

}
