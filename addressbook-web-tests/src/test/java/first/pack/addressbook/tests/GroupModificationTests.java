package first.pack.addressbook.tests;

import first.pack.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().createList().size() == 0){
            app.group().create(new GroupData("Group 01", "Group 01 header", "Group 01 footer"));
        }
    }

    @Test
    public void testGroupModification(){
        List<GroupData> listBefore = app.group().createList();
        int indexToModify = listBefore.size() - 1;
        GroupData group = new GroupData(listBefore.get(indexToModify).getValue(),"Edited group 01", "Edited group 01 header", "Edited group 01 footer");
        app.group().modify(indexToModify, group);
        List<GroupData> listAfter = app.group().createList();
        Assert.assertEquals(listAfter.size(), listBefore.size());
        listBefore.remove(indexToModify);
        listBefore.add(group);
        app.group().assertEqualLists(listBefore, listAfter);
    }




}
