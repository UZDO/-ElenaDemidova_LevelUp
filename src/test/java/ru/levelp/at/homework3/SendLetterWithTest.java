package ru.levelp.at.homework3;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SendLetterWithTest extends BaseTestMail {

    @Test
    void sendLetterWithTestIT() {
        //1. Войти в почту
        //2. Assert, что вход выполнен успешно
        var title = driver.getTitle();
        Assertions.assertThat(title).isEqualTo("Mail.ru: почта, поиск в интернете, новости, игры");
        wait.until(ExpectedConditions.elementToBeClickable(By.className("resplash-btn"))).click();
        WebElement frameElement = driver.findElement(By.className("ag-popup__frame__layout__iframe"));
        WebDriver frameDriver = driver.switchTo().frame(frameElement);
        driver.manage().window().maximize();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .name("username"))).sendKeys("Dem_level_up");
        wait.until(ExpectedConditions.elementToBeClickable(By.className("inner-0-2-81"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .name("password"))).sendKeys("245619LEVEL1!");
        wait.until(ExpectedConditions.elementToBeClickable(By.className("inner-0-2-81"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By
                .xpath("//*[contains(@class, 'nav__folder-name__txt') and contains(.,'Тест')]"))).click();
        final List<WebElement> linksBeforeTest = driver.findElements(By.xpath("//a"));
        //Подсчет отправленных
        wait.until(ExpectedConditions.elementToBeClickable(By
                .xpath("//*[contains(@class, 'nav__item') and contains(.,'Отправленные')]"))).click();
        final List<WebElement> linksBeforeSend = driver.findElements(By.xpath("//a"));

        //3. Создать новое письмо (заполнить адресата (самого себя), тему письма (должно содержать слово Тест) и тело)
        wait.until(ExpectedConditions.elementToBeClickable(By
                .xpath("//*[contains(@class, 'compose-button')]"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                        .xpath("//*[contains(@class, 'container--H9L5q')"
                                + "and parent::div[contains(@class, 'inputContainer')]]")))
                .sendKeys("dem_level_up@mail.ru");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                        .xpath("//*[contains(@class,'container--H9L5q') and @name = 'Subject']")))
                .sendKeys("Это Тест тема Тест");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                        .xpath("//*[contains(@class,'cke_contents_true cke_show_borders')]/div")))
                .sendKeys("Это тело письма");

        //4. Отправить письмо
        wait.until(ExpectedConditions.elementToBeClickable(By
                .xpath("//*[contains(@class,'vkuiButton__content') and contains(., 'Отправить')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By
                .xpath("//*[contains(@class,'button2_close')]"))).click();

        //5. Verify, что письмо появилось в папке отправленные
        wait.until(ExpectedConditions.elementToBeClickable(By
                .xpath("//*[contains(@class, 'nav__item') and contains(.,'Отправленные')]"))).click();
        final List<WebElement> linksAfterSend = driver.findElements(By
                .xpath("//*[contains(@class,'ReactVirtualized__Grid__innerScrollContainer')]//a"));
        Assertions.assertThat(linksBeforeSend.size() == linksAfterSend.size() - 1);

        //6. Verify, что письмо появилось в папке «Тест»
        wait.until(ExpectedConditions.elementToBeClickable(By
                .xpath("//*[contains(@class, 'nav__folder-name__txt') and contains(.,'Тест')]"))).click();
        final List<WebElement> linksAfterTest = driver.findElements(By.xpath("//a"));
        Assertions.assertThat(linksBeforeTest.size() == linksAfterSend.size() - 1);

        //7. Verify контент, адресата и тему письма (должно совпадать с пунктом 3)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//*[contains(@class, 'ReactVirtualized__Grid__innerScrollContainer')]")));
        wait.until(ExpectedConditions.elementToBeClickable(By
                .xpath("//*[contains(@class, 'ReactVirtualized__Grid__innerScrollContainer')]/a[1]"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//*[contains(@class, 'thread__subject-line')]")));
        driver.findElement(By.xpath("//*[contains(@class, 'letter-contact') "
                        + "and parent::div[contains(@class, 'letter__recipients_short')]]"))
                .getText().matches("dem_level_up@mail.ru");
        driver.findElement(By.xpath("//*[contains(@class,'thread__subject-line')]")).getText()
                .matches("Это Тест тема Тест");
        driver.findElement(By.xpath("//*[contains(@class,'js-helper js-readmsg-msg')]")).getText()
                .contains("Это тело письма");

        //8. Выйти из учётной записи
        wait.until(ExpectedConditions.elementToBeClickable(By
                .xpath("//*[contains(@class, 'ph-avatar-img')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By
                .xpath("//*[contains(@class, 'ph-item__hover-active') and contains(.,'Выйти')]"))).click();
    }
}
