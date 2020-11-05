
package io.ctdev.Lesson6_PageObject.shopTests;

import io.ctdev.framework.model.Customer;
import io.ctdev.framework.model.Product;
import io.ctdev.framework.pages.login.LoginPage;
import io.ctdev.framework.pages.shop.ShopPage;
import io.ctdev.framework.config.TestConfig;
import io.ctdev.framework.driver.WebDriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.ctdev.framework.driver.WebDriverSingleton.getDriver;

public class CheckApplePomaceProductContentPO {

    WebDriver driver = getDriver(); //explicit wait
    WebDriverWait wait; //explicit wait
    private Product product;
    private Customer customer;
    private LoginPage loginPage;
    private ShopPage shopPage;

    @BeforeClass
    public void setUp() {
        getDriver().get(TestConfig.cfg.baseUrl());

        wait = new WebDriverWait(driver, 500); //explicit wait
        customer = Customer.newBuilder().withName("yana4@gmail.com").withPassword("qQ2$4").build();
        product = Product.newBuilder().withProductName("Apple Pomace").withPrice("0.89¤").build();
        loginPage = new LoginPage(driver);
        shopPage = new ShopPage(driver);

    }

    @AfterClass
    public void tearDown() {
        WebDriverSingleton.closeDriver();
    }

    @Test(description = "Login verification - Positive")
    public void checkApplePomaceProductContent() {
        loginPage.closeDialoguePopup();

        // loginPage.loginFromMainPageNCheckIfUserIsLoggedIn(customer.getEmail(), customer.getPassword());
        loginPage.loginFromMainPageNCheckIfUserIsLoggedIn(customer);

        shopPage.refreshCurrentPage();

        shopPage.clickApplePomaceProduct();

        String actualExpandedProductTitle = shopPage.getExpandedProductName();

        Assert.assertEquals(actualExpandedProductTitle, product.getName(), "Product Title does not match expected Title");

        String actualAppleProductPrice = shopPage.getActualProductPrice();

        Assert.assertEquals(actualAppleProductPrice, product.getPrice(), "Product Price does not match expected Price");

    }

}
