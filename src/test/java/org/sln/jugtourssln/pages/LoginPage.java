package org.sln.jugtourssln.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    public static final String VALID_USERNAME = "jugtours@gmail.com";
    public static final String VALID_PASSWORD = "Selenium1234!";
    private final By usernameField = By.cssSelector("input[name='username']");
    private final By passwordField = By.cssSelector("input[name='password']");
    private final By submitButton = By.cssSelector("button[type='submit']");
    private final By logoutBtn = By.id("logout-button");
    private final By loginBtn = By.xpath("//button[@id='login']");

    private final WebDriverWait wait;
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public boolean isLoginButtonVisible() {
        return driver.findElement(loginBtn).isDisplayed();
    }

    public void clickLogin() {
        driver.findElement(loginBtn).click();
    }

    public void enterCredentials() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(VALID_USERNAME);
        driver.findElement(passwordField).sendKeys(VALID_PASSWORD);
    }

    public void submitLogin() {
        driver.findElement(submitButton).click();
    }
}
