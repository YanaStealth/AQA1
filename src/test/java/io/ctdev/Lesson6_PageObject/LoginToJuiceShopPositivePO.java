package io.ctdev.Lesson6_PageObject;

import io.ctdev.framework.model.Customer;
import io.ctdev.framework.pages.login.LoginFluentPage;
import io.ctdev.framework.pages.login.LoginPage;
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

public class LoginToJuiceShopPositivePO {

    private Customer customer;
   // private Customer customer2;

    // private String validUserNameLogin = "yana4@gmail.com";
    // private String passwordLogin = "qQ2$4";
    private WebDriver driver = getDriver(); //explicit wait
    private WebDriverWait wait; //explicit wait
    private LoginPage loginPage;
        private LoginFluentPage fluentPage;

    @BeforeClass
    public void setUp() {

       getDriver().get(TestConfig.cfg.baseUrl()); //new

       loginPage.closeDialogWindow();

        wait = new WebDriverWait(driver, 50); //explicit wait
        customer = Customer.newBuilder().withName("yana4@gmail.com").withPassword("qQ2$4").build();
       // customer2 = Customer.newBuilder().withName("yana4").build;
        loginPage = new LoginPage(driver);
                fluentPage = new LoginFluentPage(driver);
    }

    @AfterClass
    public void tearDown() {
        WebDriverSingleton.closeDriver();
    }

    @Test(description = "Login verification - Positive")
    public void userLoginVerificationPositiveCase() throws InterruptedException {

        loginPage.clickOnAccountButton();

        loginPage.clickOnLoginButton();

        loginPage.enterUserEmail(customer.getEmail());

        loginPage.enterUserPassword(customer.getPassword());

        loginPage.submitLoginForUser();

        String actualUserName1 = loginPage.getCurrenLoggedInUserName();

        Assert.assertEquals(actualUserName1, customer.getEmail(), "User name does not match");
    }
/*
    @Test(description = "Login verification - Positive")
    public void userIsAbleToLoginToShopPositiveCaseWithFluentInterface() {

        //можно подряд создать цепочку методов которые вызываются

        String actualUserName1 = fluentPage.clickOnAccountButton().clickOnLoginButton().enterUserEmail(customer.getEmail()).
                enterUserPassword(customer.getPassword()).submitLoginForUser().getCurrenLoggedInUserName();

        Assert.assertEquals(actualUserName1, customer.getEmail(), "User name does not match");
    }
    */

}
