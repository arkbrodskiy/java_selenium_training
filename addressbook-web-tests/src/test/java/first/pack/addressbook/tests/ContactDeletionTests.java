package first.pack.addressbook.tests;

import first.pack.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class ContactDeletionTests extends TestBase{

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
    public void testContactDeletion(){
        Set<ContactData> setBefore = app.contact().takeAll();
        ContactData contactToDelete = setBefore.iterator().next();
        app.contact().delete(contactToDelete);
        Set<ContactData> setAfter = app.contact().takeAll();
        setBefore.remove(contactToDelete);
        Assert.assertEquals(setAfter, setBefore);
    }


}
