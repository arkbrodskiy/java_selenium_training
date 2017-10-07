package first.pack.addressbook.tests;

import first.pack.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase{
    @Test
    public void testContactCreation() {
        int amountBefore = app.getGroupHelper().getItemCount();
        app.getContactHelper().createContact(new ContactData("Одеяла", "Иподушки", "Ждут", "Ребят", "Даже сказка", "5690236940", "2513690248", "1023854750", "vgh@kjhf.fkl"));
        int amountAfter = app.getGroupHelper().getItemCount();
        Assert.assertEquals(amountAfter, amountBefore + 1);
    }

}
