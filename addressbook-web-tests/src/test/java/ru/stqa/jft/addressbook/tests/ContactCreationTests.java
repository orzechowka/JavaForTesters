package ru.stqa.jft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;
import ru.stqa.jft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void ContactCreationTests() {
        Contacts before = app.contact().all();
        app.goTo().newContact();
        ContactData contact = new ContactData().withName("Jan").withSurname("Kowalski").withAddress("Zielona 7").withMobileNumber("123543123").withEmail1("kowalski@poczta.pl").withGroup("test1");
        app.contact().create(contact);
        app.contact().returnToHomepage();
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

}
