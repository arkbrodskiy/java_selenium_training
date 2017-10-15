package first.pack.addressbook.tests;

import first.pack.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

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
        Set<GroupData> setBefore = app.group().takeAll();
        GroupData groupToDelete = setBefore.iterator().next();
        app.group().delete(groupToDelete);
        Set<GroupData> setAfter = app.group().takeAll();
        Assert.assertEquals(setAfter.size(), setBefore.size() - 1);
        setBefore.remove(groupToDelete);
        Assert.assertEquals(setAfter, setBefore);
    }


}
