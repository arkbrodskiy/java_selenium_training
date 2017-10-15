package first.pack.addressbook.tests;

import first.pack.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().createList().size() == 0){
            app.group().create(new GroupData().withName("Group 01").withHeader("Group 01 header").withFooter("Group 01 footer"));
        }
    }

    @Test
    public void testGroupDeletion() {
        List<GroupData> listBefore = app.group().createList();
        int indexToDelete = listBefore.size() - 1;
        app.group().delete(indexToDelete);
        List<GroupData> listAfter = app.group().createList();
        Assert.assertEquals(listAfter.size(), listBefore.size() - 1);
        listBefore.remove(indexToDelete);
        Assert.assertEquals(listAfter, listBefore);
    }


}
