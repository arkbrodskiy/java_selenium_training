package first.pack.addressbook.tests;

import first.pack.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class ContactCreationTests extends TestBase{
    @Test
    public void testContactCreation() {
        Set<ContactData> setBefore = app.contact().takeAll();
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
        Set<ContactData> setAfter = app.contact().takeAll();
        Assert.assertEquals(setAfter.size(), setBefore.size() + 1);
        contact.withId(setAfter.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        setBefore.add(contact);
        Assert.assertEquals(setAfter, setBefore);
    }

}
