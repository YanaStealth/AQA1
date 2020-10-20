package io.ctdev.tests.login;

import io.ctdev.tests.framework.driver.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import static io.ctdev.tests.framework.driver.WebDriverSingleton.getDriver;

public class LoginToJuiceShopTest {

    private String userName = "dima@ukr.net";

    @BeforeClass
    public void setUp() {
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        getDriver().get("http://3.18.213.48/");
        getDriver().findElement(By.cssSelector("[class*='close-dialog']")).click();
    }

    @AfterClass
    public void tearDown() {
        WebDriverSingleton.closeDriver();
    }

    @Test
    public void userIsAbleToLoginToShop() throws InterruptedException {
        System.out.println("Clicking on Account button"); // полезно для дебага
        WebElement element = getDriver().findElement(By.id("navbarAccount")); // можем либо создать объект элемента, либо напрямую его вызвать getDriver().
        element.click();

        System.out.println("Clicking on Login button");
        getDriver().findElement(By.id("navbarLoginButton")).click();

        System.out.println("Typing user email" + userName);
        getDriver().findElement(By.id("email")).sendKeys(userName);

        System.out.println("Typing user password 12345678");
        getDriver().findElement(By.id("password")).sendKeys("12345678");

        System.out.println("Clicking on Login button");
        getDriver().findElement(By.id("loginButton")).click();

        System.out.println("Check if user is logged in. Clicking on Account button.");
        getDriver().findElement(By.id("navbarAccount")).click();

        System.out.println("Getting user name from navigation bar");
        Thread.sleep(2000); //добавили ожидание, тк тесты падают из-за того что не успевает прогрузиться элемент, но не советую такое делать без крайней необходимости
        String actualUserName = getDriver().findElement(By.cssSelector("[aria-label='Go to user profile'] span")).getAttribute("innerText").trim();
        Assert.assertEquals(actualUserName, userName, "User name does not match");

    }
}
