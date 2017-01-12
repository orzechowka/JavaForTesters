package ru.stqa.jft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

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

        MatcherAssert.assertThat(mergeNameAndSurname(contactInfoFromEditForm), equalTo(contactInfoFromDetailsPage.getNamesAndSurname().replace(" ", "")));
        MatcherAssert.assertThat(contactInfoFromEditForm.getEmail1(), equalTo(contactInfoFromDetailsPage.getEmail1()));
    }

    private String mergeNameAndSurname(ContactData contact) {
        return Arrays.asList(contact.getName(), contact.getSurname()).stream().filter((s) -> ! s.equals("")).collect(Collectors.joining());
    }
}
