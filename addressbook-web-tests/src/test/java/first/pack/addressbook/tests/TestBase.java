package first.pack.addressbook.tests;

import first.pack.addressbook.appmanager.ApplicationManager;
import first.pack.addressbook.model.ContactData;
import first.pack.addressbook.model.Contacts;
import first.pack.addressbook.model.GroupData;
import first.pack.addressbook.model.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import sun.font.CoreMetrics;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));
    Logger logger = LoggerFactory.getLogger(GroupCreationTests.class);

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

    @BeforeMethod
    public void logTestStart(Method m, Object[] p){
        logger.info("Start test " + m.getName() + " with parameters " + Arrays.asList(p));
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m){
        logger.info("Stop test " + m.getName());
    }

    void verifyGroupListInUI() {
        if (Boolean.getBoolean("verifyUI")){
            Groups dbGroups = app.db().readGroups();
            Groups uiGroups = app.group().takeAll();
            assertThat(dbGroups.stream().map((g) -> new GroupData()
                    .withValue(g.getValue()).withName(g.getName())).collect(Collectors.toSet()), equalTo(uiGroups));

        }
    }

    void verifyContactListInUI() {
        if (Boolean.getBoolean("verifyUI")){
            Contacts dbContacts = app.db().readContacts();
            Contacts uiContacts = app.contact().takeAll();
            assertThat(dbContacts.stream().map((c) -> new ContactData()
                    .withId(c.getId()).withFirstName(c.getFirstName()).withLastName(c.getLastName())).collect(Collectors.toSet()), equalTo(uiContacts.stream().map((c) -> new ContactData()
                    .withId(c.getId()).withFirstName(c.getFirstName()).withLastName(c.getLastName())).collect(Collectors.toSet())));

        }
    }

}
