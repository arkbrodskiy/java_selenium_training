package first.pack.addressbook.tests;

import first.pack.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().takeAll().size() == 0){
            app.group().create(new GroupData().withName("Group 01").withHeader("Group 01 header").withFooter("Group 01 footer"));
        }
    }

    @Test
    public void testGroupModification(){
        Set<GroupData> setBefore = app.group().takeAll();
        GroupData groupToModify = setBefore.iterator().next();
        GroupData group = new GroupData()
                .withValue(groupToModify.getValue())
                .withName("Edited group 01")
                .withHeader("Edited group 01 header")
                .withFooter("Edited group 01 footer");
        app.group().modify(group);
        Set<GroupData> setAfter = app.group().takeAll();
        Assert.assertEquals(setAfter.size(), setBefore.size());
        setBefore.remove(groupToModify);
        setBefore.add(group);
        Assert.assertEquals(setAfter, setBefore);
    }




}
