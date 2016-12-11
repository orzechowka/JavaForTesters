package ru.stqa.jft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;

/**
 * Created by Anna on 2016-12-11.
 */
public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification(){
        app.getNavigationHelper().goToHomepage();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Jan", "Kowalski", "Zielona 7", "123543123", "kowalski@poczta.pl"));
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomepage();
    }
}
