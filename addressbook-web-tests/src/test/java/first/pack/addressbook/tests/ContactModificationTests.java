package first.pack.addressbook.tests;

import first.pack.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        if (! app.getContactHelper().isItemPresent()){
            app.getContactHelper().createContact(new ContactData("Одеяла", "Иподушки", "Ждут", "Ребят", "Даже сказка", "5690236940", "2513690248", "1023854750", "vgh@kjhf.fkl"));
        }
        int amountBefore = app.getGroupHelper().getItemCount();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("НьюОдеяла", "НьюИподушки", "НьюЖдут", "НьюРебят", "Нью Даже сказка", "0478903654", "0876902145", "0369148570", "newhgv@svmi.iiu"));
        app.getContactHelper().submitModification();
        app.getContactHelper().returnToHomePage();
        int amountAfter = app.getGroupHelper().getItemCount();
        Assert.assertEquals(amountAfter, amountBefore);
    }
}
