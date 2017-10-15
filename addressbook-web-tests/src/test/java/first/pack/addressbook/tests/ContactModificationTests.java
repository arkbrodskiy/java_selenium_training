package first.pack.addressbook.tests;

import first.pack.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().createList().size() == 0){
            app.contact().create(new ContactData("Одеяла", "Иподушки", "Ждут", "Ребят", "Даже сказка", "5690236940", "2513690248", "1023854750", "vgh@kjhf.fkl"));
        }
    }

    @Test
    public void testContactModification() {
        List<ContactData> listBefore = app.contact().createList();
        int indexToModify = listBefore.size() - 1;
        ContactData contact = new ContactData(listBefore.get(indexToModify).getId(), "НьюОдеяла", "НьюИподушки", "НьюЖдут", "НьюРебят", "Нью Даже сказка", "0478903654", "0876902145", "0369148570", "newhgv@svmi.iiu");
        app.contact().modify(indexToModify, contact);
        List<ContactData> listAfter = app.contact().createList();
        Assert.assertEquals(listAfter.size(), listBefore.size());
        listBefore.remove(indexToModify);
        listBefore.add(contact);
        app.contact().assertEqualLists(listBefore, listAfter);
    }



}
