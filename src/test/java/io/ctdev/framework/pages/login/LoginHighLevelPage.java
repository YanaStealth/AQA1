package io.ctdev.framework.pages.login;

import io.ctdev.framework.pages.AbstractPage;
import io.ctdev.framework.config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static io.ctdev.framework.driver.WebDriverSingleton.getDriver;

public class LoginHighLevelPage extends AbstractPage {

    //private WebDriver driver;
    private WebDriverWait wait;

    private By navBarAccountElement = By.id("navbarAccount");
    private By goToUserProfileElement = By.cssSelector("[aria-label='Go to user profile'] span");
private By loginButton= By.id("loginButton");
    private By passwordInput = By.id("password");
    private By elemailInput = By.id("email");
    private By loginSubmitButton = By.id("navbarLoginButton");
// когда в локатор у которого текст, мы не знаем точно какой
// private String elementByText = "//*[@text=%s]";


    public LoginHighLevelPage(WebDriver driver){
        super(driver);
      //  this.driver=driver;
        this.wait=new WebDriverWait(driver, TIME_OUT);
    }

    @Override
    public void openPage() {
        driver.get(TestConfig.cfg.baseUrl() + "#/login");
    }

    public String getCurrenLoggedInUserName() {
        System.out.println("Check if user is logged in. Clicking on Account button.");
        //getDriver().findElement(By.id("navbarAccount")).click();
        getDriver().findElement(navBarAccountElement).click();

        System.out.println("Getting user name from navigation bar");
        WebElement userNameElement = wait.until(ExpectedConditions.presenceOfElementLocated(goToUserProfileElement));//explicit wait
        return userNameElement.getAttribute("innerText").trim();//explicit wait
        }

    public void submitLoginForUser() {
        System.out.println("Clicking on Login button");
        getDriver().findElement(loginButton).click();
    }

    public void enterUserPassword(String password) {
        System.out.println("Typing user password"+password);
        //  getDriver().findElement(By.id("password")).sendKeys(passwordLogin);
        getDriver().findElement(passwordInput).sendKeys(password);
    }

    public void enterUserEmail(String email) {
        System.out.println("Typing user email"+email);
        //getDriver().findElement(By.id("email")).sendKeys(validUserNameLogin);
        getDriver().findElement(elemailInput).sendKeys(email);
    }

    public void clickOnLoginButton() {
        System.out.println("Clicking on Login button");
        getDriver().findElement(loginSubmitButton).click();
    }

    public void clickOnAccountButton() {
        System.out.println("Clicking on Account button");
        WebElement element = getDriver().findElement(navBarAccountElement); // можем либо создать объект элемента, либо напрямую его вызвать getDriver().
        element.click();
    }

  /*  Когда нужно найти текст на странице Java преобразует, переменную текст заменит ее %s и можно будет найти текст

  public void searchElementByText(String text) {
        driver.findElement(By.xpath(String.format(elementByText, text)));
    }

Можно найти элемент внутри другого элемента:
Даже если внешний элемент мы искали по css, внутренний можно искать по xpath? когда ищем дочерний элемент нужно ставить
точку перед //
    public void findChildElement() {
        getDriver().findElement(navBarAccountElement).findElement(By.xpath(".//*[text()='Hello']"));
    }
    */


}

