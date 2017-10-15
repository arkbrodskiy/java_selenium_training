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
        Groups after = app.group().takeAll();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(group.withValue(after.stream().mapToInt((g) -> g.getValue()).max().getAsInt()))));
    }

}
