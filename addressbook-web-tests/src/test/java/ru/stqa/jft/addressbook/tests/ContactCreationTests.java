package ru.stqa.jft.addressbook.tests;


import org.apache.xpath.SourceTree;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;
import ru.stqa.jft.addressbook.model.Contacts;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(";");
            list.add(new Object[] {new ContactData().withName(split[0]).withSurname(split[1]).withAddress(split[2])
                    .withHomeNumber(split[3]).withMobileNumber(split[4]).withWorkNumber(split[5]).withEmail1(split[6])
                    .withGroup(split[7])});
            line = reader.readLine();
        }
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
