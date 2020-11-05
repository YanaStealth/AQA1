package io.ctdev.framework.pages.RegistrationPage;

import io.ctdev.framework.pages.AbstractPage;
import io.ctdev.framework.config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

import static io.ctdev.framework.driver.WebDriverSingleton.getDriver;

public class RegistrationPage extends AbstractPage {
    private WebDriver driver;
    private WebDriverWait wait;


    public RegistrationPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        this.wait=new WebDriverWait(driver, TIME_OUT);
    }
    @Override
    public void openPage() {
        driver.get(TestConfig.cfg.baseUrl());
    }

    public void goFromLoginToRegisterPageSkipPopup() {
        closeDialoguePopup();
        clickOnAccountButton();
        wait = new WebDriverWait(driver, 500);
        clickOnLoginButton();
        wait = new WebDriverWait(driver, 500);
        clickNotYetACustomerLinkToProceedToRegistrationPage();
    }

    public void closeDialoguePopup() {
        getDriver().findElement(By.cssSelector("[class*='close-dialog']")).click();
        wait = new WebDriverWait(driver, 500); //explicit wait
    }
    public void clickOnAccountButton() {
        System.out.println("Clicking on Account button");
        WebElement element = getDriver().findElement(By.id("navbarAccount")); // можем либо создать объект элемента, либо напрямую его вызвать getDriver().
        element.click();
    }
    public void clickOnLoginButton() {
        System.out.println("Clicking on Login button");
        getDriver().findElement(By.id("navbarLoginButton")).click();
    }
    public void clickNotYetACustomerLinkToProceedToRegistrationPage() {
        System.out.println("Clicking 'Not yet a customer?' link");
        getDriver().findElement(By.xpath("//*[contains(text(),'Not yet a customer?')]")).click();
    }





    public String getemptyEmailFieldNotification() {
        return getDriver().findElement(By.xpath("//*[contains(text(),'Please provide an email')]")).getAttribute("innerText").trim();
    }

    public void setFocusOnPasswordFieldRegistrationForm() {
        System.out.println("Set focus on the password field");
        getDriver().findElement(By.id("passwordControl")).click();
    }




    public void setFocusOnEmailFieldRegistrationForm() {
        System.out.println("Validation empty email field. Set focus on the email field");
        getDriver().findElement(By.id("emailControl")).click();
    }
    public void typeDataToEmailField(String email) {
        System.out.println("Typing data to email field" + email);
        getDriver().findElement(By.id("emailControl")).sendKeys(email);
    }
    public String getInvalidEmailFieldNotification() {
        System.out.println("Verifying email field validation");
        return getDriver().findElement(By.xpath("//*[contains(text(),'Email address')]")).getAttribute("innerText").trim();
    }
    public void clearEmailField() {
        getDriver().findElement(By.id("emailControl")).clear();
    }
    public String getEmptyPasswordFieldNotification() {
        return getDriver().findElement(By.xpath("//*[contains(text(),'Please provide a password.')]")).getAttribute("innerText").trim();
    }

    public void typeDataToPasswordField(String password) {
        System.out.println("Typing invalid data to password field" + password);
        getDriver().findElement(By.id("passwordControl")).sendKeys(password);
    }
    public String getInvalidPasswordNotification() {
        System.out.println("Verifying password field validation - invalid password");
        return getDriver().findElement(By.xpath("//*[contains(text(),'Password must be 5-20 characters long.')]")).getAttribute("innerText").trim();
    }

    public void typeDataToRepeatPasswordField(String password) {
        getDriver().findElement(By.id("repeatPasswordControl")).sendKeys(password);
    }
    public String getPasswordDoNotMatchNotification() {
        System.out.println("Verifying Repeat Password field validation when the passwords do not match");
        String passwordsDoNotMatch = getDriver().findElement(By.xpath("//*[contains(text(),'Passwords do not match')]")).getAttribute("innerText").trim();
        return passwordsDoNotMatch;
    }

    public void clearPasswordField() {
        getDriver().findElement(By.id("passwordControl")).clear();
    }
    public void clearRepeatPasswordField() {
        getDriver().findElement(By.id("repeatPasswordControl")).clear();
    }

    public void clickSecurityQuestionField() {
        System.out.println("Click Security Question field.");
        getDriver().findElement(By.id("securityAnswerControl")).click();
    }
    public String getEmptySecurityQuestionFieldNotification() {
        String emptySecurityQuestionFieldNotification = getDriver().findElement(By.xpath("//*[contains(text(),'Please provide an answer to your security question.')]")).getAttribute("innerText").trim();
        return emptySecurityQuestionFieldNotification;
    }

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

    public void selectMothersMaidenNameSequrityQuestion() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),\"Mother's maiden name?\")]"))).click(); //explicit wait
    }

    public void typeSecurityAnswer(String securityAnswer) {
        System.out.println("Typing data to Security Answer field");
        getDriver().findElement(By.id("securityAnswerControl")).sendKeys();
    }

    public void waitLoginPageLoading() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Not yet a customer?')]"))); //explicit wait
    }

    public void clickRegisterButoon() {
        System.out.println("Clicking Register button");
        getDriver().findElement(By.id("registerButton")).click();
    }


    public void enterUserEmail(String email) {
        System.out.println("Typing user email"+email);
        getDriver().findElement(By.id("email")).sendKeys(email);
    }
    public void enterUserPassword(String password) {
        System.out.println("Typing user password"+password);
        getDriver().findElement(By.id("password")).sendKeys(password);
    }
    public void submitLoginForUser() {
        System.out.println("Clicking on Login button");
        getDriver().findElement(By.id("loginButton")).click();
    }

    public String getCurrenLoggedInUserName() {
        System.out.println("Check if user is logged in. Clicking on Account button.");
        getDriver().findElement(By.id("navbarAccount")).click();
        System.out.println("Getting user name from navigation bar");
        WebElement userNameElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label='Go to user profile'] span")));//explicit wait
        String actualUserName1 = userNameElement.getAttribute("innerText").trim();//explicit wait
        return actualUserName1;
    }
}
