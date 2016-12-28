package ru.stqa.jft.addressbook.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by Anna on 2016-12-11.
 */
public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        //app.getNavigationHelper().goToHomepage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToNewContact();
            app.getContactHelper().createContact(new ContactData("Jan", "Kowalski", "Zielona 7", "123543123", "kowalski@poczta.pl", "test1"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteSelectedContacts();
        app.getContactHelper().acceptAlert();
        app.getNavigationHelper().goToHomepage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);


    }
}
