package io.ctdev.Lesson5.ExplicitWait;

import io.ctdev.framework.driver.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.ctdev.framework.driver.WebDriverSingleton.getDriver;

public class LoginToJuiceShopPositiveExplicit {
    private String validUserNameLogin = "yana4@gmail.com";
    private String passwordLogin = "qQ2$4";
    WebDriver driver = getDriver(); //explicit wait
    WebDriverWait wait; //explicit wait

    @BeforeClass
    public void setUp() throws InterruptedException {

        getDriver().get("http://3.18.213.48/");

        getDriver().findElement(By.cssSelector("[class*='close-dialog']")).click();
        wait = new WebDriverWait(driver, 500); //explicit wait
        System.out.println("Clicking on Account button");
        WebElement element = getDriver().findElement(By.id("navbarAccount")); // можем либо создать объект элемента, либо напрямую его вызвать getDriver().
        element.click();

        System.out.println("Clicking on Login button");
        getDriver().findElement(By.id("navbarLoginButton")).click();
    }

    @AfterClass
    public void tearDown() {
        WebDriverSingleton.closeDriver();
    }

    @Test(description = "Login verification - Positive")
    public void userLoginVerificationPositiveCase() throws InterruptedException {


        System.out.println("Typing user email" + validUserNameLogin);
        getDriver().findElement(By.id("email")).sendKeys(validUserNameLogin);

        System.out.println("Typing user password"+passwordLogin);
        getDriver().findElement(By.id("password")).sendKeys(passwordLogin);

        System.out.println("Clicking on Login button");
        getDriver().findElement(By.id("loginButton")).click();

        System.out.println("Check if user is logged in. Clicking on Account button.");
        getDriver().findElement(By.id("navbarAccount")).click();
        // Thread.sleep(2000);

        System.out.println("Getting user name from navigation bar");
        WebElement userNameElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label='Go to user profile'] span")));//explicit wait
        String actualUserName1 = userNameElement.getAttribute("innerText").trim();//explicit wait
        //String actualUserName1 = getDriver().findElement(By.cssSelector("[aria-label='Go to user profile'] span")).getAttribute("innerText").trim();
        Assert.assertEquals(actualUserName1, validUserNameLogin, "User name does not match");
    }
}
