package ru.stqa.jft.addressbook.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

/**
 * Created by Anna on 2016-12-11.
 */
public class ContactDeletionTests extends TestBase {

    //to fix
    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().goToHomepage();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContacts();
        FirefoxDriver wd = new FirefoxDriver();
        wd.switchTo().alert().accept();
        app.getNavigationHelper().goToHomepage();

    }
}
