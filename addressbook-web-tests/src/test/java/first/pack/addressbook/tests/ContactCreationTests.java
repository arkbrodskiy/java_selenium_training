package first.pack.addressbook.tests;

import first.pack.addressbook.model.ContactData;
import first.pack.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase{
    @Test
    public void testContactCreation() {
        List<ContactData> listBefore = app.getContactHelper().getContactList();
        ContactData contact = new ContactData("Даже", "Сказка", "Спать", "Ложится", "Чтобы", "4369852147", "1652058741", "9696323258", "dnri@fhjdgt.so");
        app.getContactHelper().createContact(contact);
        List<ContactData> listAfter = app.getContactHelper().getContactList();
        Assert.assertEquals(listAfter.size(), listBefore.size() + 1);
        listBefore.add(contact);
        assertEqualsContactLists(listBefore, listAfter);
    }

}
