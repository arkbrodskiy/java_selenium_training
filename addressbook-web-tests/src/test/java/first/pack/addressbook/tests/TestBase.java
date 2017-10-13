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

}
