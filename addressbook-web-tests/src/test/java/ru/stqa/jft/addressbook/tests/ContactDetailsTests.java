package ru.stqa.jft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;

/**
 * Created by Anna on 2017-01-11.
 */
public class ContactDetailsTests extends TestBase{

    @Test
    public void testContactDetails() {
        app.goTo().goToHomepage();

        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        ContactData contactInfoFromDetailsPage = app.contact().infoFromDetailsPage(contact);

        MatcherAssert.assertThat(contactInfoFromEditForm.getName(), equalTo(contactInfoFromDetailsPage.getName()));
        MatcherAssert.assertThat(contactInfoFromEditForm.getSurname(), equalTo(contactInfoFromDetailsPage.getSurname()));

    }
}
