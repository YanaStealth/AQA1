package io.ctdev.Lesson6_PageObject;

import io.ctdev.framework.model.Customer;
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

public class LoginToJuiceShopNegativePO {

    //private String invalidUserName1 = "yana";
    //  private String password = "qQ2$4";

    private Customer customer;
    private LoginPage loginPage;

    WebDriver driver = getDriver(); //explicit wait
    WebDriverWait wait; //explicit wait


    @BeforeClass
    public void setUp() throws InterruptedException {
        getDriver().get(TestConfig.cfg.baseUrl());

        loginPage.closeDialogWindow();

        wait = new WebDriverWait(driver, 500);

        loginPage.clickOnAccountButton();

        loginPage.clickOnLoginButton();
        customer = Customer.newBuilder().withName("yana").withPassword("qQ2$4").build();
    }

    @AfterClass
    public void tearDown() {
        WebDriverSingleton.closeDriver();
    }


    @Test(description = "Login -Negative Case - Login with empty email field")
    public void loginEmptyEmailFieldValidationNegativeCase() throws InterruptedException {

        loginPage.clickEmptyEmailFieldLoginPage();

        loginPage.clickEmptyPasswordFieldLoginPage();

        String emptyEmailFieldNotification = loginPage.getEmptyEmailFieldNotificationElement();

        Assert.assertEquals(emptyEmailFieldNotification, "Please provide an email address.", "Email field does not have validation for empty value");
    }


    @Test(description = "Login - Negative Case - Login with empty password field")
    public void loginEmptyPasswordFieldValidationNegativeCase() throws InterruptedException {

        System.out.println("Empty password field verification");

        loginPage.clickEmptyPasswordFieldLoginPage();
        loginPage.clickEmptyEmailFieldLoginPage();

        String emptyPasswordFieldNotification = loginPage.getEmptyPasswordFieldNotification();

        Assert.assertEquals(emptyPasswordFieldNotification, "Please provide a password.", "Password field does not have validation for empty value");

    }

    @Test(description = "Login - Negative Case - Login with invalid email and password fields")
    public void loginNotRegisteredUserEmailFieldValidationNegativeCase() throws InterruptedException {

        System.out.println("Invalid email field verification");

        loginPage.enterUserEmail(customer.getEmail());

        loginPage.enterUserPassword(customer.getPassword());

        loginPage.submitLoginForUser();

        String invalidEmailNotification = loginPage.getInvalidEmailNotificationElement();

        Assert.assertEquals(invalidEmailNotification, "Invalid email or password.", "Email and password fields do not have validation for invalid values");

    }

}


