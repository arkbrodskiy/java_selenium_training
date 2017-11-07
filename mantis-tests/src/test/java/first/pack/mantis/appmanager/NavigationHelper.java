package first.pack.mantis.appmanager;

import first.pack.mantis.model.UserData;
import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void usersPage() {
        click(By.cssSelector("[href='manage_user_create_page.php']"));
        click(By.cssSelector("[href='/mantisbt-2.8.0/manage_user_page.php']"));
    }

    public void manageUser(UserData user) {
        type(By.cssSelector("input[id='username']"), user.getUsername());
        click(By.cssSelector("[value = 'Manage User']"));
    }
}
