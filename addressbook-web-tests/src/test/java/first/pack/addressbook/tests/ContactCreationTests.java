package first.pack.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import first.pack.addressbook.model.ContactData;
import first.pack.addressbook.model.Contacts;
import first.pack.addressbook.model.GroupData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTests extends TestBase{

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))){
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
            return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validContacts")
    public void testContactCreation(ContactData contact) {
        Contacts before = app.contact().takeAll();
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
