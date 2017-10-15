package first.pack.addressbook.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(ApplicationManager app) {
        super(app);
        this.app = app;
        this.wd = app.wd;
    }

    public void groupPage() {
        click(By.linkText("groups"));
    }

    public void homePage() {
        click(By.linkText("home"));
    }
}
