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

public class LoginToJuiceShopNegative {

    private String invalidUserName1 = "yana";
    private String password = "qQ2$4";

    @BeforeClass
    public void setUp() throws InterruptedException {
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        getDriver().get("http://3.18.213.48/");

        getDriver().findElement(By.cssSelector("[class*='close-dialog']")).click();

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


    @Test(description = "Login -Negative Case - Login with empty email field")
    public void emptryEmailFieldLogin() throws InterruptedException {

        System.out.println("Empty email field verification");
        getDriver().findElement(By.id("email")).click();
        getDriver().findElement(By.id("password")).click();
        Thread.sleep(2000);
        String emptyEmailFieldNotification = getDriver().findElement(By.xpath("//*[contains(text(),'Please provide an email address')]")).getAttribute("innerText").trim();
        Assert.assertEquals(emptyEmailFieldNotification, "Please provide an email address.", "Email field does not have validation for empty value");
        Thread.sleep(2000);
    }

    @Test(description = "Login - Negative Case - Login with empty password field")
    public void emptryPasswordFieldLogin() throws InterruptedException {

        System.out.println("Empty password field verification");
        getDriver().findElement(By.id("password")).click();
        getDriver().findElement(By.id("email")).click();
        Thread.sleep(2000);
        String emptyPasswordFieldNotification = getDriver().findElement(By.xpath("//*[contains(text(),'Please provide a password')]")).getAttribute("innerText").trim();
        Assert.assertEquals(emptyPasswordFieldNotification, "Please provide a password.", "Password field does not have validation for empty value");
        Thread.sleep(2000);
    }

    @Test(description = "Login - Negative Case - Login with invalid email and password fields")
    public void invalidEmailFieldLogin() throws InterruptedException {

        System.out.println("Invalid email field verification");
        getDriver().findElement(By.id("email")).sendKeys(invalidUserName1);
        getDriver().findElement(By.id("password")).sendKeys(password);
        System.out.println("Clicking on Login button");
        getDriver().findElement(By.id("loginButton")).click();
        Thread.sleep(2000);
        String invalidEmailNotification = getDriver().findElement(By.xpath("//*[contains(text(),'Invalid email or password.')]")).getAttribute("innerText").trim();
        Assert.assertEquals(invalidEmailNotification, "Invalid email or password.", "Email and password fields do not have validation for invalid values");
        Thread.sleep(2000);
    }

}
