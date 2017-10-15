package first.pack.addressbook.tests;

import first.pack.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ContactCreationTests extends TestBase{
    @Test
    public void testContactCreation() {
        List<ContactData> listBefore = app.contact().createList();
        ContactData contact = new ContactData("Даже", "Сказка", "Спать", "Ложится", "Чтобы", "4369852147", "1652058741", "9696323258", "dnri@fhjdgt.so");
        app.contact().create(contact);
        List<ContactData> listAfter = app.contact().createList();
        Assert.assertEquals(listAfter.size(), listBefore.size() + 1);
        listBefore.add(contact);
        app.contact().assertEqualLists(listBefore, listAfter);
    }

}
