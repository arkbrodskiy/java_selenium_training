package first.pack.addressbook.tests;

import first.pack.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().createList().size() == 0){
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
        List<ContactData> listBefore = app.contact().createList();
        int indexToModify = listBefore.size() - 1;
        ContactData contact = new ContactData()
                .withId(listBefore.get(indexToModify).getId())
                .withFirstName("НьюОдеяла")
                .withLastName("НьюИподушки")
                .withNickname("НьюЖдут")
                .withTitle("НьюРебят")
                .withCompany("Нью Даже сказка")
                .withHomePhone("0478903654")
                .withMobilePhone("0876902145")
                .withOfficePhone("0369148570")
                .withEmail("newhgv@svmi.iiu");
        app.contact().modify(indexToModify, contact);
        List<ContactData> listAfter = app.contact().createList();
        Assert.assertEquals(listAfter.size(), listBefore.size());
        listBefore.remove(indexToModify);
        listBefore.add(contact);
        app.contact().assertEqualLists(listBefore, listAfter);
    }



}
