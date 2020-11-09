package io.ctdev.maven_Lesson8.LoginPositive;

import io.ctdev.framework.config.TestConfig;
import io.ctdev.framework.driver.WebDriverSingleton;
import io.ctdev.framework.model.Customer;
import io.ctdev.framework.pages.login.LoginFluentPage;
import io.ctdev.framework.pages.login.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.ctdev.framework.driver.WebDriverSingleton.getDriver;

@Epic("Signin/SignUp")
@Story("Login")
public class LoginPositiveMaven {

    private Customer customer;

    private WebDriver driver = getDriver(); //explicit wait
    private WebDriverWait wait; //explicit wait
    private LoginPage loginPage;
    private LoginFluentPage fluentPage;

    @BeforeClass
    public void setUp() {

        //getDriver().get(TestConfig.cfg.baseUrl()); //new

        wait = new WebDriverWait(driver, 50); //explicit wait
        customer = Customer.newBuilder().withName("yana4@gmail.com").withPassword("qQ2$4").build();
        fluentPage = new LoginFluentPage(driver);
        loginPage = new LoginPage(driver);
        driver.get(TestConfig.cfg.baseUrl());
        loginPage.closeDialoguePopup();

    }

    @AfterClass
    public void tearDown() {
        WebDriverSingleton.closeDriver();
    }

    @Test(description = "Login verification - Positive")
    @Description("Login verification - Positive")
    public void userLoginVerificationPositiveCase() {

        loginPage.loginFromMainPage(customer);
        String actualUserName = loginPage.getCurrenLoggedInUserName();
        Assert.assertEquals(actualUserName, customer.getEmail(), "User name does not match");
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