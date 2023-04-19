package org.example;
import com.thoughtworks.gauge.Step;
import io.appium.java_client.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.google.common.collect.ImmutableMap;
import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;
import javax.annotation.Nullable;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.*;
public class StepImplementation extends BaseTest {
    public static Logger logger = LogManager.getLogger(StepImplementation.class);
    private List<String> stringList = new ArrayList<>();

    @Step("<id> elementi gorunur olana kadar bekle")
    public void gorunurbekleByID(String id) {
        WebDriverWait wait = new WebDriverWait(appiumDriver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
    }
    @Step("<xpath> elementi gorunur olana kadar bekle")
    public void gorunurbekleXpath(String xpath) {
        WebDriverWait wait = new WebDriverWait(appiumDriver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }
    @Step("<id> elementinin oldugu kontrol edilir")
    public void checkElementByID(String element) {
        Assert.assertTrue("Elementi gördü", appiumDriver.findElement(By.id(element)).isDisplayed());
        logger.info("Elementi gördü");
    }
    @Step("<xpath> elementinin oldugu kontrol edilir")
    public void checkElementByXpath(String element) {
        Assert.assertTrue("Elementi gördü", appiumDriver.findElement(By.xpath(element)).isDisplayed());
        logger.info("Elementi gördü");
    }
    @Step("<id> elementine tikla")
    public void clickByID(String element) {
        appiumDriver.findElement(By.id(element)).click();
        logger.info(element + "tiklandi");
    }
    @Step("<xpath>  elementine tikla")
    public void clickByXpath(String element) {
        appiumDriver.findElement(By.xpath(element)).click();
        logger.info(element + "tiklandi");
    }
    @Step("<id> elementi varsa tikla")
    public void clickAcceptbuttonByID(String element) throws InterruptedException {
        if (appiumDriver.findElement(By.id(element)).isDisplayed()) {
            appiumDriver.findElement(By.id(element)).click();
            waitForsecond(3);
        } else {
            System.out.println("Pop-up gelmedi");
        }
    }
    @Step("<xpath> elementi varsa tikla")
    public void clickAcceptbuttonByXpath(String element) throws InterruptedException {
        if (appiumDriver.findElement(By.xpath(element)).isDisplayed()) {
            appiumDriver.findElement(By.xpath(element)).click();
            waitForsecond(3);
        } else {
            System.out.println("Pop-up gelmedi");
        }
    }
    @Step("<id> id'li elemente <text> text degerini gonder")
    public void sendKeysByID(String id, String text) {
        appiumDriver.findElement(By.id(id)).sendKeys(text);
        logger.info(text + "degeri gonderildi");
    }
    @Step("<xpath> xpath'li elemente <text> text degerini gonder")
    public void sendKeysByxpath(String xpath, String text) {
        appiumDriver.findElement(By.xpath(xpath)).sendKeys(text);
        logger.info(text + "degeri gonderildi");
    }
    @Step("<id> elementin <keyword> text değerini içerdiğini kontrol et")
    public void textControlByID(String key, String keyword) {
        assertTrue(appiumDriver.findElement(By.id(key)).getText().contains(keyword), "elementi içermiyor");
        logger.info("Elementi içeriyor");
    }
    @Step("<xpath> elementin <keyword> text değerini içerdiğini kontrol et")
    public void textControlByXpath(String xpath, String keyword) {
        assertTrue(appiumDriver.findElement(By.xpath(xpath)).getText().contains(keyword), "elementi içermiyor");
        logger.info("Elementi içeriyor");
    }
    @Step("<xpath> elementin text degeri bir dizine at")
    public void getTextListByXpath(String key) {
        String text = appiumDriver.findElement(By.xpath(key)).getText();
        stringList.add(text);
        logger.info(text + " texti dizine atıldı");
    }
    @Step("<id> elementin text degeri bir dizine at")
    public void getTextListIdByID(String key) {
        String text = appiumDriver.findElement(By.id(key)).getText();
        stringList.add(text);
        logger.info(text + " texti dizine atıldı");
    }
    @Step("<xpath> li elementi al,bugun veya bugunden sonraki 7 gunden birine tikla")
    public void clickRandomByxpath(String xpath) {
        List<MobileElement> elements = appiumDriver.findElements(By.xpath(xpath));
        Random rand = new Random();
        int randomNumber = rand.nextInt(8) + 1;
        elements.get(randomNumber).click();
    }
    @Step("string listenin iki elementinin esit oldugunu kontrol et")
    public void compareList() {
        assertEquals(stringList.get(0), stringList.get(1));
        logger.info(stringList.get(0) + stringList.get(1) + "birbirine esittir");
    }
    @Step("<second> saniye kadar bekle")
    public void waitForsecond(int second) throws InterruptedException {
        Thread.sleep(1000 * second);
    }
    @Step("<key> elementi rasgele sec")
    public void chooseRandomProduct(String key) {
        List<MobileElement> productList = new ArrayList<>();
        List<MobileElement> elements = (List<MobileElement>) appiumDriver.findElement(By.id(key));
        int elementsSize = elements.size();
        int height = appiumDriver.manage().window().getSize().height;
        for (int i = 0; i < elementsSize; i++) {
            MobileElement element = elements.get(i);
            int y = element.getCenter().getY();
            if (y > 0 && y < (height - 100)) {
                productList.add(element);
            }
        }
        Random random = new Random();
        int randomNumber = random.nextInt(productList.size());
        productList.get(randomNumber).click();
        logger.info("Rastgele seçim yapıldı");
    }
    @Step("<xpath> elementi rasgele sec")
    public void chooseRandomProductByXpath(String xpath) {

        List<MobileElement> productList = new ArrayList<>();
        List<MobileElement> elements = (List<MobileElement>) appiumDriver.findElement(By.id(xpath));
        int elementsSize = elements.size();
        int height = appiumDriver.manage().window().getSize().height;
        for (int i = 0; i < elementsSize; i++) {
            MobileElement element = elements.get(i);
            int y = element.getCenter().getY();
            if (y > 0 && y < (height - 100)) {
                productList.add(element);
            }
        }
        Random random = new Random();
        int randomNumber = random.nextInt(productList.size());
        productList.get(randomNumber).click();
        logger.info("Rastgele seçim yapıldı");
    }
    public void swipeDownAccordingToPhoneSize(AppiumDriver<MobileElement> appiumDriver) {
        Dimension size = appiumDriver.manage().window().getSize();
        int startY = (int) (size.height * 0.25);
        int endY = (int) (size.height * 0.75);
        int startX = size.width / 2;

        TouchAction<?> action = new TouchAction<>(appiumDriver);
        action.press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(startX, endY))
                .release()
                .perform();
    }
    @Step({"<id> elementi bulana kadar swipe et"})
    public void findByKeyWithSwipeByID(String key) {
        int maxRetryCount = 10;
        while (maxRetryCount > 0) {

            List<MobileElement> elements = (List<MobileElement>) appiumDriver.findElement(By.id(key));
            if (elements != null) {
                if (elements.get(0).isDisplayed() == false) {
                    maxRetryCount--;
                    swipeDownAccordingToPhoneSize(appiumDriver);
                } else {
                    System.out.println(key + " element bulundu. DOWN");
                    break;
                }
            } else {
                System.out.println(key + " else girdi. DOWN");
                swipeDownAccordingToPhoneSize(appiumDriver);
            }
        }
    }
    @Step({"<xpath> elementi bulana kadar swipe et"})
    public void findByKeyWithSwipeByXpath(String key) {
        int maxRetryCount = 10;
        while (maxRetryCount > 0) {

            List<MobileElement> elements = (List<MobileElement>) appiumDriver.findElement(By.xpath(key));
            if (elements != null) {
                if (elements.get(0).isDisplayed() == false) {
                    maxRetryCount--;
                    swipeDownAccordingToPhoneSize(appiumDriver);
                } else {
                    System.out.println(key + " element bulundu. DOWN");
                    break;
                }
            } else {
                System.out.println(key + " else girdi. DOWN");
                swipeDownAccordingToPhoneSize(appiumDriver);
            }
        }
    }
}
