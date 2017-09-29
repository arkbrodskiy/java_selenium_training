package first.pack.addressbook.tests;

import first.pack.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase{

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().createGroup(new GroupData("Group 06", "Group 06 header", "Group 06 footer"));
    }

}
