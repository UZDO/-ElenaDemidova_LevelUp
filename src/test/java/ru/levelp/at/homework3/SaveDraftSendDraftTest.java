package ru.levelp.at.homework3;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SaveDraftSendDraftTest extends BaseTestMail {

    @Test
    void saveDraftSendDraftTestIT() {
        //1. Войти в почту
        //2. Assert, что вход выполнен успешно
        var title = driver.getTitle();
        Assertions.assertThat(title).isEqualTo("Mail.ru: почта, поиск в интернете, новости, игры");
        wait.until(ExpectedConditions.elementToBeClickable(By.className("resplash-btn"))).click();
        //Авторизация
        WebElement frameElement = driver.findElement(By.className("ag-popup__frame__layout__iframe"));
        WebDriver frameDriver = driver.switchTo().frame(frameElement);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys("Dem_level_up");
        wait.until(ExpectedConditions.elementToBeClickable(By.className("inner-0-2-81"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password"))).sendKeys("245619LEVEL1!");
        wait.until(ExpectedConditions.elementToBeClickable(By.className("inner-0-2-81"))).click();
        //Подсчет отправленных
        wait.until(ExpectedConditions.elementToBeClickable(By
                .xpath("//*[contains(@class, 'nav__item') and contains(.,'Отправленные')]"))).click();
        final List<WebElement> linksBeforeSend = driver.findElements(By.xpath("//a"));
        //Подсчет черновиков
        wait.until(ExpectedConditions.elementToBeClickable(By
                .xpath("//*[contains(@class, 'nav__item') and contains(.,'Черновики')]"))).click();
        final List<WebElement> linksBeforeDraft = driver.findElements(By.xpath("//a"));

        //3. Создать новое письмо (заполнить адресата, тему письма и тело)
        wait.until(ExpectedConditions.elementToBeClickable(By
                .xpath("//*[contains(@class, 'compose-button')]"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                        .xpath("//*[contains(@class, 'container--H9L5q')"
                                + "and parent::div[contains(@class, 'inputContainer')]]")))
                .sendKeys("dem_level_for_send@mail.ru");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//*[contains(@class,'container--H9L5q') and @name = 'Subject']"))).sendKeys("Это тема");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//*[contains(@class,'cke_contents_true cke_show_borders')]/div"))).sendKeys("Это тело");

        //4. Сохранить его как черновик
        wait.until(ExpectedConditions.elementToBeClickable(By
                .xpath("//*[contains(@class,'vkuiButton__content') and contains(., 'Сохранить')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@title, 'Закрыть')]"))).click();

        //5. Verify, что письмо сохранено в черновиках
        wait.until(ExpectedConditions.elementToBeClickable(By
                .xpath("//*[contains(@class, 'nav__item') and contains(.,'Черновики')]"))).click();
        System.out.println("Открыли черновики");
        List<WebElement> linksAfterDraft = driver.findElements(By
                .xpath("//*[contains(@class,'ReactVirtualized__Grid__innerScrollContainer')]//a"));
        Assertions.assertThat(linksBeforeDraft.size() == linksAfterDraft.size() - 1);
        //Открыли черновик
        wait.until(ExpectedConditions.elementToBeClickable(By
                .xpath("//*[contains(@class, 'ReactVirtualized__Grid__innerScrollContainer')]/a[1]"))).click();

        //6. Verify контент, адресата и тему письма (должно совпадать с пунктом 3)
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("compose-app__compose")));
        driver.findElement(By.xpath("//*[contains(@class, 'container--H9L5q') "
                        + "and parent::div[contains(@class, 'inputContainer')]]"))
                .getText().matches("dem_level_for_send@mail.ru");
        driver.findElement(By.xpath("//*[contains(@class,'container--H9L5q') and @name = 'Subject']")).getText()
                .matches("Это тема");
        driver.findElement(By.xpath("//*[contains(@class,'cke_contents_true cke_show_borders')]")).getText()
                .matches("Это тело");

        //7. Отправить письмо
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By
                .xpath("//*[contains(@class,'compose-windows__notify')]")));
        wait.until(ExpectedConditions.elementToBeClickable(By
                .xpath("//*[contains(@class,'vkuiButton__content') and contains(., 'Отправить')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@class,'button2_close')]"))).click();

        //8. Verify, что письмо исчезло из черновиков
        driver.manage().window().maximize();
        final List<WebElement> linksAfterSendDraft = driver.findElements(By
                .xpath("//*[contains(@class,'ReactVirtualized__Grid__innerScrollContainer')]//a"));
        Assertions.assertThat(linksAfterSendDraft.size() == linksAfterDraft.size() - 1);

        //9. Verify, что письмо появилось в папке отправленные
        wait.until(ExpectedConditions.elementToBeClickable(By
                .xpath("//*[contains(@class, 'nav__folder-name__txt') and contains(.,'Отправленные')]"))).click();
        final List<WebElement> linksAfterSend = driver.findElements(By
                .xpath("//*[contains(@class,'ReactVirtualized__Grid__innerScrollContainer')]//a"));
        Assertions.assertThat(linksBeforeSend.size() == linksAfterSend.size() + 1);

        //10. Выйти из учётной записи
        wait.until(ExpectedConditions.elementToBeClickable(By
                .xpath("//*[contains(@class, 'ph-avatar-img')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By
                .xpath("//*[contains(@class, 'ph-item__hover-active') and contains(.,'Выйти')]"))).click();
    }
}
