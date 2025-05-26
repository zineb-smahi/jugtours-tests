package org.sln.jugtourssln.helpers;

import org.openqa.selenium.WebDriver;
import org.sln.jugtourssln.base.BaseTest;
import org.sln.jugtourssln.base.BaseTestWithLogin;
import org.sln.jugtourssln.pages.GroupPage;

public class GroupHelper extends BaseTest {

    public GroupHelper(WebDriver driver) {
        this.driver = driver;
    }

    public GroupPage addNewGroup(String title) {
        GroupPage group = new GroupPage(driver);
        group.clickManageBtn();
        group.clickAddGroup();
        group.enterName(title);
        group.clickSave();
        return group;
    }
}
