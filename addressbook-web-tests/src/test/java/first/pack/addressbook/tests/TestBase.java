package first.pack.addressbook.tests;

import first.pack.addressbook.appmanager.ApplicationManager;
import first.pack.addressbook.model.ContactData;
import first.pack.addressbook.model.GroupData;
import org.openqa.selenium.remote.BrowserType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.Comparator;
import java.util.List;

public class TestBase {

    protected final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

    @BeforeMethod
    public void setUp() throws Exception {
        app.init();
    }

    @AfterMethod
    public void tearDown() {
        app.stop();
    }

    protected void assertEqualsGroupLists(List<GroupData> listBefore, List<GroupData> listAfter) {
        Comparator<? super GroupData> byValue = ((o1, o2) -> Integer.compare(o1.getValue(), o2.getValue()));
        listBefore.sort(byValue);
        listAfter.sort(byValue);
        Assert.assertEquals(listAfter, listBefore);
    }

    protected void assertEqualsContactLists(List<ContactData> listBefore, List<ContactData> listAfter) {
        Comparator<? super ContactData> byId = ((o1, o2) -> Integer.compare(o1.getId(), o2.getId()));
        listBefore.sort(byId);
        listAfter.sort(byId);
        Assert.assertEquals(listAfter, listBefore);
    }
}
