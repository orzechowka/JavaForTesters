package ru.stqa.jft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;

/**
 * Created by Anna on 2017-01-09.
 */
public class ContactPhoneTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().list().size() == 0) {
            app.goTo().newContact();
            app.contact().create(new ContactData().withName("Jan").withSurname("Kowalski").withAddress("Zielona 7").withMobileNumber("123543123").withEmail1("kowalski@poczta.pl").withGroup("test1"));
        }
    }

    @Test
    public void testContactPhones(){
        app.goTo().goToHomepage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        MatcherAssert.assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        MatcherAssert.assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
        MatcherAssert.assertThat(contact.getEmail1(), equalTo(contactInfoFromEditForm.getEmail1()));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomeNumber(), contact.getMobileNumber(), contact.getWorkNumber())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactPhoneTests::cleaned).collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s","").replaceAll("[-()]", "");
    }
}
