package first.pack.addressbook.appmanager;

import first.pack.addressbook.model.ContactData;
import first.pack.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getOfficePhone());
        type(By.name("email"), contactData.getEmail());
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void initContactModification(int index) {
        wd.findElements(By.cssSelector("[name='entry'] td:nth-of-type(8) a")).get(index).click();
    }

    public void deleteSelectedContacts() {
        click(By.cssSelector("[value='Delete']"));
    }

    public void dismissAlertConfirm() {
        wd.switchTo().alert().accept();
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contactList = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element: elements){
            String firstName = element.findElement(By.cssSelector("td:nth-of-type(3)")).getText();
            String lastName = element.findElement(By.cssSelector("td:nth-of-type(2)")).getText();
            ContactData contact = new ContactData(firstName, lastName, null, null, null, null, null, null, null);
            contactList.add(contact);
        }
        return contactList;
    }
}
