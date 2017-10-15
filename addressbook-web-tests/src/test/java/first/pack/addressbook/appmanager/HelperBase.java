package first.pack.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperBase {
    protected WebDriver wd;
    protected ApplicationManager app;

    public HelperBase(ApplicationManager app) {
        this.app = app;
        this.wd = app.wd;
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        if (text != null){
            String existingText = wd.findElement(locator).getAttribute("value");
            if (! text.equals(existingText)){
                click(locator);
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    public void submitModification() {
        click(By.name("update"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }
}
