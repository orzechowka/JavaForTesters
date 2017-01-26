package ru.stqa.jft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;
import ru.stqa.jft.addressbook.model.Contacts;
import ru.stqa.jft.addressbook.model.GroupData;
import ru.stqa.jft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Anna on 2017-01-25.
 */
public class ContactAddingToGroupTests extends TestBase{

    ContactData contactAdded;
    int id;

    @BeforeMethod

    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().newContact();
            app.contact().create(new ContactData().withName("Jan").withSurname("Kowalski").withAddress("Zielona 7").withHomeNumber("675-76-16").withMobileNumber("123 43 23").withWorkNumber("(44)-67-678").withEmail1("kowalski@poczta.pl"));
        }
        if (app.db().groups().size() == 0) {
            GroupData group = new GroupData().withName("group").withHeader("header").withFooter("footer");
            app.group().create(group);
        }
    }
    @Test
    public void testContactAddToGroup() {

        Groups groups = app.db().groups();
        GroupData group = groups.iterator().next();
        String groupName = group.getName();
        Contacts contacts = app.db().contacts();
        Contacts contactsInGroup = group.getContacts();

        if (contactsInGroup.size() >= 1) {
            contacts.retainAll(contactsInGroup);
        }

        for (ContactData contact: contacts) {
            contactAdded = new ContactData();
            id = contact.getId();
        }

        app.contact().selectContactById(id);
        app.contact().selectGroup(groupName);
        app.contact().addToGroup();

        contactsInGroup.add(contactAdded);
        Contacts contactsInGroupAfterAdding = group.getContacts();

        assertThat(contactsInGroup, equalTo(contactsInGroupAfterAdding));

        }

}
