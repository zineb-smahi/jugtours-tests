package org.sln.jugtourssln.base;

import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sln.jugtourssln.helpers.GroupHelper;
import org.sln.jugtourssln.pages.LoginPage;

import java.time.Duration;

public class BaseTestWithLogin extends BaseTest {
    protected LoginPage loginPage;
    protected WebDriverWait wait;

    protected GroupHelper groupHelper;

    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver);
        performLogin();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        groupHelper = new GroupHelper(driver);
    }

    protected void performLogin() {
        loginPage.clickLogin();
        loginPage.enterCredentials();
        loginPage.submitLogin();
    }

}