package first.pack.addressbook.appmanager;

import first.pack.addressbook.model.ContactData;
import first.pack.addressbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.*;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager app) {
        super(app);
        this.app = app;
        this.wd = app.wd;
    }

    private Contacts contactCache = null;

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

    public void deleteSelectedContacts() {
        click(By.cssSelector("[value='Delete']"));
    }

    public void dismissAlertConfirm() {
        wd.switchTo().alert().accept();
    }

    public void create(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        contactCache = null;
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContacts();
        dismissAlertConfirm();
        contactCache = null;
        app.goTo().homePage();
    }

    private void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact);
        submitModification();
        contactCache = null;
        returnToHomePage();
    }

    private void initContactModificationById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    public Contacts takeAll() {
        if (contactCache != null) return new Contacts(contactCache);
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element: elements){
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            String firstName = element.findElement(By.cssSelector("td:nth-of-type(3)")).getText();
            String lastName = element.findElement(By.cssSelector("td:nth-of-type(2)")).getText();
            String[] phones = element.findElement(By.cssSelector("td:nth-of-type(6)")).getText().split("\n");
            contactCache.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
                    .withHomePhone(phones[0]).withMobilePhone(phones[1]).withOfficePhone(phones[2]));
        }
        return new Contacts(contactCache);
    }

    public void assertEqualLists(List<ContactData> listBefore, List<ContactData> listAfter) {
        Comparator<? super ContactData> byId = ((o1, o2) -> Integer.compare(o1.getId(), o2.getId()));
        listBefore.sort(byId);
        listAfter.sort(byId);
        Assert.assertEquals(listAfter, listBefore);
    }

    public ContactData takeEditFormInfo(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String office = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname).withHomePhone(home).withMobilePhone(mobile).withOfficePhone(office);
    }
}
