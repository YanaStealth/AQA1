package io.ctdev.maven_Lesson8.LoginNegative;

import io.ctdev.framework.config.TestConfig;
import io.ctdev.framework.driver.WebDriverSingleton;
import io.ctdev.framework.model.Customer;
import io.ctdev.framework.pages.login.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.ctdev.framework.driver.WebDriverSingleton.getDriver;

public class LoginToJuiceShopNegativeMaven {

    WebDriver driver = getDriver(); //explicit wait
    WebDriverWait wait; //explicit wait
    private Customer customer;
    private LoginPage loginPage;

    @BeforeClass
    public void setUp() {

        getDriver().get(TestConfig.cfg.baseUrl());

        wait = new WebDriverWait(driver, 500);
        customer = Customer.newBuilder().withName("yana").withPassword("qQ2$4").build();
        loginPage = new LoginPage(driver);
    }

    @AfterClass
    public void tearDown() {
        WebDriverSingleton.closeDriver();
    }



    @Test(description = "Login -Negative Case - Login with empty email field")
    public void loginEmptyEmailFieldValidationNegativeCase()  {
        loginPage.closeDialoguePopup();
        loginPage.clickOnAccountButton();
        loginPage.clickOnLoginButton();
        loginPage.clickEmptyEmailFieldLoginPage();
        loginPage.clickEmptyPasswordFieldLoginPage();
        String emptyEmailFieldNotification = loginPage.getEmptyEmailFieldNotificationElement();
        Assert.assertEquals(emptyEmailFieldNotification, "Please provide an email address.", "Email field does not have validation for empty value");
    }


    @Test(description = "Login - Negative Case - Login with empty password field")
    public void loginEmptyPasswordFieldValidationNegativeCase()  {
        // loginPage.closeDialoguePopup();
        System.out.println("Empty password field verification");
        loginPage.clickOnAccountButton();
        loginPage.clickOnLoginButton();
        loginPage.clickEmptyPasswordFieldLoginPage();
        loginPage.clickEmptyEmailFieldLoginPage();
        String emptyPasswordFieldNotification = loginPage.getEmptyPasswordFieldNotification();
        Assert.assertEquals(emptyPasswordFieldNotification, "Please provide a password.", "Password field does not have validation for empty value");

    }

    @Test(description = "Login - Negative Case - Login with invalid email and password fields")
    public void loginNotRegisteredUserEmailFieldValidationNegativeCase() {
        //  loginPage.closeDialoguePopup();
        System.out.println("Invalid email field verification");
        loginPage.clickOnAccountButton();
        loginPage.clickOnLoginButton();
        loginPage.enterUserEmail(customer.getEmail());
        loginPage.enterUserPassword(customer.getPassword());
        loginPage.submitLoginForUser();

        String invalidEmailNotification = loginPage.getInvalidEmailNotificationElement();

        Assert.assertEquals(invalidEmailNotification, "Invalid email or password.", "Email and password fields do not have validation for invalid values");
    }
}


