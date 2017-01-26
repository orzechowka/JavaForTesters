package ru.stqa.jft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;
import ru.stqa.jft.addressbook.model.Contacts;
import ru.stqa.jft.addressbook.model.GroupData;
import ru.stqa.jft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Anna on 2017-01-26.
 */
public class ContactDeletionFromGroup extends TestBase{

    int contactId;
    int groupId;
    String groupName;
    Contacts contacts;
    ContactData contact;

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

        Groups groups = app.db().groups();
        for (GroupData group: groups) {
            if (group.getContacts().equals(null)) {
                contacts = app.db().contacts();
                contact = contacts.iterator().next();
                GroupData group1 = groups.iterator().next();
                app.contact().selectContactById(contact.getId());
                app.contact().selectGroup(group1.getName());
                app.contact().addToGroup();
            } else {
                break;
            }
        }
    }

    @Test
    public void testDeletionFromGroup() {
        Groups groups = app.db().groups();
        for (GroupData group: groups) {
            if (!group.getContacts().equals(null)) {
                Contacts contacts = group.getContacts();
                groupName = group.getName();
                groupId = group.getId();
                for (ContactData contact: contacts) {
                    contactId = contact.getId();
                break;
                }
                break;
            }
        }

        app.contact().chooseGroup(groupName);
        app.contact().selectContactById(contactId);
        app.contact().removeFromGroup();
        app.group().goToGroupPage(groupId);

        assertThat(app.db().contacts(), equalTo(contacts.remove(contact)));

    }
}
