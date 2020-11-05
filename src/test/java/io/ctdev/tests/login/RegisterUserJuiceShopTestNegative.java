package io.ctdev.tests.login;

import io.ctdev.framework.driver.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.ctdev.framework.driver.WebDriverSingleton.getDriver;

public class RegisterUserJuiceShopTestNegative {

    private String invalidUserName0 = "yan1";
    private String password2 = "qQ2$1";
    private String invalidPassword1 = "1234";
    private String invalidPassword2 = "123456789012345678901";

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

    @Test(description = "Registration - Negative - Validation empty email field.")
    public void userRegistrationEmptyEmailFieldValidationNegativeCase() throws InterruptedException {
        System.out.println("Validation empty email field. Set focus on the email field");
        getDriver().findElement(By.id("emailControl")).click();

        System.out.println("Set focus out of the email field");
        getDriver().findElement(By.id("passwordControl")).click();

        String emptyEmailFieldNotification = getDriver().findElement(By.xpath("//*[contains(text(),'Please provide an email')]")).getAttribute("innerText").trim();
        Assert.assertEquals(emptyEmailFieldNotification, "Please provide an email address.", "Email field does not have validation for empty value");
        Thread.sleep(7000);
    }

    @Test (description = "Registration - Negative - Typing invalid data to email field.")
    public void userRegistrationInvalidDataEmailFieldValidationNegativeCase() throws InterruptedException {
        System.out.println("Typing invalid data to email field" + invalidUserName0);
        getDriver().findElement(By.id("emailControl")).sendKeys(invalidUserName0);
        System.out.println("Set focus out of the email field");
        getDriver().findElement(By.id("passwordControl")).click();
        Thread.sleep(7000);
        System.out.println("Verifying email field validation");
        String invalidEmailNotification = getDriver().findElement(By.xpath("//*[contains(text(),'Email address')]")).getAttribute("innerText").trim();
        Assert.assertEquals(invalidEmailNotification, "Email address is not valid.", "Email field does not have validation for invalid value");
        Thread.sleep(7000);
        getDriver().findElement(By.id("emailControl")).clear();
        Thread.sleep(7000);
    }

    @Test (description = "Registration - Negative - Validation of the empty password field.")
    public void userRegistrationEmptyPasswordFieldValidationNegativeCase() throws InterruptedException {
        System.out.println("Empty password field validation. Set focus on the email field");
        getDriver().findElement(By.id("passwordControl")).click();

        System.out.println("Set focus out of the password field");
        getDriver().findElement(By.id("emailControl")).click();
        Thread.sleep(2000);
        String emptyPasswordFieldNotification = getDriver().findElement(By.xpath("//*[contains(text(),'Please provide a password.')]")).getAttribute("innerText").trim();
        Assert.assertEquals(emptyPasswordFieldNotification, "Please provide a password.", "Password field does not have validation for empty value");
        Thread.sleep(3000);
    }

    @Test (description = "Registration - Negative - Typing invalid data to password field -'1234'.")
    public void userRegistrationShortInvalidDataPasswordFieldValidationNegativeCase2() throws InterruptedException {
        System.out.println("Typing invalid data to password field" + invalidPassword1);
        getDriver().findElement(By.id("passwordControl")).sendKeys(invalidPassword1);
        System.out.println("Set focus out of the password field");
        getDriver().findElement(By.id("emailControl")).click();
        Thread.sleep(3000);
        System.out.println("Verifying password field validation - invalid password"+invalidPassword1);
        String invalidPasswordNotification1 = getDriver().findElement(By.xpath("//*[contains(text(),'Password must be 5-20 characters long.')]")).getAttribute("innerText").trim();
        Assert.assertEquals(invalidPasswordNotification1, "Password must be 5-20 characters long.", "Password field does not have validation for invalid value");
        Thread.sleep(3000);
        getDriver().findElement(By.id("passwordControl")).clear();
        Thread.sleep(3000);
    }

    @Test (description = "Registration - Negative - Typing invalid data to password field -'123456789012345678901'.")
    public void userRegistrationLongInvalidDataPasswordFieldValidationNegativeCase3() throws InterruptedException {
        System.out.println("Typing invalid data to password field" + invalidPassword2);
        getDriver().findElement(By.id("passwordControl")).sendKeys(invalidPassword2);
        System.out.println("Verifying password field validation - invalid password"+invalidPassword2);
        String invalidPasswordNotification2 = getDriver().findElement(By.xpath("//*[contains(text(),'Password must be 5-20 characters long.')]")).getAttribute("innerText").trim();
        Assert.assertEquals(invalidPasswordNotification2, "Password must be 5-20 characters long.", "Password field does not have validation for invalid value");
        Thread.sleep(3000);
        getDriver().findElement(By.id("passwordControl")).clear();
        Thread.sleep(3000);
    }

    @Test (description = "Registration - Negative - Repeat Password field validation verification")
    public void userRegistrationWrongDataRepeatPasswordFieldValidationNegativeCase() throws InterruptedException {
        System.out.println("Typing data that does not match the Password field value to Repeat Password field");
        getDriver().findElement(By.id("passwordControl")).sendKeys(password2);
        getDriver().findElement(By.id("repeatPasswordControl")).sendKeys("1");
        getDriver().findElement(By.id("emailControl")).click();
        System.out.println("Verifying Repeat Password field validation when the passwords do not match");
        String passwordsDoNotMatch = getDriver().findElement(By.xpath("//*[contains(text(),'Passwords do not match')]")).getAttribute("innerText").trim();
        Assert.assertEquals(passwordsDoNotMatch, "Passwords do not match", "Repeat Password field does not have validation for invalid value");
        Thread.sleep(3000);
        getDriver().findElement(By.id("passwordControl")).clear();
        getDriver().findElement(By.id("repeatPasswordControl")).clear();
        Thread.sleep(3000);
    }

    @Test (description = "Registration - Negative - Validation of the empty Security Question field.")
    public void userRegistrationEmptySecurityQuestionFieldNegativeCase1() throws InterruptedException {
        System.out.println("Empty Security Question field validation. Set focus on the email field");
        getDriver().findElement(By.id("securityAnswerControl")).click();
        System.out.println("Set focus out of the Security Question field");
        getDriver().findElement(By.id("emailControl")).click();
        String emptySecurityQuestionFieldNotification = getDriver().findElement(By.xpath("//*[contains(text(),'Please provide an answer to your security question.')]")).getAttribute("innerText").trim();
        Assert.assertEquals(emptySecurityQuestionFieldNotification, "Please provide an answer to your security question.", "Security Question field does not have validation for empty value");
        Thread.sleep(7000);
    }
}
