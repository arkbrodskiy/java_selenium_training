package first.pack.addressbook.tests;

import first.pack.addressbook.model.ContactData;
import first.pack.addressbook.model.Contacts;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTests extends TestBase{
    @Test
    public void testContactCreation() {
        Contacts before = app.contact().takeAll();
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
        Contacts after = app.contact().takeAll();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

}
