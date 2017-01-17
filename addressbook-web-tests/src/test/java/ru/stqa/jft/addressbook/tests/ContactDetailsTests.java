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

        MatcherAssert.assertThat(cleaned(contactInfoFromDetailsPage.getAllInfo()), equalTo(cleaned(mergeAllInfo(contactInfoFromEditForm))));
    }

    private String mergeAllInfo(ContactData contact) {
        return Arrays.asList(contact.getName(), contact.getSurname(), contact.getAddress(), contact.getHomeNumber(), contact.getMobileNumber(), contact.getWorkNumber(), contact.getEmail1()).stream().filter((s) -> !s.equals("")).collect(Collectors.joining());
    }

    private String cleaned(String allInfo) {
        return allInfo.replaceAll("\\s", "").replaceAll("[-()]", "").replace("H:", "").replace("M:", "").replace("W:", "");
    }
}
