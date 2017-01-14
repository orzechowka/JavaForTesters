package ru.stqa.jft.addressbook.tests;


import org.apache.xpath.SourceTree;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;
import ru.stqa.jft.addressbook.model.Contacts;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() {
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[] {new ContactData().withName("name1").withSurname("surname1").withHomeNumber("number1")
                .withMobileNumber("number1").withWorkNumber("number1").withAddress("address1").withEmail1("email1").withGroup("test1")});
        return list.iterator();
    }

    @Test (dataProvider = "validContacts")
    public void ContactCreationTests(ContactData contact) {
        Contacts before = app.contact().all();
        app.goTo().newContact();
        // File photo = new File("src/test/resources/02.jpg");
        app.contact().create(contact);
        app.contact().returnToHomepage();
        assertThat(app.contact().count(), equalTo(before.size()+1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

}
