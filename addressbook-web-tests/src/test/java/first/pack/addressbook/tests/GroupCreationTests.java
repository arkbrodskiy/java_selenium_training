package first.pack.addressbook.tests;

import first.pack.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GroupCreationTests extends TestBase{

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        List<GroupData> listBefore = app.group().createList();
        GroupData group = new GroupData().withName("Group 07").withHeader("Group 07 header").withFooter("Group 07 footer");
        app.group().create(group);
        List<GroupData> listAfter = app.group().createList();
        Assert.assertEquals(listAfter.size(), listBefore.size() + 1);
        listBefore.add(group);
        app.group().assertEqualLists(listBefore, listAfter);
    }

}
