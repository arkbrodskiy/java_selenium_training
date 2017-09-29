package first.pack.addressbook.tests;

import first.pack.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isItemPresent()){
            app.getGroupHelper().createGroup(new GroupData("Group 01", "Group 01 header", "Group 01 footer"));
        }
        app.getGroupHelper().selectItem();
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
    }
}
