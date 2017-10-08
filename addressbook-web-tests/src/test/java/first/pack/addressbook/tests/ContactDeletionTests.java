package first.pack.addressbook.tests;

import first.pack.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletion(){
        if (! app.getContactHelper().isItemPresent()){
            app.getContactHelper().createContact(new ContactData("Одеяла", "Иподушки", "Ждут", "Ребят", "Даже сказка", "5690236940", "2513690248", "1023854750", "vgh@kjhf.fkl"));
        }
        List<ContactData> listBefore = app.getContactHelper().getContactList();
        app.getContactHelper().selectItem(listBefore.size() - 1);
        app.getContactHelper().deleteSelectedContacts();
        app.getContactHelper().dismissAlertConfirm();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> listAfter = app.getContactHelper().getContactList();
        Assert.assertEquals(listAfter.size(), listBefore.size() - 1);
    }
}
