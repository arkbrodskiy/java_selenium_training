package first.pack.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HelperBase {
    protected WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
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

    public Boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException e){
            return false;
        }
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public void submitModification() {
        click(By.name("update"));
    }

    public void selectItem() {
        click(By.name("selected[]"));
    }

    public boolean isItemPresent() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getItemCount() {
        return wd.findElements(By.name("selected[]")).size();
    }
}
