package io.ctdev.framework.pages.login;

import io.ctdev.framework.pages.AbstractPage;
import io.ctdev.tests.framework.config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static io.ctdev.tests.framework.driver.WebDriverSingleton.getDriver;

public class LoginPage extends AbstractPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        this.wait=new WebDriverWait(driver, TIME_OUT);
    }

   /* @Override
    public void openPage() {
    driver.get(TestConfig.cfg.baseUrl() + "#/login");
    }
*/
   @Override
   public void openPage() {
       driver.get(TestConfig.cfg.baseUrl());
   }

        public String getCurrenLoggedInUserName() {
        System.out.println("Check if user is logged in. Clicking on Account button.");
        getDriver().findElement(By.id("navbarAccount")).click();
        System.out.println("Getting user name from navigation bar");
        WebElement userNameElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label='Go to user profile'] span")));//explicit wait
        String actualUserName1 = userNameElement.getAttribute("innerText").trim();//explicit wait
        return actualUserName1;
    }

    public void submitLoginForUser() {
        System.out.println("Clicking on Login button");
        getDriver().findElement(By.id("loginButton")).click();
    }

    public void enterUserPassword(String password) {
        System.out.println("Typing user password"+password);
        getDriver().findElement(By.id("password")).sendKeys(password);
    }

    public void enterUserEmail(String email) {
        System.out.println("Typing user email"+email);
        getDriver().findElement(By.id("email")).sendKeys(email);
    }

    public void clickOnLoginButton() {
        System.out.println("Clicking on Login button");
        getDriver().findElement(By.id("navbarLoginButton")).click();
    }

    public void clickOnAccountButton() {
        System.out.println("Clicking on Account button");
        WebElement element = getDriver().findElement(By.id("navbarAccount")); // можем либо создать объект элемента, либо напрямую его вызвать getDriver().
        element.click();
    }
    public void clickEmptyPasswordFieldLoginPage() {
        System.out.println("Click empty password field");
        getDriver().findElement(By.id("password")).click();
    }

    public void clickEmptyEmailFieldLoginPage() {
        System.out.println("Click Empty email field");
        getDriver().findElement(By.id("email")).click();
    }

    public String getEmptyEmailFieldNotificationElement() {
        WebElement emptyEmailFieldNotificationElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Please provide an email address')]"))); //explicit wait
        return emptyEmailFieldNotificationElement.getAttribute("innerText").trim();
    }

    public String getEmptyPasswordFieldNotification() {
        WebElement emptyPasswordFieldNotificationElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Please provide a password')]"))); //explicit wait
        return emptyPasswordFieldNotificationElement.getAttribute("innerText").trim();
    }
    public String getInvalidEmailNotificationElement() {
        WebElement invalidEmailNotificationElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Invalid email or password.')]"))); //explicit wait
        String invalidEmailNotification = invalidEmailNotificationElement.getAttribute("innerText").trim(); //explicit wait
        return invalidEmailNotification;
    }
}
