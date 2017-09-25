package first.pack.addressbook.tests;

import first.pack.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("НьюОдеяла", "НьюИподушки", "НьюЖдут", "НьюРебят", "Нью Даже сказка", "0478903654", "0876902145", "0369148570", "newhgv@svmi.iiu"));
        app.getContactHelper().submitModification();
        app.getContactHelper().returnToHomePage();
    }
}
