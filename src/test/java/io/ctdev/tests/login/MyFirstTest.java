package io.ctdev.tests.login;

import io.ctdev.tests.framework.driver.WebDriverSingleton;
import io.ctdev.tests.signup.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.ctdev.tests.framework.driver.WebDriverSingleton.getDriver;

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
