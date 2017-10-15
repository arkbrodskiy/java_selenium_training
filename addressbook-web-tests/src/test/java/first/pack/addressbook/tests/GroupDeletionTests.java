package first.pack.addressbook.tests;

import first.pack.addressbook.model.GroupData;
import first.pack.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().takeAll().size() == 0){
            app.group().create(new GroupData().withName("Group 01").withHeader("Group 01 header").withFooter("Group 01 footer"));
        }
    }

    @Test
    public void testGroupDeletion() {
        Groups before = app.group().takeAll();
        GroupData groupToDelete = before.iterator().next();
        app.group().delete(groupToDelete);
        assertThat(app.group().count(), equalTo(before.size() - 1));
        Groups after = app.group().takeAll();
        assertThat(after, equalTo(before.without(groupToDelete)));
    }


}
