package first.pack.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase{

    @Test
    public void testGroupCreation() {
        gotoGroupPage();
        initGroupCreation();
        fillGroupForm(new GroupData("Group 04", "Group 04 header", "Group 04 footer"));
        submitGroupCreation();
        returnToGroupPage();
    }

}
