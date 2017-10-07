package first.pack.addressbook.tests;

import first.pack.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification(){
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isItemPresent()){
            app.getGroupHelper().createGroup(new GroupData("Group 01", "Group 01 header", "Group 01 footer"));
        }
        int amountBefore = app.getGroupHelper().getItemCount();
        app.getGroupHelper().selectItem(amountBefore - 1);
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("Edited group 01", "Edited group 01 header", "Edited group 01 footer"));
        app.getGroupHelper().submitModification();
        app.getGroupHelper().returnToGroupPage();
        int amountAfter = app.getGroupHelper().getItemCount();
        Assert.assertEquals(amountAfter, amountBefore);
    }
}
