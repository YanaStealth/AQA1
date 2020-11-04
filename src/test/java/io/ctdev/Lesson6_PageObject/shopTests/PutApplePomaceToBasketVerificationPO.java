
package io.ctdev.Lesson6_PageObject.shopTests;

import io.ctdev.framework.model.Customer;
import io.ctdev.framework.pages.login.LoginPage;
import io.ctdev.framework.pages.shop.ShopPage;
import io.ctdev.tests.framework.config.TestConfig;
import io.ctdev.tests.framework.driver.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.ctdev.tests.framework.driver.WebDriverSingleton.getDriver;

public class PutApplePomaceToBasketVerificationPO {
    WebDriver driver = getDriver(); //explicit wait
    WebDriverWait wait; //explicit wait

    private String expectedApplePomaceElement = "Apple Pomace";

    private Customer customer;
    private LoginPage loginPage;
    private ShopPage shopPage;

    @BeforeClass
    public void setUp() {
        getDriver().get(TestConfig.cfg.baseUrl());
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
    public void applePomaceProductPresenceInBasketVerification() {
        loginPage.closeDialoguePopup();
        //loginPage.loginFromMainPageNCheckIfUserIsLoggedIn(customer.getEmail(), customer.getPassword());

        loginPage.loginFromMainPageNCheckIfUserIsLoggedIn(customer);
        driver.navigate().refresh();

        shopPage.putApplePomaceProductToBasket();

        shopPage.openShippingCart();

        String actualShippingCartElement = shopPage.getActualShippingCartElement();

        Assert.assertEquals(actualShippingCartElement, expectedApplePomaceElement, "The Product Apple Pomace is not in the Basket");
    }
}

