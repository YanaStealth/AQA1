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

public class RegisterUserJuiceShopTestPositive {
    private String validUserName = "yana6@gmail.com";
    private String password = "qQ2$4";
    private String answer = "Coopert";

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

        System.out.println("Clicking 'Not yet a customer?' link");
        getDriver().findElement(By.xpath("//*[contains(text(),'Not yet a customer?')]")).click(); //!!!!!
        Thread.sleep(7000);
    }

    @AfterClass
    public void tearDown() {
        WebDriverSingleton.closeDriver();
    }

    @Test (description = "Registration verification - Positive")
    public void userRegistrationPositiveCase() throws InterruptedException {
        System.out.println("Typing data to email field"+validUserName);
        getDriver().findElement(By.id("emailControl")).sendKeys(validUserName);

        System.out.println("Typing data to password field");
        getDriver().findElement(By.id("passwordControl")).sendKeys(password);

        System.out.println("Typing data to Repeat Password field");
        getDriver().findElement(By.id("repeatPasswordControl")).sendKeys(password);

        System.out.println("Selecting data to Security Question field");

        getDriver().findElement(By.xpath("//*[contains(@class,'mat-select-placeholder ng-tns-c138')]")).click();

        getDriver().findElement(By.xpath("//*[contains(text(),\"Mother's maiden name?\")]")).click();

        System.out.println("Typing data to Security Answer field");
        getDriver().findElement(By.id("securityAnswerControl")).sendKeys(answer);

        System.out.println("Clicking Register button");
        getDriver().findElement(By.id("registerButton")).click();
        Thread.sleep(7000);

        System.out.println("Typing user email" + validUserName);
        getDriver().findElement(By.id("email")).sendKeys(validUserName);

        System.out.println("Typing user password"+password);
        getDriver().findElement(By.id("password")).sendKeys(password);

        System.out.println("Clicking on Login button");
        getDriver().findElement(By.id("loginButton")).click();

        System.out.println("Check if user is logged in. Clicking on Account button.");
        getDriver().findElement(By.id("navbarAccount")).click();

        System.out.println("Getting user name from navigation bar");
        Thread.sleep(2000); //добавили ожидание, тк тесты падают из-за того что не успевает прогрузиться элемент, но не советую такое делать без крайней необходимости
        String actualUserName = getDriver().findElement(By.cssSelector("[aria-label='Go to user profile'] span")).getAttribute("innerText").trim();
        Assert.assertEquals(actualUserName, validUserName, "User name does not match");
    }


}

