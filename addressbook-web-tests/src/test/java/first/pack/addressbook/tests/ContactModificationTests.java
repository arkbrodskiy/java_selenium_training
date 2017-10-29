package first.pack.addressbook.tests;

import first.pack.addressbook.generators.ContactDataGenerator;
import first.pack.addressbook.model.ContactData;
import first.pack.addressbook.model.Contacts;
import first.pack.addressbook.model.GroupData;
import first.pack.addressbook.model.Groups;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensureContactExists() {
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
    public void testContactModification() {
        ContactDataGenerator generator = new ContactDataGenerator();
        Contacts before = app.db().readContacts();
        ContactData contactToModify = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(contactToModify.getId())
                .withFirstName("EditedFirstname")
                .withLastName("EditedLastname")
                .withNickname("EditedNickname")
                .withTitle("EditedTitle")
                .withCompany("EditedCompany")
                .withHomePhone(generator.generateRandomPhone())
                .withMobilePhone(generator.generateRandomPhone())
                .withOfficePhone(generator.generateRandomPhone())
                .withAddress("")
                .withIm("")
                .withIm2("")
                .withIm3("")
                .withEmail(generator.generateRandomEmail())
                .withEmail2(generator.generateRandomEmail())
                .withEmail3(generator.generateRandomEmail());
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().readContacts();
        assertThat(after, equalTo(before.without(contactToModify).withAdded(contact)));
        verifyContactListInUI();
    }

    @Test
    public void testAddToGroup(){
        ContactData contactToAdd = app.db().readContacts().iterator().next();
        GroupData groupToAdd = app.contact().findGroupToAdd(contactToAdd);
        app.contact().addToGroup(contactToAdd, groupToAdd);
        Assert.assertTrue(app.contact().getById(contactToAdd.getId()).getGroups().contains(groupToAdd));
    }

    @Test
    public void testRemoveFromGroup(){
        ContactData contactToRemove = app.db().readContacts().iterator().next();
        GroupData groupToRemove = app.contact().findGroupToRemove(contactToRemove);
        app.contact().removeFromGroup(contactToRemove, groupToRemove);
        Assert.assertFalse(app.contact().getById(contactToRemove.getId()).getGroups().contains(groupToRemove));
    }



}
