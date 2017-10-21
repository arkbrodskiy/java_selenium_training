package first.pack.addressbook.tests;

import first.pack.addressbook.generators.ContactDataGenerator;
import first.pack.addressbook.model.ContactData;
import first.pack.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactDeletionTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        ContactDataGenerator generator = new ContactDataGenerator();
        if (app.contact().takeAll().size() == 0){
            app.contact().create(new ContactData()
                    .withFirstName("Одеяла")
                    .withLastName("Иподушки")
                    .withNickname("Ждут")
                    .withTitle("Ребят")
                    .withCompany("Даже сказка")
                    .withHomePhone(generator.generateRandomPhone())
                    .withMobilePhone(generator.generateRandomPhone())
                    .withOfficePhone(generator.generateRandomPhone())
                    .withEmail(generator.generateRandomEmail()));
        }
    }

    @Test
    public void testContactDeletion(){
        Contacts before = app.contact().takeAll();
        ContactData contactToDelete = before.iterator().next();
        app.contact().delete(contactToDelete);
        assertThat(app.contact().count(), equalTo(before.size() - 1));
        Contacts after = app.contact().takeAll();
        assertThat(after, equalTo(before.without(contactToDelete)));
    }


}
