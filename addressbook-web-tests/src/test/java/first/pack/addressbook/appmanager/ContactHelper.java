package first.pack.addressbook.appmanager;

import first.pack.addressbook.model.ContactData;
import first.pack.addressbook.model.Contacts;
import first.pack.addressbook.model.GroupData;
import first.pack.addressbook.model.Groups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getOfficePhone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
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
            String address = element.findElement(By.cssSelector("td:nth-of-type(4)")).getText();
            String allEmails = element.findElement(By.cssSelector("td:nth-of-type(5)")).getText();
            String allPhones = element.findElement(By.cssSelector("td:nth-of-type(6)")).getText();
            contactCache.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName).withAddress(address).withAllEmails(allEmails).withAllPhones(allPhones));
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
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String office = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
                .withAddress(address).withHomePhone(home).withMobilePhone(mobile).withOfficePhone(office)
                .withEmail(email).withEmail2(email2).withEmail3(email3);
    }

    public String mergePhones(ContactData contact) {
        return Stream.of(contact.getHomePhone(), contact.getMobilePhone(), contact.getOfficePhone()).filter((s) -> ! s.equals(""))
                .map(ContactHelper::cleaned).collect(Collectors.joining("\n"));
    }

    private static String cleaned(String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }

    public String mergeEmails(ContactData contact) {
        return Stream.of(contact.getEmail(), contact.getEmail2(), contact.getEmail3()).filter((s) -> ! s.equals("")).collect(Collectors.joining("\n"));
    }

    public void addToGroup(ContactData contact, GroupData group) {
        selectContactById(contact.getId());
        selectGroupByValue(By.name("to_group"), group);
        click(By.name("add"));
        app.goTo().homePage();
    }

    private void selectGroupByValue(By menuLocator, GroupData group) {
        Select dropdown = new Select(wd.findElement(menuLocator));
        dropdown.selectByValue(String.valueOf(group.getValue()));
    }

    public ContactData getById(int id) {
        Contacts contactList = app.db().readContacts();
        ContactData result = new ContactData();
        for (ContactData contact: contactList){
            if (contact.getId() == id) {
                result = contact;
                break;
            }
        }
        return result;
    }

    public void removeFromGroup(ContactData contact, GroupData group) {
        selectGroupByValue(By.cssSelector("select[name='group']"), group);
        selectContactById(contact.getId());
        click(By.name("remove"));
        app.goTo().homePage();
    }

    public GroupData findFreeGroup(Groups groupsWithContact) {
        Groups groupList = app.db().readGroups();
        GroupData group = new GroupData();
        for(GroupData groupInList: groupList){
            if (!groupsWithContact.contains(groupInList)){
                group = groupInList;
                break;
            }
        }
        return group;
    }

    public void ensureFreeGroupExists(Groups groups) {
        if (groups.size() == app.db().readGroups().size()){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Group 01").withHeader("Group 01 header").withFooter("Group 01 footer"));
            app.goTo().homePage();
        }
    }

    public GroupData findGroupToRemove(ContactData contact) {
        Groups groupsWithContact = contact.getGroups();
        ensureGroupExists();
        return findAssociatedGroup(contact, groupsWithContact);
    }

    private GroupData findAssociatedGroup(ContactData contact, Groups groupsWithContact) {
        GroupData result;
        if (groupsWithContact.size() == 0) {
            result = app.db().readGroups().iterator().next();
            app.contact().addToGroup(contact, result);
        }
        else result = groupsWithContact.iterator().next();
        return result;
    }

    private void ensureGroupExists() {
        if (app.db().readGroups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Group 01").withHeader("Group 01 header").withFooter("Group 01 footer"));
            app.goTo().homePage();
        }
    }
}
