package ru.stqa.jft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;
import ru.stqa.jft.addressbook.model.Contacts;
import ru.stqa.jft.addressbook.model.GroupData;
import ru.stqa.jft.addressbook.model.Groups;

/**
 * Created by Anna on 2017-01-25.
 */
public class ContactAddingToGroupTests extends TestBase{


    @Test
    public void testContactAddToGroup() {

        if(app.db().groups().size() == 0) {
            GroupData group = new GroupData().withName("group").withHeader("header").withFooter("footer");
            app.group().create(group);
        }

        Groups groups = app.db().groups();
        //for (GroupData group : groups) {
        //    Contacts contact = group.getContacts();

        GroupData group = groups.iterator().next();
        String groupName = group.getName();
        Contacts contacts = app.db().contacts();
        ContactData contact = contacts.iterator().next();

        app.contact().selectContactById(contact.getId());
        app.contact().selectGroup(groupName);
        app.contact().addToGroup();

        }
  
}
