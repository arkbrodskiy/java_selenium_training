package first.pack.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import first.pack.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    public static void main (String args[]) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jc = JCommander.newBuilder().addObject(generator).build();
        jc.parse(args);
        generator.run();

    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        saveAsJson(contacts, new File(file));
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<>();
        for (int i=0; i < count; i++){
            contacts.add(new ContactData()
                    .withFirstName(String.format("Firstname_gen %s", i))
                    .withLastName(String.format("Lastname_gen %s", i))
                    .withNickname(String.format("Nickname_gen %s", i))
                    .withTitle(String.format("Title_gen %s", i))
                    .withCompany(String.format("Company_gen %s", i))
                    .withHomePhone(getRandomPhone())
                    .withMobilePhone(getRandomPhone())
                    .withOfficePhone(getRandomPhone())
                    .withEmail(getRandomEmail())
                    .withEmail2(getRandomEmail())
                    .withEmail3(getRandomEmail()));
        }
        return contacts;
    }

    private String getRandomEmail() {
        return getRandomAlphaNumString(3, 10) + "@" + getRandomAlphaNumString(3, 7) + "." + getRandomAlphaNumString(2, 3);
    }

    private String getRandomAlphaNumString(int minLength, int maxLength) {
        Random random = new Random();
        String characters = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();
        int stringLength = random.nextInt(maxLength - minLength) + minLength;
        for (int i = 0; i < stringLength; i++){
            Character randomChar = characters.charAt(random.nextInt(characters.length()));
            result.append(randomChar);
        }
        return String.valueOf(result);
    }

    private String getRandomPhone() {
        Random random = new Random();
        StringBuilder result = new StringBuilder();
        for (int i=0; i < 10; i++){
            int number = random.nextInt(10);
            result.append(number);
        }
        return String.valueOf(result);
    }
}
