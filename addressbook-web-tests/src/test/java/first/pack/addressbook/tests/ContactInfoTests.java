package first.pack.addressbook.tests;

import first.pack.addressbook.generators.ContactDataGenerator;
import first.pack.addressbook.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInfoTests extends TestBase{

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
                    .withAddress("698546 Омск ул. Мира 3-59")
                    .withEmail(generator.generateRandomEmail())
                    .withEmail2(generator.generateRandomEmail())
                    .withEmail3(generator.generateRandomEmail()));
        }
    }

    @Test
    public void testContactInfo(){
        app.goTo().homePage();
        ContactData contact = app.contact().takeAll().iterator().next();
        ContactData contactEditFormInfo = app.contact().takeEditFormInfo(contact);
        assertThat(contact.getAllPhones(), equalTo(app.contact().mergePhones(contactEditFormInfo)));
        assertThat(contact.getAddress(), equalTo(contactEditFormInfo.getAddress()));
        assertThat(contact.getAllEmails(), equalTo(app.contact().mergeEmails(contactEditFormInfo)));
    }

}
