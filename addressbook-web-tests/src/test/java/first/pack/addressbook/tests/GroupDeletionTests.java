package first.pack.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectItem();
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
    }
}
