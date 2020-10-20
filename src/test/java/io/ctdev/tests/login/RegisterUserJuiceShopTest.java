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

public class RegisterUserJuiceShopTest {
    private String validUserName = "yana4@gmail.com";
    private String invalidUserName1 = "yana";
    private String password = "qQ2$4";
    private String invalidPassword1 = "1234";
    private String invalidPassword2 = "123456789012345678901";
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

    @Test (description = "Validation empty email field.")
    public void userRegistrationEmailFieldNegativeCase1() throws InterruptedException {
        System.out.println("Validation empty email field. Set focus on the email field");
        getDriver().findElement(By.id("emailControl")).click();

        System.out.println("Set focus out of the email field");
        getDriver().findElement(By.id("passwordControl")).click();

        String emptyEmailFieldNotification = getDriver().findElement(By.xpath("//*[contains(text(),'Please provide an email')]")).getAttribute("innerText").trim();
        Assert.assertEquals(emptyEmailFieldNotification, "Please provide an email address.", "Email field does not have validation for empty value");
        Thread.sleep(7000);
    }

    @Test (description = "Typing invalid data to email field.")
    public void userRegistrationEmailFieldNegativeCase2() throws InterruptedException {
        System.out.println("Typing invalid data to email field" + invalidUserName1);
        getDriver().findElement(By.id("emailControl")).sendKeys(invalidUserName1);
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

    @Test (description = "Validation of the empty password field.")
    public void userRegistrationPasswordFieldNegativeCase1() throws InterruptedException {
        System.out.println("Empty password field validation. Set focus on the email field");
        getDriver().findElement(By.id("passwordControl")).click();

        System.out.println("Set focus out of the password field");
        getDriver().findElement(By.id("emailControl")).click();
        Thread.sleep(2000);
        String emptyPasswordFieldNotification = getDriver().findElement(By.xpath("//*[contains(text(),'Please provide a password.')]")).getAttribute("innerText").trim();
        Assert.assertEquals(emptyPasswordFieldNotification, "Please provide a password.", "Password field does not have validation for empty value");
        Thread.sleep(3000);
    }

    @Test (description = "Typing invalid data to password field -'1234'.")
    public void userRegistrationPasswordFieldNegativeCase2() throws InterruptedException {
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

    @Test (description = "Typing invalid data to password field -'123456789012345678901'.")
    public void userRegistrationPasswordFieldNegativeCase3() throws InterruptedException {
        System.out.println("Typing invalid data to password field" + invalidPassword2);
        getDriver().findElement(By.id("passwordControl")).sendKeys(invalidPassword2);
        System.out.println("Verifying password field validation - invalid password"+invalidPassword2);
        String invalidPasswordNotification2 = getDriver().findElement(By.xpath("//*[contains(text(),'Password must be 5-20 characters long.')]")).getAttribute("innerText").trim();
        Assert.assertEquals(invalidPasswordNotification2, "Password must be 5-20 characters long.", "Password field does not have validation for invalid value");
        Thread.sleep(3000);
        getDriver().findElement(By.id("passwordControl")).clear();
        Thread.sleep(3000);
    }

    @Test (description = "Repeat Password field validation verification")
    public void userRegistrationRepeatPasswordFieldNegativeCase() throws InterruptedException {
        System.out.println("Typing data that does not match the Password field value to Repeat Password field");
        getDriver().findElement(By.id("passwordControl")).sendKeys(password);
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

    @Test (description = "Validation of the empty Security Question field.")
    public void userRegistrationSecurityQuestionFieldNegativeCase1() throws InterruptedException {
        System.out.println("Empty Security Question field validation. Set focus on the email field");
        getDriver().findElement(By.id("securityAnswerControl")).click();
        System.out.println("Set focus out of the Security Question field");
        getDriver().findElement(By.id("emailControl")).click();
        String emptySecurityQuestionFieldNotification = getDriver().findElement(By.xpath("//*[contains(text(),'Please provide an answer to your security question.')]")).getAttribute("innerText").trim();
        Assert.assertEquals(emptySecurityQuestionFieldNotification, "Please provide an answer to your security question.", "Security Question field does not have validation for empty value");
        Thread.sleep(7000);
    }

    @Test (description = "Registration verification")
    public void userRegistration() throws InterruptedException {
        System.out.println("Typing data to email field"+validUserName);
        getDriver().findElement(By.id("emailControl")).sendKeys(validUserName);

        System.out.println("Typing data to password field");
        getDriver().findElement(By.id("passwordControl")).sendKeys(password);

        System.out.println("Typing data to Repeat Password field");
        getDriver().findElement(By.id("repeatPasswordControl")).sendKeys(password);

        System.out.println("Selecting data to Security Question field");
      //  getDriver().findElement(By.xpath("//*[contains(text(),'mat-select-placeholder ng-tns-c138')]")).click();
        getDriver().findElement(By.xpath("//*[contains(@class,'mat-select-placeholder ng-tns-c138')]")).click();
        Thread.sleep(2000);
       getDriver().findElement(By.xpath("//*[contains(text(),' maiden name?')]")).click();
      //  getDriver().findElement(By.xpath("//*[contains(@class,'mat-option mat-focus-indicator ng-tns-c138-15 ng-star-inserted')]")).click();

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

    @Test (description = "Login with empty email field")
    public void emptryEmailFieldLogin() throws InterruptedException {
        System.out.println("Open Login page");
        getDriver().get("http://3.18.213.48/#/login");
        Thread.sleep(2000);
        System.out.println("Empty email field verification");
        getDriver().findElement(By.id("email")).click();
        getDriver().findElement(By.id("password")).click();
        Thread.sleep(2000);
        String emptyEmailFieldNotification = getDriver().findElement(By.xpath("//*[contains(text(),'Please provide an email address')]")).getAttribute("innerText").trim();
        Assert.assertEquals(emptyEmailFieldNotification, "Please provide an email address.", "Email field does not have validation for empty value");
        Thread.sleep(2000);
    }

    @Test (description = "Login with empty password field")
    public void emptryPasswordFieldLogin() throws InterruptedException {
        System.out.println("Open Login page");
        getDriver().get("http://3.18.213.48/#/login");
        Thread.sleep(2000);
        System.out.println("Empty password field verification");
        getDriver().findElement(By.id("password")).click();
        getDriver().findElement(By.id("email")).click();
        Thread.sleep(2000);
        String emptyPasswordFieldNotification = getDriver().findElement(By.xpath("//*[contains(text(),'Please provide a password')]")).getAttribute("innerText").trim();
        Assert.assertEquals(emptyPasswordFieldNotification, "Please provide a password.", "Password field does not have validation for empty value");
        Thread.sleep(2000);
    }

    @Test (description = "Login with invalid email and password fields")
    public void invalidEmailFieldLogin() throws InterruptedException {
        System.out.println("Open Login page");
        getDriver().get("http://3.18.213.48/#/login");
        Thread.sleep(2000);
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

    @Test (description = "Login verification")
    public void userLogin() throws InterruptedException {
        System.out.println("Open Login page");
        getDriver().get("http://3.18.213.48/#/login");
        Thread.sleep(2000);
        System.out.println("Typing user email" + validUserName);
        getDriver().findElement(By.id("email")).sendKeys(validUserName);
        Thread.sleep(2000);
        System.out.println("Typing user password"+password);
        getDriver().findElement(By.id("password")).sendKeys(password);

        System.out.println("Clicking on Login button");
        getDriver().findElement(By.id("loginButton")).click();
        Thread.sleep(2000);
        System.out.println("Check if user is logged in. Clicking on Account button.");
        getDriver().findElement(By.id("navbarAccount")).click();
        Thread.sleep(2000);
        System.out.println("Getting user name from navigation bar");
        Thread.sleep(2000);
        String actualUserName1 = getDriver().findElement(By.cssSelector("[aria-label='Go to user profile'] span")).getAttribute("innerText").trim();
        Assert.assertEquals(actualUserName1, validUserName, "User name does not match");
    }


}

