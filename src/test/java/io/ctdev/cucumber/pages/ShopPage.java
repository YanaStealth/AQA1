package io.ctdev.cucumber.pages;

import io.ctdev.framework.config.TestConfig;
import io.ctdev.framework.pages.AbstractPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static io.ctdev.framework.driver.WebDriverSingleton.getDriver;

public class ShopPage extends AbstractPage{
    private WebDriver driver;
    private WebDriverWait wait;

    public ShopPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        this.wait=new WebDriverWait(driver, TIME_OUT);
    }
    @Override
    public void openPage() {
        driver.get(TestConfig.cfg.baseUrl());
    }
    @Step
    public String getTotalMoneyAmountOfBasket() {
        System.out.println("Check the sold Out Product presence in the Basket- Compare the product price and Total amount in the Basket");
        String isPresent = getDriver().findElement(By.xpath("//div[@id='price']")).getText();
        return isPresent;
    }
    @Step
    public void openBasket() {
        System.out.println("Open the Basket");
        getDriver().findElement(By.xpath("//button[@aria-label='Show the shopping cart']")).click();
    }
    @Step
    public void putSoldOutProductToBasket() {
        System.out.println("Put Sold out Product to the Basket");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Sold Out')]/ancestor::mat-card[contains(@class,'mat-card')]//button[@aria-label='Add to Basket']"))).click();
    }
    @Step
    public void clickProceedToNextShopPageButton() {
        System.out.println("Click Next button to proceed to the next Shop page");
        getDriver().findElement(By.xpath("//button[contains(@class,'mat-paginator-navigation-next')]")).click();
    }
    @Step
    public void dismissCookieMessage() {
        System.out.println("Dismiss cookie message");
        getDriver().findElement(By.xpath("//a[@aria-label='dismiss cookie message']")).click();
    }
    @Step
    public void refreshCurrentPage() {
        System.out.println("Refresh the page");
        driver.navigate().refresh();
    }
    @Step
    public String getActualProductPrice() {
        System.out.println("Comparing the Product price with Expected");
        String actualAppleProductPrice = getDriver().findElement(By.xpath("//app-product-details[@class='ng-star-inserted']//p[@class='item-price']")).getText();
        return actualAppleProductPrice;
    }
    @Step
    public String getExpandedProductName() {
        System.out.println("Comparing the Product title with Expected");
        return driver.findElement(By.tagName("h1")).getText();
    }
    @Step
    public void clickApplePomaceProduct() {
        System.out.println("Click Apple Pomace Product");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Apple Pomace')]"))).click();
    }
    @Step
    public void putApplePomaceProductToBasket() {
        System.out.println("Put Apple Pomace Product to the Basket");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='table-container custom-slate']//mat-grid-tile[@class='mat-grid-tile ng-star-inserted'][2]//button[@aria-label='Add to Basket']"))).click();
    }
    @Step
    public void openShippingCart() {
        System.out.println("Open the Shipping Cart");
        getDriver().findElement(By.xpath("//button[@aria-label='Show the shopping cart']")).click();

        driver.navigate().refresh();

        System.out.println("Loading the Shipping Cart");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='mat-focus-indicator mat-icon-button mat-button-base']")));
    }
    @Step
    public String getActualShippingCartElement() {
        String actualShippingCartElement=getDriver().findElement(By.xpath("//mat-cell[contains(text(),'Apple Pomace')]")).getText();
        return actualShippingCartElement;
    }
}
