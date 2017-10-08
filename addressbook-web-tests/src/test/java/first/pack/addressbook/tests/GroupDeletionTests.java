package first.pack.addressbook.tests;

import first.pack.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isItemPresent()){
            app.getGroupHelper().createGroup(new GroupData("Group 01", "Group 01 header", "Group 01 footer"));
        }
        List<GroupData> listBefore = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectItem(listBefore.size() - 1);
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> listAfter = app.getGroupHelper().getGroupList();
        Assert.assertEquals(listAfter.size(), listBefore.size() - 1);
    }
}
