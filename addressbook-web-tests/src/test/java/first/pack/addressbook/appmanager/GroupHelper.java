package first.pack.addressbook.appmanager;

import first.pack.addressbook.model.GroupData;
import first.pack.addressbook.model.Groups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.*;

public class GroupHelper extends HelperBase {

    public GroupHelper(ApplicationManager app) {
        super(app);
        this.app = app;
        this.wd = app.wd;
    }

    private Groups groupCache = null;

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void deleteSelectedGroups() {
        click(By.name("delete"));
    }

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        groupCache = null;
        returnToGroupPage();
    }

    public void delete(GroupData group) {
        selectGroupByValue(group.getValue());
        deleteSelectedGroups();
        groupCache = null;
        returnToGroupPage();
    }

    private void selectGroupByValue(int value) {
        wd.findElement(By.cssSelector("input[value='" + value + "']")).click();
    }

    public void modify(GroupData group) {
        selectGroupByValue(group.getValue());
        initGroupModification();
        fillGroupForm(group);
        submitModification();
        groupCache = null;
        returnToGroupPage();
    }

    public Groups takeAll() {
        if (groupCache != null) return new Groups(groupCache);
        groupCache = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element: elements){
            int value = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String name = element.getText();
            groupCache.add(new GroupData().withValue(value).withName(name));
        }
        return new Groups(groupCache);
    }

    public void assertEqualLists(List<GroupData> listBefore, List<GroupData> listAfter) {
        Comparator<? super GroupData> byValue = ((o1, o2) -> Integer.compare(o1.getValue(), o2.getValue()));
        listBefore.sort(byValue);
        listAfter.sort(byValue);
        Assert.assertEquals(listAfter, listBefore);
    }



}
