package ru.stqa.jft.addressbook.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;

/**
 * Created by Anna on 2016-12-11.
 */
public class ContactDeletionTests extends TestBase {

    //to fix
    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().goToHomepage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToNewContact();
            app.getContactHelper().createContact(new ContactData("Jan", "Kowalski", "Zielona 7", "123543123", "kowalski@poczta.pl", "test1"));
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContacts();
        app.getContactHelper().acceptAlert();
        app.getNavigationHelper().goToHomepage();

    }
}
