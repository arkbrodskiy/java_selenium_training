package first.pack.addressbook.appmanager;

import first.pack.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GroupHelper extends HelperBase {

    public GroupHelper(ApplicationManager app) {
        super(app);
        this.app = app;
        this.wd = app.wd;
    }

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

    public void createGroup(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupPage();
    }

    public void deleteGroup(int index) {
        selectItem(index);
        deleteSelectedGroups();
        returnToGroupPage();
    }

    public void modifyGroup(int index, GroupData group) {
        selectItem(index);
        initGroupModification();
        fillGroupForm(group);
        submitModification();
        returnToGroupPage();
    }

    public List<GroupData> getGroupList() {
        List<GroupData> groupList = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element: elements){
            int value = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String name = element.getText();
            GroupData group = new GroupData(value, name, null, null);
            groupList.add(group);
        }
        return groupList;
    }

    public void assertEqualsGroupLists(List<GroupData> listBefore, List<GroupData> listAfter) {
        Comparator<? super GroupData> byValue = ((o1, o2) -> Integer.compare(o1.getValue(), o2.getValue()));
        listBefore.sort(byValue);
        listAfter.sort(byValue);
        Assert.assertEquals(listAfter, listBefore);
    }

}
