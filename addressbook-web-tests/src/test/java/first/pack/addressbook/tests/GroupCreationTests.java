package first.pack.addressbook.tests;

import first.pack.addressbook.model.GroupData;
import first.pack.addressbook.model.Groups;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class GroupCreationTests extends TestBase{

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        Groups before = app.group().takeAll();
        GroupData group = new GroupData().withName("Group 07").withHeader("Group 07 header").withFooter("Group 07 footer");
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size() + 1));
        Groups after = app.group().takeAll();
        assertThat(after, equalTo(
                before.withAdded(group.withValue(after.stream().mapToInt((g) -> g.getValue()).max().getAsInt()))));
    }

    @Test
    public void testBadGroupCreation() {
        app.goTo().groupPage();
        Groups before = app.group().takeAll();
        GroupData group = new GroupData().withName("Group XX'").withHeader("Group XX header").withFooter("Group XX footer");
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.group().takeAll();
        assertThat(after, equalTo(before));
    }

}
