package ru.stqa.jft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;

import java.util.List;
import java.util.Set;

/**
 * Created by Anna on 2016-12-11.
 */
public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().list().size() == 0) {
            app.goTo().newContact();
            app.contact().create(new ContactData().withName("Jan").withSurname("Kowalski").withAddress("Zielona 7").withMobileNumber("123543123").withEmail1("kowalski@poczta.pl").withGroup("test1"));
        }
    }
    @Test
    public void testContactDeletion() {
        Set<ContactData> before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedContact);
        Assert.assertEquals(before, after);


    }


}
