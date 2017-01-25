package ru.stqa.jft.addressbook.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;
import ru.stqa.jft.addressbook.model.Contacts;
import ru.stqa.jft.addressbook.model.GroupData;
import ru.stqa.jft.addressbook.model.Groups;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.Before;

/**
 * Created by Anna on 2017-01-23.
 */
public class ContactAddToGroupTests extends TestBase{

    private SessionFactory sessionFactory;


    @BeforeClass
    public void setUp() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            e.printStackTrace();
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    @Test
    public void hbConnection() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> results = session.createQuery("from GroupData").list();
        for (GroupData group : results) {
            Contacts contact = group.getContacts();

        }

        //Groups groups = app.db().groups();
        Contacts before = app.contact().all();
        ContactData addedContact = before.iterator().next();
        app.contact().selectContactById(addedContact.getId());
        String group = "test 1";
        app.contact().selectGroup(group);
        app.contact().addToGroup();

        session.getTransaction().commit();
        session.close();
        }

}
