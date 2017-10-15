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
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().takeAll();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

    @Test
    public void testBadContactCreation() {
        Contacts before = app.contact().takeAll();
        ContactData contact = new ContactData()
                .withFirstName("XXДаже'")
                .withLastName("XXСказка'")
                .withNickname("Спать")
                .withTitle("Ложится")
                .withCompany("Чтобы")
                .withHomePhone("4369852147")
                .withMobilePhone("1652058741")
                .withOfficePhone("9696323258")
                .withEmail("dnri@fhjdgt.so");
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().takeAll();
        assertThat(after, equalTo(before));
    }

}
