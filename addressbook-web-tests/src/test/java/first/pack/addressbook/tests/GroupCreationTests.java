package first.pack.addressbook.tests;

import first.pack.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase{

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        int amountBefore = app.getGroupHelper().getItemCount();
        app.getGroupHelper().createGroup(new GroupData("Group 06", "Group 06 header", "Group 06 footer"));
        int amountAfter = app.getGroupHelper().getItemCount();
        Assert.assertEquals(amountAfter, amountBefore + 1);
    }

}
