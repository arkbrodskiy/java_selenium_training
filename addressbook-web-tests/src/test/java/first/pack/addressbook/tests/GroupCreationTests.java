package first.pack.addressbook.tests;

import first.pack.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase{

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> listBefore = app.getGroupHelper().getGroupList();
        GroupData group = new GroupData("Group 06", "Group 06 header", "Group 06 footer");
        app.getGroupHelper().createGroup(group);
        List<GroupData> listAfter = app.getGroupHelper().getGroupList();
        Assert.assertEquals(listAfter.size(), listBefore.size() + 1);
        listBefore.add(group);
        assertEqualsGroupLists(listBefore, listAfter);
    }

}
