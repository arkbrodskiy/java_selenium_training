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
        if (app.db().readContacts().size() == 0){
            app.contact().create(new ContactData()
                    .withFirstName("JustFirstname")
                    .withLastName("JustLastname")
                    .withNickname("JustNickname")
                    .withTitle("JustTitle")
                    .withCompany("JustCompany")
                    .withAddress("")
                    .withIm("")
                    .withIm2("")
                    .withIm3("")
                    .withHomePhone(generator.generateRandomPhone())
                    .withMobilePhone(generator.generateRandomPhone())
                    .withOfficePhone(generator.generateRandomPhone())
                    .withEmail(generator.generateRandomEmail())
                    .withEmail2(generator.generateRandomEmail())
                    .withEmail3(generator.generateRandomEmail()));
        }
    }

    @Test
    public void testContactDeletion(){
        Contacts before = app.db().readContacts();
        ContactData contactToDelete = before.iterator().next();
        app.contact().delete(contactToDelete);
        assertThat(app.contact().count(), equalTo(before.size() - 1));
        Contacts after = app.db().readContacts();
        assertThat(after, equalTo(before.without(contactToDelete)));
        verifyContactListInUI();
    }


}
