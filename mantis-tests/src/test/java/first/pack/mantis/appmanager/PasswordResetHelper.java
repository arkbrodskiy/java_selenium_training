package first.pack.mantis.appmanager;

import first.pack.mantis.model.UserData;
import org.openqa.selenium.By;

import java.util.List;

public class PasswordResetHelper extends HelperBase{

    public PasswordResetHelper(ApplicationManager app) {
        super(app);
    }

    public UserData findUser(List<UserData> users) {
        UserData resultUser = new UserData();
        if (users.size() == 0) throw new Error("User list is empty :(");
        for (UserData user : users) {
            if (user.getUsername().contains("reset")) {
                resultUser = user;
                break;
            }
        }
        if (resultUser.getUsername() == null) throw new Error("There is no user for password reset :(");
        return resultUser;
    }

    public void init(UserData user) {
        app.goTo().usersPage();
        app.goTo().manageUser(user);
        click(By.cssSelector("[value = 'Reset Password']"));
    }

}
