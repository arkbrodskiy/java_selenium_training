package first.pack.addressbook;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

public class ContactCreationTests extends TestBase{
    @Test
    public void testContactCreation() {
        initContactCreation();
        fillContactForm(new ContactData("Сднемрождения", "Поздравит", "Инаверно", "Оставит", "Мне в подарок", "4687569820", "4753698561", "8965475258", "tpoi@lkweq.giu"));
        submitContactCreation();
        returnToHomePage();
    }

}
