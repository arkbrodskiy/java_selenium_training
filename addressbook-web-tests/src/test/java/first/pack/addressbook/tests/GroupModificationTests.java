package first.pack.addressbook.tests;

import first.pack.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification(){
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isItemPresent()){
            app.getGroupHelper().createGroup(new GroupData("Group 01", "Group 01 header", "Group 01 footer"));
        }
        List<GroupData> listBefore = app.getGroupHelper().getGroupList();
        int indexToModify = listBefore.size() - 1;
        GroupData group = new GroupData(listBefore.get(indexToModify).getValue(),"Edited group 01", "Edited group 01 header", "Edited group 01 footer");
        app.getGroupHelper().selectItem(indexToModify);
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().submitModification();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> listAfter = app.getGroupHelper().getGroupList();
        Assert.assertEquals(listAfter.size(), listBefore.size());
        listBefore.remove(indexToModify);
        listBefore.add(group);
        Comparator<? super GroupData> byValue = ((o1, o2) -> Integer.compare(o1.getValue(), o2.getValue()));
        listBefore.sort(byValue);
        listAfter.sort(byValue);
        Assert.assertEquals(listAfter, listBefore);
    }
}
