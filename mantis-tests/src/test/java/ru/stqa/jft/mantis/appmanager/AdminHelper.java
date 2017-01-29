package ru.stqa.jft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 2017-01-28.
 */
public class AdminHelper extends HelperBase {

    public AdminHelper(ApplicationManager app) {
        super(app);
    }
    //private String email;

    public void goToSettings() {
        wd.findElement(By.cssSelector("a[href='/mantisbt-2.0.0/manage_overview_page.php']")).click();
    }

    public void goToManageUsers() {
        wd.findElement(By.cssSelector("a[href='/mantisbt-2.0.0/manage_user_page.php']")).click();
    }

    public List<String> getUsersNamesList() {
        List<WebElement> users = wd.findElements(By.xpath("/html/body/div[3]/div[2]/div[2]/div/div/div[4]/div[2]/div[2]/div/table/tbody/tr"));
        List<String> userInfo = new ArrayList<>();
        for (int i = 1; i < users.size(); i++) {
            String name = wd.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div/div/div[4]/div[2]/div[2]/div/table/tbody/tr[" + Integer.toString(i) + "]/td/a")).getText();
            userInfo.add(name);
        }
        return userInfo;
    }

    public void chooseUser(){
        for (int i = 1; i < getUsersNamesList().size(); i++) {
            if (!getUsersNamesList().get(i-1).equals("administrator")) {
                wd.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div/div/div[4]/div[2]/div[2]/div/table/tbody/tr[" + Integer.toString(i) + "]/td/a")).click();
                break;
            }
        }
    }
    /*
    public List<String> chooseUser() {
        List<WebElement> users = wd.findElements(By.xpath("/html/body/div[3]/div[2]/div[2]/div/div/div[4]/div[2]/div[2]/div/table/tbody/tr"));
        List<String> userInfo = new ArrayList<>();
        for (int i = 1; i < users.size(); i++) {
            String name = wd.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div/div/div[4]/div[2]/div[2]/div/table/tbody/tr[" + Integer.toString(i) +"]/td/a")).getText();
            if (!name.equals("administrator")) {
                String email = wd.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div/div/div[4]/div[2]/div[2]/div/table/tbody/tr[" + Integer.toString(i) + "]/td[3]")).getText();
                userInfo.add(name);
                userInfo.add(email);
                wd.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div/div/div[4]/div[2]/div[2]/div/table/tbody/tr[" + Integer.toString(i) + "]/td/a")).click();
                break;
            }
        }
        return userInfo;
    }
    */

    public String getMail(){
        List<String> emailList = new ArrayList<>();
        String email = "";
        for (int i = 1; i < getUsersNamesList().size(); i++) {
            if (!getUsersNamesList().get(i-1).equals("administrator")) {
                emailList.add(wd.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div/div/div[4]/div[2]/div[2]/div/table/tbody/tr[" + Integer.toString(i) + "]/td[3]")).getText());
                email = emailList.get(0);
                break;
            }
        }
        return email;
    }
    /*
    public String  getMail() {
        // wyciagnac to do metody innej (fora)
        return chooseUser().get(1);
    }
    */

    public void resetPassword() {
        wd.findElement(By.cssSelector("input[value='Nowe hasło']")).click();
    }

    public void login(String administrator, String root) {
        wd.findElement(By.id("username")).sendKeys(administrator);
        wd.findElement(By.id("password")).sendKeys(root);
        wd.findElement(By.cssSelector("input[value='Zaloguj się']")).click();
    }

    public void changePassword(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.id("password"), password);
        type(By.id("password-confirm"), password);
        wd.findElement(By.cssSelector("button[type='submit']")).click();
    }
}
