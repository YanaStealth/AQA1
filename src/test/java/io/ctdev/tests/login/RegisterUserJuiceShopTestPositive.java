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

public class RegisterUserJuiceShopTestPositive {
    // private String validUserName = "yana6@gmail.com";
    private String password = "qQ2$4";
    private String answer = "Coopert";

    public String createRandomEmail() {
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
        String randomEmail = randomString + "@gmail.com";
        return randomEmail;
    }

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

    @Test(description = "Registration verification - Positive")
    public void userRegistrationPositiveCase() throws InterruptedException {
        String userEmail = createRandomEmail();

        System.out.println("Typing data to email field" + userEmail);
        getDriver().findElement(By.id("emailControl")).sendKeys(userEmail);

        System.out.println("Typing data to password field");
        getDriver().findElement(By.id("passwordControl")).sendKeys(password);

        System.out.println("Typing data to Repeat Password field");
        getDriver().findElement(By.id("repeatPasswordControl")).sendKeys(password);

        System.out.println("Selecting data to Security Question field");

        //getDriver().findElement(By.xpath("//*[contains(@class,'mat-select-placeholder ng-tns-c138')]")).click();
        getDriver().findElement(By.xpath("//*[contains(@class,'mat-form-field-flex ng-tns-c123-16')]")).click();

        getDriver().findElement(By.xpath("//*[contains(text(),\"Mother's maiden name?\")]")).click();

        System.out.println("Typing data to Security Answer field");
        getDriver().findElement(By.id("securityAnswerControl")).sendKeys(answer);

        System.out.println("Clicking Register button");
        getDriver().findElement(By.id("registerButton")).click();
        Thread.sleep(7000);

        System.out.println("Typing user email" + userEmail);
        getDriver().findElement(By.id("email")).sendKeys(userEmail);

        System.out.println("Typing user password" + password);
        getDriver().findElement(By.id("password")).sendKeys(password);

        System.out.println("Clicking on Login button");
        getDriver().findElement(By.id("loginButton")).click();

        System.out.println("Check if user is logged in. Clicking on Account button.");
        getDriver().findElement(By.id("navbarAccount")).click();

        System.out.println("Getting user name from navigation bar");
        Thread.sleep(5000); //добавили ожидание, тк тесты падают из-за того что не успевает прогрузиться элемент, но не советую такое делать без крайней необходимости
        String actualUserName = getDriver().findElement(By.cssSelector("[aria-label='Go to user profile'] span")).getAttribute("innerText").trim();
        Assert.assertEquals(actualUserName, userEmail, "User name does not match");
    }


}

