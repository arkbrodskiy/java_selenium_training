package first.pack.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletion(){
        app.getContactHelper().selectItem();
        app.getContactHelper().deleteSelectedContacts();
        app.getContactHelper().dismissAlertConfirm();
        app.getNavigationHelper().gotoHomePage();
    }
}
