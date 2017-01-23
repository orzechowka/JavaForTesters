package ru.stqa.jft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;
import ru.stqa.jft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Anna on 2016-12-11.
 */
public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if(app.db().contacts().size() == 0) {
            app.goTo().newContact();
            app.contact().create(new ContactData().withName("Jan").withSurname("Kowalski").withAddress("Zielona 7").withHomeNumber("675-76-16").withMobileNumber("123 43 23").withWorkNumber("(44)-67-678").withEmail1("kowalski@poczta.pl").withGroup("test1").withPhoto(new File("src/test/resources/02.jpg")));
        }
    }

    @Test
    public void testContactModification(){
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withName("Jan").withSurname("Kowalski").withAddress("Zielona 7").withMobileNumber("123543123").withEmail1("kowalski@poczta.pl").withPhoto(new File("src/test/resources/02.jpg"));
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, CoreMatchers.equalTo(before.without(modifiedContact).withAdded(contact)));
    }

}
