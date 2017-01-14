package ru.stqa.jft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.jft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 2017-01-14.
 */
public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Group count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    public static void main(String args[]) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();

    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        save(contacts, new File(file));
    }

    private static void save(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactData contact: contacts) {
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getName(), contact.getSurname(), contact.getAddress(),
                    contact.getHomeNumber(), contact.getMobileNumber(), contact.getWorkNumber(), contact.getEmail1(),
                    contact.getGroup()));
        }
        writer.close();
    }

    private static List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withName(String.format("name %s", i)).withSurname(String.format("surname %s", i))
                    .withAddress(String.format("address %s", i)).withHomeNumber(String.format("home number %s", i))
                    .withMobileNumber(String.format("mobile number %s", i)).withWorkNumber(String.format("work number %s", i))
                    .withEmail1(String.format("email %s", i)).withGroup(String.format("group %s", i)));
        }
        return contacts;
    }
}
