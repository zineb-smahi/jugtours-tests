package org.sln.jugtourssln.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sln.jugtourssln.base.BaseTest;
import org.sln.jugtourssln.pages.LoginPage;

import java.time.Duration;

public class LoginTest extends BaseTest {
    private final String INVALID_PASSWORD = "Jugtours1234";

    @Test
    public void loginPageLoads() {
        LoginPage login = new LoginPage(driver);
        Assertions.assertTrue(login.isLoginButtonVisible(), "Login button should be visible");
    }

    @Test
    public void login() {
        LoginPage login = new LoginPage(driver);
        login.clickLogin();
    }

    @Test
    public void testSuccessfulLogin() {
        login();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[name='username']")));
        emailField.sendKeys(LoginPage.VALID_USERNAME);

        WebElement passwordField = driver.findElement(By.cssSelector("input[name='password']"));
        passwordField.sendKeys(LoginPage.VALID_PASSWORD);

        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();

        WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("logout")));
        Assertions.assertTrue(logoutButton.isDisplayed(), "Logout button should be visible after successful login");
    }

    @Test
    public void testFailedLogin() {
        login();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[name='username']")));
        emailField.sendKeys(LoginPage.VALID_USERNAME);

        WebElement passwordField = driver.findElement(By.cssSelector("input[name='password']"));
        passwordField.sendKeys(INVALID_PASSWORD);

        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".ulp-input-error-message")));
        Assertions.assertTrue(errorMessage.isDisplayed(), "Error message should be visible after failed login");
    }
}
