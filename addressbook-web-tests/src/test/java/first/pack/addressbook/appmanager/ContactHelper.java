package first.pack.addressbook.appmanager;

import first.pack.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager app) {
        super(app);
        this.app = app;
        this.wd = app.wd;
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

    public void create(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public void delete(int index) {
        selectItem(index);
        deleteSelectedContacts();
        dismissAlertConfirm();
        app.goTo().gotoHomePage();
    }

    public void modify(int index, ContactData contact) {
        initContactModification(index);
        fillContactForm(contact);
        submitModification();
        returnToHomePage();
    }

    public List<ContactData> createList() {
        List<ContactData> contactList = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element: elements){
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            String firstName = element.findElement(By.cssSelector("td:nth-of-type(3)")).getText();
            String lastName = element.findElement(By.cssSelector("td:nth-of-type(2)")).getText();
            ContactData contact = new ContactData(id, firstName, lastName, null, null, null, null, null, null, null);
            contactList.add(contact);
        }
        return contactList;
    }

    public void assertEqualLists(List<ContactData> listBefore, List<ContactData> listAfter) {
        Comparator<? super ContactData> byId = ((o1, o2) -> Integer.compare(o1.getId(), o2.getId()));
        listBefore.sort(byId);
        listAfter.sort(byId);
        Assert.assertEquals(listAfter, listBefore);
    }

}
