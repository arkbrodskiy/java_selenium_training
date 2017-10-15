package first.pack.addressbook.tests;

import first.pack.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class GroupCreationTests extends TestBase{

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        Set<GroupData> setBefore = app.group().takeAll();
        GroupData group = new GroupData().withName("Group 07").withHeader("Group 07 header").withFooter("Group 07 footer");
        app.group().create(group);
        Set<GroupData> setAfter = app.group().takeAll();
        Assert.assertEquals(setAfter.size(), setBefore.size() + 1);
        group.withValue(setAfter.stream().mapToInt((g) -> g.getValue()).max().getAsInt());
        setBefore.add(group);
        Assert.assertEquals(setAfter, setBefore);
    }

}
