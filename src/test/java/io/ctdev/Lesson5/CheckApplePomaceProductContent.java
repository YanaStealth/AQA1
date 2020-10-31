package io.ctdev.Lesson5;

import io.ctdev.tests.framework.driver.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.ctdev.tests.framework.driver.WebDriverSingleton.getDriver;

public class CheckApplePomaceProductContent {
    private String validUserNameLogin = "yana4@gmail.com";
    private String passwordLogin = "qQ2$4";
    private String validAppleProductTitle = "Apple Pomace";
    private String validAppleProductPrice ="0.89¤";
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
    public void checkProductCarrotJuiceContent() throws InterruptedException {
        System.out.println("Typing user email" + validUserNameLogin);
        getDriver().findElement(By.id("email")).sendKeys(validUserNameLogin);
        System.out.println("Typing user password"+passwordLogin);
        getDriver().findElement(By.id("password")).sendKeys(passwordLogin);
        System.out.println("Clicking on Login button");
        getDriver().findElement(By.id("loginButton")).click();


        System.out.println("Check if user is logged in. Clicking on Account button.");
        getDriver().findElement(By.id("navbarAccount")).click();
        System.out.println("Getting user name from navigation bar");
        WebElement userNameElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label='Go to user profile'] span")));//explicit wait
        String actualUserName1 = userNameElement.getAttribute("innerText").trim();
        Assert.assertEquals(actualUserName1, validUserNameLogin, "User name does not match");

        System.out.println("Refresh the page to close the window with email");
        driver.navigate().refresh();

       // getDriver().findElement(By.xpath(".//*[text()='Apple Pomace']")).click(); - не сработало
        //getDriver().findElement(By.cssSelector("img[alt='Apple Pomace']")).click();
        //getDriver().findElement(By.cssSelector("[src&='apple_pressings.jpg']")).click();
        //getDriver().findElement(By.cssSelector("img[alt='Apple Pomace']")).click();
        // getDriver().findElement(By.xpath(".//*img[contains(@src,'apple_pressings.jpg']")).click();
        // getDriver().findElement(By.xpath("//*[contains(text(),'Apple Pomace')]")).click();
//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'What did you like or dislike?')]")));

        System.out.println("Click Apple Pomace Product");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Apple Pomace')]"))).click();
      //  Thread.sleep(3000);

        System.out.println("Comparing the Product title with Expected");
        String acualAppleProductTitle=driver.findElement(By.tagName("h1")).getText();
        Assert.assertEquals(acualAppleProductTitle, validAppleProductTitle, "Product Title does not match expected Title");

        System.out.println("Comparing the Product price with Expected");
        String actualAppleProductPrice=getDriver().findElement(By.xpath("//app-product-details[@class='ng-star-inserted']//p[@class='item-price']")).getText();
        Assert.assertEquals(actualAppleProductPrice, validAppleProductPrice, "Product Price does not match expected Price");

 /*       String actualAppleProductPrice=getDriver().findElement(By.xpath("//app-product-details[@class='ng-star-inserted']//p[@class='item-price']")).getText();
//app-product-details[@class='ng-star-inserted']//h1[contains(text(),'Apple Pomace')]//p[@class='item-price']


        WebElement userNameElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label='Go to user profile'] span")));//explicit wait
        String actualUserName1 = userNameElement.getAttribute("innerText").trim();
*/
    }
}
