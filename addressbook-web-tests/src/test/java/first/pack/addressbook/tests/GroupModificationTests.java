package first.pack.addressbook.tests;

import first.pack.addressbook.model.GroupData;
import first.pack.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

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
        Groups before = app.group().takeAll();
        GroupData groupToModify = before.iterator().next();
        GroupData group = new GroupData()
                .withValue(groupToModify.getValue())
                .withName("Edited group 01")
                .withHeader("Edited group 01 header")
                .withFooter("Edited group 01 footer");
        app.group().modify(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.group().takeAll();
        assertThat(after, equalTo(before.without(groupToModify).withAdded(group)));
    }




}
