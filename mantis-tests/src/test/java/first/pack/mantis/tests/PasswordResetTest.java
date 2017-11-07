package first.pack.mantis.tests;

import first.pack.mantis.model.MailMessage;
import first.pack.mantis.model.UserData;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class PasswordResetTest extends TestBase{


    @Test
    public void testPasswordReset() throws MessagingException, IOException {
        String serverPass = "pass001";
        String newPass = "pass003";
        List<UserData> users = app.db().readUsers();
        UserData userToReset = app.passwordReset().findUser(users);
        String username = userToReset.getUsername();
        String email = userToReset.getEmail();
        app.session().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        app.james().enableTelnet();
        app.james().drainEmail(username, serverPass);
        app.passwordReset().init(userToReset);
        List<MailMessage> mailMessages = app.james().waitForMail(username, serverPass, 60000);
        String confirmationLink = app.passwordReset().findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, newPass);
        assertTrue(app.newSession().login(username, newPass));
        app.session().login(username, newPass);
    }
}
