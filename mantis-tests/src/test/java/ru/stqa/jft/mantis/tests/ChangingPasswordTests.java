package ru.stqa.jft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.jft.mantis.appmanager.HttpSession;
import ru.stqa.jft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by Anna on 2017-01-28.
 */
public class ChangingPasswordTests extends TestBase{

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testChangePassword() throws IOException, MessagingException {
        app.admin().login("administrator", "root");
        app.admin().goToSettings();
        app.admin().goToManageUsers();
        app.admin().chooseUser();
        app.admin().resetPassword();
        String email = app.admin().getMail();
        String username = app.admin().getUserName();


        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        String password = "password1";
        app.admin().changePassword(confirmationLink, password);

        assertTrue(app.newSession().login(username, password));

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
