package first.pack.addressbook.tests;

import first.pack.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletionTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().createList().size() == 0){
            app.contact().create(new ContactData("Одеяла", "Иподушки", "Ждут", "Ребят", "Даже сказка", "5690236940", "2513690248", "1023854750", "vgh@kjhf.fkl"));
        }
    }

    @Test
    public void testContactDeletion(){
        List<ContactData> listBefore = app.contact().createList();
        int indexToDelete = listBefore.size() - 1;
        app.contact().delete(indexToDelete);
        List<ContactData> listAfter = app.contact().createList();
        listBefore.remove(indexToDelete);
        Assert.assertEquals(listAfter, listBefore);
    }


}
