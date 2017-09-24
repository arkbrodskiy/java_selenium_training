package first.pack.addressbook.tests;

import first.pack.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase{
    @Test
    public void testContactCreation() {
        app.initContactCreation();
        app.fillContactForm(new ContactData("Сднемрождения", "Поздравит", "Инаверно", "Оставит", "Мне в подарок", "4687569820", "4753698561", "8965475258", "tpoi@lkweq.giu"));
        app.submitContactCreation();
        app.returnToHomePage();
    }

}
