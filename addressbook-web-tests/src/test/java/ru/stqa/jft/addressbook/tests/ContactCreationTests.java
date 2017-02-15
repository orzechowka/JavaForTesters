package ru.stqa.jft.addressbook.tests;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.apache.xpath.SourceTree;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;
import ru.stqa.jft.addressbook.model.Contacts;
import ru.stqa.jft.addressbook.model.GroupData;
import ru.stqa.jft.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactsFromXml() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xstream = new XStream();
            xstream.processAnnotations(ContactData.class);
            List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
            return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
            }.getType());
            return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
        }
    }

    @Test (dataProvider = "validContactsFromJson")
    public void ContactCreationTests(ContactData contact) {
        Contacts before = app.db().contacts();
        app.goTo().newContact();
        //String photo = "src/test/resources/02.jpg";
        app.contact().create(contact);
        app.contact().returnToHomepage();
        assertThat(app.contact().count(), equalTo(before.size()+1));
        Contacts after = app.db().contacts();
        //assertThat(after, equalTo(
        //        before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
        //broken tests to check screenshots
        assertThat(after, equalTo(before));
        verifyContactListUI();
    }

    @Test
    public void testContactCreation() {
        if(app.db().groups().size() == 0) {
            GroupData group = new GroupData().withName("group").withHeader("header").withFooter("footer");
            app.group().create(group);
        }
        Groups groups = app.db().groups();
        File photo = new File("src/test/resources/02.jpg");
        ContactData newContact = new ContactData().withName("test_name").withSurname("test_surname")
                .withPhoto(photo).inGroup(groups.iterator().next());
        app.goTo().goToHomepage();
        app.goTo().newContact();
        app.contact().fillContactForm(newContact, true);
        app.contact().submitContactCreation();
        app.contact().returnToHomepage();
    }

}
