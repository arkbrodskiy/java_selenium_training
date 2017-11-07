package first.pack.mantis.appmanager;

import org.openqa.selenium.By;
import org.testng.Assert;

public class SessionHelper extends HelperBase {

    public SessionHelper(ApplicationManager app) {
        super(app);
    }

    public void login(String username, String password) {
        type(By.name("username"), username);
        click(By.cssSelector("[type='submit']"));
        type(By.name("password"), password);
        click(By.cssSelector("[type='submit']"));
        Assert.assertTrue(wd.findElement(By.cssSelector(".breadcrumb a")).getText().equals(username));
    }
}
