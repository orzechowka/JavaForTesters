package ru.stqa.jft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Anna on 2016-12-10.
 */
public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void groupPage() {
        if (isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))) {
            return;
        }
        click(By.linkText("grupy"));
    }

    public void goToNewContact() {
        if (isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Edytuj / dodaj wpis do książki adresowej\n" +
                "\n")) {
            return;
        }
        click(By.linkText("nowy wpis"));
    }

    public void goToHomepage() {
        if (isElementPresent(By.name("maintable"))) {
            return;
        }
        click(By.linkText("strona główna"));
    }
}
