package ru.stqa.jft.mantis.tests;

import org.testng.annotations.Test;

/**
 * Created by Anna on 2017-01-28.
 */
public class RegistrationTests extends TestBase{

    @Test
    public void testRegistration() {
        app.registration().start("user1", "user1@localhost.localdomain");

    }
}
