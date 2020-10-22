package io.ctdev.tests.login;
import io.ctdev.tests.framework.driver.WebDriverSingleton;
import io.ctdev.tests.signup.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HeaderPromUaTest extends BaseTest {
    WebDriver driver;

    @Test
    public void headerPromUaTestVerification() throws InterruptedException {
        // System.setProperty("webdriver.chrome.driver", "C://Program Files//WEBDrivers//chromedriver.exe");
        driver.get("http://prom.ua");
        String expectedTitle = "Prom.ua — маркетплейс Украины";
        String actualTitle = driver.getTitle();
        Thread.sleep(3000);
        Assert.assertEquals(actualTitle, expectedTitle, "The title is not:Prom.ua — маркетплейс Украины");
    }

    @BeforeClass
    public void beforeClass() {
       // System.setProperty("webdriver.chrome.driver", "C://Program Files//WEBDrivers//chromedriver.exe"); //настройки выносим в beforeClass()
       // ChromeOptions options = new ChromeOptions();
       // options.addArguments("--start-fullscreen"); //используем options открыть full screen браузера
       // options.setHeadless(true); //запустить браузер в headless режиме, мы браузер не видим
      //  driver = new ChromeDriver(options);
        WebDriverSingleton.getDriver();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}






