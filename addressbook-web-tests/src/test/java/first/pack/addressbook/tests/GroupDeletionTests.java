package first.pack.addressbook.tests;

import first.pack.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isItemPresent()){
            app.getGroupHelper().createGroup(new GroupData("Group 01", "Group 01 header", "Group 01 footer"));
        }
    }

    @Test
    public void testGroupDeletion() {
        ensurePreconditions();
        List<GroupData> listBefore = app.getGroupHelper().getGroupList();
        int indexToDelete = listBefore.size() - 1;
        app.getGroupHelper().deleteGroup(indexToDelete);
        List<GroupData> listAfter = app.getGroupHelper().getGroupList();
        Assert.assertEquals(listAfter.size(), listBefore.size() - 1);
        listBefore.remove(indexToDelete);
        Assert.assertEquals(listAfter, listBefore);
    }


}
