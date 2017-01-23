package ru.stqa.jft.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;
import ru.stqa.jft.addressbook.model.Contacts;
import ru.stqa.jft.addressbook.model.Groups;

/**
 * Created by Anna on 2017-01-23.
 */
public class ContactAddToGroupTests extends TestBase{

    @Test
    public void testAddingContactToGroup() {
        Groups groups = app.db().groups();
        Contacts before = app.contact().all();
        ContactData addedContact = before.iterator().next();
        app.contact().selectContact(addedContact.getId());

        //app.contact().selectContact(addedContact.getId());
       // if(addedContact.getGroups() == null) {

        //} else {
       //     app.contact().selectGroup(groups.iterator().next())      }
    }
}
