package io.ctdev.tests.login;

import io.ctdev.framework.driver.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.ctdev.framework.driver.WebDriverSingleton.getDriver;

public class MyFirstTest {
    WebDriver driver;

    @Test
    public void shouldAnswerWithTrue1() throws InterruptedException {
        driver.get("http://prom.ua");
        String expectedTitle = "Prom.ua — маркетплейс Украины";
        String actualTitle = driver.getTitle();
        Thread.sleep(3000);
        Assert.assertEquals(actualTitle, expectedTitle, "The title is not Google");
    }

    @Test
    public void searchWebelementTest() throws InterruptedException {
        driver.get("https:google.com/");
        List<WebElement> elements = driver.findElements(By.tagName("div")); //список элементов с тэгом div
        System.out.println(elements.size());

        /*
        WebElement element = driver.findElement(By.name("q")); // создадим webelement который будет искать поле ввода
        element.sendKeys("Customertimes");//сразу попробуем в поле что-то записать, для этого есть команда sendKeys
        Thread.sleep(15000);
        */
    }

    @BeforeClass
    public void beforeClass() {
        driver = getDriver();
    }

    @AfterClass
    public void afterClass() {
        WebDriverSingleton.closeDriver();
        //      driver.quit();
    }
}
