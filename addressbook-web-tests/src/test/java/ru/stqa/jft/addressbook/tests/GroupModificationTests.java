package ru.stqa.jft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.GroupData;

/**
 * Created by Anna on 2016-12-11.
 */
public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification(){
        app.getNavigationHelper().goToGroupPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
        app.getGroupHelper().submitGroupModification();
        //app.getGroupHelper().returnToGroupPage();
    }
}
