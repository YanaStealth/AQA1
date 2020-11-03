package io.ctdev.Lesson6_PageObject.shopTests;

import io.ctdev.framework.model.Customer;
import io.ctdev.framework.pages.login.LoginPage;
import io.ctdev.framework.pages.shop.ShopPage;
import io.ctdev.tests.framework.config.TestConfig;
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

public class BuySoldOutProductBasketCheckPO {
    WebDriver driver = getDriver(); //explicit wait
    WebDriverWait wait; //explicit wait
    private Customer customer;
    private LoginPage loginPage;
    private ShopPage shopPage;

    /*
    String expectedTotalPriceOfBasket = "Total Price: 0¤";
    private String validUserNameLogin = "yana4@gmail.com";
    private String passwordLogin = "qQ2$4"; */

    @BeforeClass
    public void setUp() throws InterruptedException {
        getDriver().get(TestConfig.cfg.baseUrl());
        getDriver().findElement(By.cssSelector("[class*='close-dialog']")).click();
        wait = new WebDriverWait(driver, 500); //explicit wait
        customer = Customer.newBuilder().withName("yana4@gmail.com").withPassword("qQ2$4").build();
        loginPage = new LoginPage(driver);
        shopPage = new ShopPage(driver);
    }

    @AfterClass
    public void tearDown() {
        WebDriverSingleton.closeDriver();
    }

    @Test(description = "Login verification - Positive")
    public void checkPossibilityToPutSoldOutProductToBasket() throws InterruptedException {
        loginPage.clickOnAccountButton();
        loginPage.clickOnLoginButton();
        loginPage.enterUserEmail(customer.getEmail());
        loginPage.enterUserPassword(customer.getPassword());
        loginPage.submitLoginForUser();
        String actualUserName1 = loginPage.getCurrenLoggedInUserName();
        Assert.assertEquals(actualUserName1, customer.getEmail(), "User name does not match");

        shopPage.refreshCurrentPage();

        shopPage.dismissCookieMessage();

        shopPage.clickProceedToNextShopPageButton();

        shopPage.putSoldOutProductToBasket();

        shopPage.openBasket();

        String isPresent = shopPage.getTotalMoneyAmountOfBasket();

        Assert.assertEquals(isPresent, "Total Price: 0¤", "The Sold Out Product is in the Basket");
    }

}
