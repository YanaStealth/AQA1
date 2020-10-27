package io.ctdev.tests.login;

import io.ctdev.tests.framework.driver.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static io.ctdev.tests.framework.driver.WebDriverSingleton.getDriver;

public class LoginToJuiceShopPositive {

    //private String validUserNameLogin = "yana4@gmail.com";
    private String passwordLogin = "qQ2$4";


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

    @Test(description = "Login verification - Positive")
    public void userLoginVerificationPositiveCase() throws InterruptedException {

        // create a string of all characters
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        // create random string builder
        StringBuilder sb = new StringBuilder();

        // create an object of Random class
        Random random = new Random();

        // specify length of random string
        int length = 7;

        for (int i = 0; i < length; i++) {

            // generate random index number
            int index = random.nextInt(alphabet.length());

            // get character specified by index
            // from the string
            char randomChar = alphabet.charAt(index);

            // append the character to string builder
            sb.append(randomChar);
        }

        String randomString = sb.toString();
        System.out.println("Random String is: " + randomString);

        private String validUserNameLogin = randomString + "@gmail.com";
        System.out.println("Typing user email" + validUserNameLogin);
        getDriver().findElement(By.id("email")).sendKeys(validUserNameLogin);
        Thread.sleep(2000);
        System.out.println("Typing user password"+passwordLogin);
        getDriver().findElement(By.id("password")).sendKeys(passwordLogin);

        System.out.println("Clicking on Login button");
        getDriver().findElement(By.id("loginButton")).click();
        Thread.sleep(2000);
        System.out.println("Check if user is logged in. Clicking on Account button.");
        getDriver().findElement(By.id("navbarAccount")).click();
        Thread.sleep(2000);
        System.out.println("Getting user name from navigation bar");
        Thread.sleep(2000);
        String actualUserName1 = getDriver().findElement(By.cssSelector("[aria-label='Go to user profile'] span")).getAttribute("innerText").trim();
        Assert.assertEquals(actualUserName1, validUserNameLogin, "User name does not match");
    }
}
