package org.sln.jugtourssln.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.sln.jugtourssln.base.BaseTestWithLogin;


public class DeleteGroupTest extends BaseTestWithLogin {

    @Test
    public void deleteGroup_Positive() {
        String groupName = "To be deleted";
        groupHelper.addNewGroup(groupName);
        Assertions.assertTrue(isGroupPresent(groupName), "Test group should exist before deletion");
        deleteGroupWithConfirmation(groupName);
        Assertions.assertFalse(isGroupPresent(groupName), "Group should be deleted");
    }


    private boolean isGroupPresent(String groupName) {
        try {
            findGroupRow(groupName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private WebElement findGroupRow(String groupName) {
        return driver.findElements(By.cssSelector("mat-row")).stream()
                .filter(row -> {
                    try {
                        return row.findElement(By.cssSelector("mat-cell.cdk-column-name")).getText().equals(groupName);
                    } catch (Exception e) {
                        return false;
                    }
                })
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Group not found: " + groupName));
    }

    private void deleteGroupWithConfirmation(String groupName) {
        WebElement groupRow = findGroupRow(groupName);
        WebElement deleteButton = groupRow.findElement(By.cssSelector(".mat-mdc-button"));
        deleteButton.click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("alert-success")));
    }

    @Test
    public void deleteGroup_Negative_Cancel() {
        String groupName = "To be kept";
        groupHelper.addNewGroup(groupName);

        Assertions.assertTrue(isGroupPresent(groupName), "Test group should exist before deletion");
        deleteGroupWithCanceling(groupName);
    }

    private void deleteGroupWithCanceling(String groupName) {
        WebElement groupRow = findGroupRow(groupName);

        WebElement deleteButton = groupRow.findElement(By.cssSelector(".mat-mdc-button"));
        deleteButton.click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.dismiss();

        Assertions.assertTrue(isGroupPresent(groupName), "Group should be deleted");
    }
}
