package ru.levelp.at.homework4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginMailPage {

    private static final String MAIL_URL = "https://mail.ru/";

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(className = "username")
    private WebElement loginTextField;

    @FindBy(className = "password")
    private WebElement passwordTextField;

    @FindBy(className = "resplash-btn")
    private WebElement startLoginButton;

    @FindBy(className = "ag-popup__frame__layout__iframe")
    private WebElement frameElement;

    public LoginMailPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.navigate().to(MAIL_URL);
    }

    public void openFrame() {
        driver.switchTo().frame(frameElement);
        PageFactory.initElements(driver, this);
        //PageFactory.initElements(frameDriver, this.frameDriver);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void fillLoginTextField(final String login) {
        wait.until(ExpectedConditions.visibilityOf(loginTextField)).sendKeys(login);
    }

    public void fillPasswordTextField(final String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordTextField)).sendKeys(password);
    }

    public void clickStartLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(startLoginButton)).click();
    }

}
