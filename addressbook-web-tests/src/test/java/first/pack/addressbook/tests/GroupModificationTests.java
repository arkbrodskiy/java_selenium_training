package first.pack.addressbook.tests;

import first.pack.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification(){
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("Edited group 01", "Edited group 01 header", "Edited group 06 footer"));
        app.getGroupHelper().submitModification();
        app.getGroupHelper().returnToGroupPage();
    }
}
