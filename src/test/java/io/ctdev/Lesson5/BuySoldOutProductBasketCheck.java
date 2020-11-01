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

import java.util.concurrent.TimeUnit;

import static io.ctdev.tests.framework.driver.WebDriverSingleton.getDriver;

public class BuySoldOutProductBasketCheck {
    WebDriver driver = getDriver(); //explicit wait
    WebDriverWait wait; //explicit wait
    String expectedTotalPriceOfBasket = "Total Price: 0¤";
    private String validUserNameLogin = "yana4@gmail.com";
    private String passwordLogin = "qQ2$4";

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
    public void checkPossibilityToPutSoldOutProductToBasket() throws InterruptedException {
        System.out.println("Typing user email" + validUserNameLogin);
        getDriver().findElement(By.id("email")).sendKeys(validUserNameLogin);
        System.out.println("Typing user password" + passwordLogin);
        getDriver().findElement(By.id("password")).sendKeys(passwordLogin);
        System.out.println("Clicking on Login button");
        getDriver().findElement(By.id("loginButton")).click();
        System.out.println("Check if user is logged in. Clicking on Account button.");
        getDriver().findElement(By.id("navbarAccount")).click();
        System.out.println("Getting user name from navigation bar");
        WebElement userNameElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label='Go to user profile'] span")));//explicit wait
        String actualUserName1 = userNameElement.getAttribute("innerText").trim();//explicit wait
        Assert.assertEquals(actualUserName1, validUserNameLogin, "User name does not match");

        System.out.println("Refresh the page to close the window with email");
        driver.navigate().refresh();

        System.out.println("Dismiss cookie message");
        getDriver().findElement(By.xpath("//a[@aria-label='dismiss cookie message']")).click();

        System.out.println("Click Next button and proceed to the 2nd page of the Shop");
        getDriver().findElement(By.xpath("//button[contains(@class,'mat-paginator-navigation-next')]")).click();

        System.out.println("create String with Sold out Product name");
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Sold Out')]/parent::div/following-sibling::div/button[@aria-label='Add to Basket']/ancestor::mat-card//div[@class='item-name']")));
        //String soldOutProductName=getDriver().findElement(By.xpath("//span[contains(text(),'Sold Out')]/parent::div/following-sibling::div/button[@aria-label='Add to Basket']/ancestor::mat-card//div[@class='item-name']")).getText();

        System.out.println("Put Sold out Product to the Basket");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Sold Out')]/ancestor::mat-card[contains(@class,'mat-card')]//button[@aria-label='Add to Basket']"))).click();

        System.out.println("Open the Basket");
        getDriver().findElement(By.xpath("//button[@aria-label='Show the shopping cart']")).click();

        //getDriver().manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
        System.out.println("Check the sold Out Product presence in the Basket");

        String isPresent = getDriver().findElement(By.xpath("//div[@id='price']")).getText();
        Assert.assertEquals(isPresent, expectedTotalPriceOfBasket, "The Sold Out Product is in the Basket");
    }

}
