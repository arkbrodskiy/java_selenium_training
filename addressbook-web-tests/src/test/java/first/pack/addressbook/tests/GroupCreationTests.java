package first.pack.addressbook.tests;

import first.pack.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase{

    @Test
    public void testGroupCreation() {
        app.gotoGroupPage();
        app.initGroupCreation();
        app.fillGroupForm(new GroupData("Group 04", "Group 04 header", "Group 04 footer"));
        app.submitGroupCreation();
        app.returnToGroupPage();
    }

}
