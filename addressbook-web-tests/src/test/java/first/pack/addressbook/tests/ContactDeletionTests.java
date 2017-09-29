package first.pack.addressbook.tests;

import first.pack.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletion(){
        if (! app.getContactHelper().isItemPresent()){
            app.getContactHelper().createContact(new ContactData("Одеяла", "Иподушки", "Ждут", "Ребят", "Даже сказка", "5690236940", "2513690248", "1023854750", "vgh@kjhf.fkl"));
        }
        app.getContactHelper().selectItem();
        app.getContactHelper().deleteSelectedContacts();
        app.getContactHelper().dismissAlertConfirm();
        app.getNavigationHelper().gotoHomePage();
    }
}
