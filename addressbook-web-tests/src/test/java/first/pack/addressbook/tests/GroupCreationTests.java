package first.pack.addressbook.tests;

import first.pack.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GroupCreationTests extends TestBase{

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> listBefore = app.getGroupHelper().getGroupList();
        app.getGroupHelper().createGroup(new GroupData("Group 06", "Group 06 header", "Group 06 footer"));
        List<GroupData> listAfter = app.getGroupHelper().getGroupList();
        Assert.assertEquals(listAfter.size(), listBefore.size() + 1);
    }

}
