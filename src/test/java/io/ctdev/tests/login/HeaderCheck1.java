package io.ctdev.tests.login;

import io.ctdev.tests.framework.driver.WebDriverSingleton;
import io.ctdev.tests.signup.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HeaderCheck1 extends BaseTest {

    WebDriver driver, driver1;

    @Test
    public void shouldAnswerWithTrue1() throws InterruptedException {

        driver.get("http://prom.ua");
        String expectedTitle = "Prom.ua — маркетплейс Украины";
        String actualTitle = driver.getTitle();
                Assert.assertEquals(actualTitle, expectedTitle, "The title is not Google");

        driver1.get("http://prom.ua");
        //String expectedTitle = "Prom.ua — маркетплейс Украины";
        String actualTitle1 = driver1.getTitle();
        Assert.assertEquals(actualTitle1, expectedTitle, "The title is not Google");

        Thread.sleep(3000);
    }

    @BeforeClass
    public void beforeClass() {
       //  System.setProperty("webdriver.chrome.driver", "C://Program Files//WEBDrivers//chromedriver.exe"); //настройки выносим в beforeClass()
        WebDriverManager.chromedriver().setup(); //скачает из интернета актуальный вебдрайвер для текущего браузера
       ChromeOptions options = new ChromeOptions();
       options.addArguments("--start-fullscreen"); //используем options открыть full screen браузера
        driver = new ChromeDriver(options);

        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options1=new FirefoxOptions();
        driver1 = new FirefoxDriver(options1);
    }

    @AfterClass
    public void afterClass() {
        WebDriverSingleton.closeDriver();
    }
}