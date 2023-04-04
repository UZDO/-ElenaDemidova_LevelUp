package ru.levelp.at.homework3;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DeleteReceivedLetterTest extends BaseTestMail {

    @Test
    void deleteReceivedLetterIT() {
        var title = driver.getTitle();
        Assertions.assertThat(title).isEqualTo("Mail.ru: почта, поиск в интернете, новости, игры");
        wait.until(ExpectedConditions.elementToBeClickable(By.className("resplash-btn"))).click();
        driver.manage().window().maximize();
        //1. Войти в почту
        //2. Assert, что вход выполнен успешно
        WebElement frameElement = driver.findElement(By.className("ag-popup__frame__layout__iframe"));
        WebDriver frameDriver = driver.switchTo().frame(frameElement);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .name("username"))).sendKeys("Dem_level_up");
        wait.until(ExpectedConditions.elementToBeClickable(By
                .className("inner-0-2-81"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .name("password"))).sendKeys("245619LEVEL1!");
        wait.until(ExpectedConditions.elementToBeClickable(By
                .className("inner-0-2-81"))).click();
        //Подсчет удаленных
        wait.until(ExpectedConditions.elementToBeClickable(By
                .xpath("//*[contains(@class, 'nav__item') and contains(.,'Корзина')]"))).click();
        final List<WebElement> linksBeforeDeleted = driver.findElements(By.xpath("//a"));
        //Подсчет входящих
        wait.until(ExpectedConditions.elementToBeClickable(By
                .xpath("//*[contains(@class, 'nav__item') and contains(.,'Входящие')]"))).click();
        final List<WebElement> linksBeforeReceived = driver.findElements(By.xpath("//a"));

        //3. Создать новое письмо (заполнить адресата (самого себя), тему письма (должно содержать слово Тест) и тело)
        wait.until(ExpectedConditions.elementToBeClickable(By
                .xpath("//*[contains(@class, 'compose-button')]"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                        .xpath("//*[contains(@class, 'container--H9L5q') "
                                + "and parent::div[contains(@class, 'inputContainer')]]")))
                .sendKeys("dem_level_up@mail.ru");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                        .xpath("//*[contains(@class,'container--H9L5q') and @name = 'Subject']")))
                .sendKeys("Это тема удаленного письма");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                        .xpath("//*[contains(@class,'cke_contents_true cke_show_borders')]/div")))
                .sendKeys("Это тело удаленного письма");

        //4. Отправить письмо
        wait.until(ExpectedConditions.elementToBeClickable(By
                .xpath("//*[contains(@class,'vkuiButton__content') and contains(., 'Отправить')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@class,'button2_close')]"))).click();

        //5. Verify, что письмо появилось в папке Входящие
        final List<WebElement> linksAfterReceived = driver.findElements(By.xpath("//a"));
        Assertions.assertThat(linksAfterReceived.size() == linksBeforeReceived.size() + 1);

        //6. Verify контент, адресата и тему письма (должно совпадать с пунктом 3)
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
                .matches("Это тема удаленного письма");
        driver.findElement(By.xpath("//*[contains(@class,'js-helper js-readmsg-msg')]")).getText()
                .contains("Это тело удаленного письма");

        //7. Удалить письмо
        wait.until(ExpectedConditions.elementToBeClickable(By
                .xpath("//*[contains(@class, 'button2__txt')and contains(., 'Удалить')]"))).click();

        //8. Verify что письмо появилось в папке Корзина
        wait.until(ExpectedConditions.elementToBeClickable(By
                .xpath("//*[contains(@class, 'nav__item') and contains(.,'Корзина')]"))).click();
        final List<WebElement> linksAfterDeleted = driver.findElements(By.xpath("//a"));
        Assertions.assertThat(linksAfterDeleted.size() == linksBeforeDeleted.size() + 1);

        //9. Выйти из учётной записи
        wait.until(ExpectedConditions.elementToBeClickable(By
                .xpath("//*[contains(@class, 'ph-avatar-img')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By
                .xpath("//*[contains(@class, 'ph-item__hover-active') and contains(.,'Выйти')]"))).click();
    }
}
