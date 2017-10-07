package first.pack.addressbook.tests;

import first.pack.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase{
    @Test
    public void testContactCreation() {
        int amountBefore = app.getGroupHelper().getItemCount();
        app.getContactHelper().createContact(new ContactData("Даже", "Сказка", "Спать", "Ложится", "Чтобы", "4369852147", "1652058741", "9696323258", "dnri@fhjdgt.so"));
        int amountAfter = app.getGroupHelper().getItemCount();
        Assert.assertEquals(amountAfter, amountBefore + 1);
    }

}
