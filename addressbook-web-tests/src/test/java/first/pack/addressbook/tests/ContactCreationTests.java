package first.pack.addressbook.tests;

import first.pack.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ContactCreationTests extends TestBase{
    @Test
    public void testContactCreation() {
        List<ContactData> listBefore = app.contact().createList();
        ContactData contact = new ContactData()
                .withFirstName("Даже")
                .withLastName("Сказка")
                .withNickname("Спать")
                .withTitle("Ложится")
                .withCompany("Чтобы")
                .withHomePhone("4369852147")
                .withMobilePhone("1652058741")
                .withOfficePhone("9696323258")
                .withEmail("dnri@fhjdgt.so");
        app.contact().create(contact);
        List<ContactData> listAfter = app.contact().createList();
        Assert.assertEquals(listAfter.size(), listBefore.size() + 1);
        listBefore.add(contact);
        app.contact().assertEqualLists(listBefore, listAfter);
    }

}
