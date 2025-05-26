package org.sln.jugtourssln.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GroupPage {
    private WebDriver driver;
    private By manageBtn = By.linkText("Manage JUG Tour");
    private By addGroupBtn = By.xpath("//a[@id='add']");
    private By nameField = By.id("name");
    private By saveBtn = By.xpath("//button[@id='save']");

    public GroupPage(WebDriver driver) {
        this.driver = driver;
    }

    public By getSaveBtn() {
        return saveBtn;
    }

    public void clickManageBtn() {
        driver.findElement(manageBtn).click();
    }

    public void clickAddGroup() {
        driver.findElement(addGroupBtn).click();
    }

    public void enterName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void clickSave() {
        driver.findElement(saveBtn).click();
    }

    public boolean isGroupPresent(String name) {
        return driver.getPageSource().contains(name);
    }
}
