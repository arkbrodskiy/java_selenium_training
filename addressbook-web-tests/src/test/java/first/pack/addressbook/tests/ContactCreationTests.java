package first.pack.addressbook.tests;

import first.pack.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase{
    @Test
    public void testContactCreation() {
        app.getContactHelper().createContact(new ContactData("Одеяла", "Иподушки", "Ждут", "Ребят", "Даже сказка", "5690236940", "2513690248", "1023854750", "vgh@kjhf.fkl"));
    }

}
