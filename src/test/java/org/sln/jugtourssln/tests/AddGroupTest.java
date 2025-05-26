package org.sln.jugtourssln.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.sln.jugtourssln.base.BaseTestWithLogin;
import org.sln.jugtourssln.pages.GroupPage;

public class AddGroupTest extends BaseTestWithLogin {

    public AddGroupTest() {
    }

    @Test
    public void addNewGroup_Positive() {
        String TEST_TITLE = "Selenium Test";
        GroupPage group = groupHelper.addNewGroup(TEST_TITLE);

        wait.until((ExpectedCondition<Boolean>) d ->
                group.isGroupPresent("Selenium Test"));
        Assertions.assertTrue(group.isGroupPresent("Selenium Test"), "group should be listed");
    }

    @Test
    public void addNewGroup_Negative_EmptyName() {
        GroupPage group = new GroupPage(driver);
        group.clickManageBtn();
        group.clickAddGroup();
        group.enterName("");
        WebElement saveBtn = driver.findElement(group.getSaveBtn());
        Assertions.assertFalse(saveBtn.isEnabled(), "Error message should show for empty name");
    }
}
