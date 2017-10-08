package first.pack.addressbook.tests;

import first.pack.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        if (! app.getContactHelper().isItemPresent()){
            app.getContactHelper().createContact(new ContactData("Одеяла", "Иподушки", "Ждут", "Ребят", "Даже сказка", "5690236940", "2513690248", "1023854750", "vgh@kjhf.fkl"));
        }
        List<ContactData> listBefore = app.getContactHelper().getContactList();
        int indexToModify = listBefore.size() - 1;
        ContactData contact = new ContactData(listBefore.get(indexToModify).getId(), "НьюОдеяла", "НьюИподушки", "НьюЖдут", "НьюРебят", "Нью Даже сказка", "0478903654", "0876902145", "0369148570", "newhgv@svmi.iiu");
        app.getContactHelper().initContactModification(indexToModify);
        app.getContactHelper().fillContactForm(contact);
        app.getContactHelper().submitModification();
        app.getContactHelper().returnToHomePage();
        List<ContactData> listAfter = app.getContactHelper().getContactList();
        Assert.assertEquals(listAfter.size(), listBefore.size());
        listBefore.remove(indexToModify);
        listBefore.add(contact);
        Comparator<? super ContactData> byId = ((o1, o2) -> Integer.compare(o1.getId(), o2.getId()));
        listBefore.sort(byId);
        listAfter.sort(byId);
        Assert.assertEquals(listAfter, listBefore);
    }
}
