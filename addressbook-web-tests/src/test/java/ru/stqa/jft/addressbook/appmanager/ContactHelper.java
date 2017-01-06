package ru.stqa.jft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.jft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Anna on 2016-12-10.
 */
public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToHomepage() {
        click(By.linkText("home page"));
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"),contactData.getName());
        type(By.name("lastname"), contactData.getSurname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getMobileNumber());
        type(By.name("email"), contactData.getEmail1());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }


    public void initContactModification(int index) {
        wd.findElements(By.xpath("//td/a/img[@src='icons/pencil.png']")).get(index).click();

        //click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    private void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteSelectedContacts() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void submitContactModification() {
        click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }

    public void acceptAlert() {
        wd.switchTo().alert().accept();
    }

    public void create(ContactData contact) {
        fillContactForm(contact, true);
        submitContactCreation();
    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        returnToHomepage();
    }

    private void initContactModificationById(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    }

    public void delete(int index) {
        selectContact(index);
        deleteSelectedContacts();
        acceptAlert();
        /*
        is this needed?
        app.goTo().goToHomepage();
        */
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContacts();
        acceptAlert();
    }



    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }


    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element: elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            String surname = wd.findElement(By.xpath("//tr[@name='entry']/td[2]")).getText();
            String name = wd.findElement(By.xpath("//tr[@name='entry']/td[3]")).getText();
            contacts.add(new ContactData().withId(id).withName(name).withSurname(surname));
        }
        return contacts;
    }

    public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element: elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            String surname = wd.findElement(By.xpath("//tr[@name='entry']/td[2]")).getText();
            String name = wd.findElement(By.xpath("//tr[@name='entry']/td[3]")).getText();
            contacts.add(new ContactData().withId(id).withName(name).withSurname(surname));
        }
        return contacts;
    }


}
