package first.pack.addressbook.tests;

import first.pack.addressbook.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInfoTests extends TestBase{

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
                    .withAddress("698546 Омск ул. Мира 3-59")
                    .withEmail("vgh@kjhf.fkl")
                    .withEmail2("ghjlg@iuhc.gvf")
                    .withEmail3("ijvnb@bvhkw.hb"));
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
