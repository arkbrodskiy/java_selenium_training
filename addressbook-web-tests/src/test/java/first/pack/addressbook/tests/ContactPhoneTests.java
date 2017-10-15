package first.pack.addressbook.tests;

import first.pack.addressbook.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase{

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
    public void testContactPhones(){
        app.goTo().homePage();
        ContactData contact = app.contact().takeAll().iterator().next();
        ContactData contactEditFormInfo = app.contact().takeEditFormInfo(contact);
        assertThat(contact.getHomePhone(), equalTo(cleaned(contactEditFormInfo.getHomePhone())));
        assertThat(contact.getMobilePhone(), equalTo(cleaned(contactEditFormInfo.getMobilePhone())));
        assertThat(contact.getOfficePhone(), equalTo(cleaned(contactEditFormInfo.getOfficePhone())));
    }

    public String cleaned(String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
