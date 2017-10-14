package first.pack.addressbook.tests;

import first.pack.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isItemPresent()){
            app.getGroupHelper().createGroup(new GroupData("Group 01", "Group 01 header", "Group 01 footer"));
        }
    }

    @Test
    public void testGroupModification(){
        ensurePreconditions();
        List<GroupData> listBefore = app.getGroupHelper().getGroupList();
        int indexToModify = listBefore.size() - 1;
        GroupData group = new GroupData(listBefore.get(indexToModify).getValue(),"Edited group 01", "Edited group 01 header", "Edited group 01 footer");
        app.getGroupHelper().modifyGroup(indexToModify, group);
        List<GroupData> listAfter = app.getGroupHelper().getGroupList();
        Assert.assertEquals(listAfter.size(), listBefore.size());
        listBefore.remove(indexToModify);
        listBefore.add(group);
        app.getGroupHelper().assertEqualsGroupLists(listBefore, listAfter);
    }




}
