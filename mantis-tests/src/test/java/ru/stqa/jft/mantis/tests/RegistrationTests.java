package ru.stqa.jft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.jft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by Anna on 2017-01-28.
 */
public class RegistrationTests extends TestBase{


    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }


    @Test
    public void testRegistration() throws IOException, MessagingException, ServiceException {
        skipIfNotFixed(10);
        String user = "user6";
        String password = "password";
        String email = "user5@localhost.localdomain";
        app.registration().start(user, email);

        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);
        assertTrue(app.newSession().login(user, password));

    }


    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();

    }

}
