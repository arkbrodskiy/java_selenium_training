package first.pack.addressbook.tests;

import first.pack.addressbook.model.ContactData;
import first.pack.addressbook.model.Contacts;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().takeAll().size() == 0){
            app.contact().create(new ContactData()
                    .withFirstName("Одеяла")
                    .withLastName("Иподушки")
                    .withNickname("Ждут")
                    .withTitle("Ребят")
                    .withCompany("Даже сказка")
                    .withHomePhone("5690236940")
                    .withMobilePhone("2513690248")
                    .withOfficePhone("1023854750")
                    .withEmail("vgh@kjhf.fkl"));
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.contact().takeAll();
        ContactData contactToModify = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(contactToModify.getId())
                .withFirstName("НьюОдеяла")
                .withLastName("НьюИподушки")
                .withNickname("НьюЖдут")
                .withTitle("НьюРебят")
                .withCompany("Нью Даже сказка")
                .withHomePhone("0478903654")
                .withMobilePhone("0876902145")
                .withOfficePhone("0369148570")
                .withEmail("newhgv@svmi.iiu");
        app.contact().modify(contact);
        Contacts after = app.contact().takeAll();
        assertThat(after.size(), equalTo(before.size()));
        assertThat(after, equalTo(before.without(contactToModify).withAdded(contact)));
    }



}
