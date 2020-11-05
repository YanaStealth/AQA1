package io.ctdev.Lesson6_PageObject.registrationTests;

import io.ctdev.framework.model.Customer;
import io.ctdev.framework.pages.RegistrationPage.RegistrationPage;
import io.ctdev.framework.config.TestConfig;
import io.ctdev.framework.driver.WebDriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.ctdev.framework.driver.WebDriverSingleton.getDriver;


//WORKS WITHOUT WAITS

public class RegisterUserJuiceShopTestNegativePO {

    WebDriver driver = getDriver(); //explicit wait
    WebDriverWait wait; //explicit wait

    private Customer customer,customer1, customer2, customer3;
    private RegistrationPage registrationPage;

    @BeforeClass
    public void setUp()  {
        getDriver().get(TestConfig.cfg.baseUrl());
        wait = new WebDriverWait(driver, 500); //explicit wait
        customer = Customer.newBuilder().withName("yan1").build();
        customer1 = Customer.newBuilder().withPassword("1234").build();
        customer2 = Customer.newBuilder().withPassword("123456789012345678901").build();
        customer3 = Customer.newBuilder().withPassword("qQ2$1").build();

        registrationPage = new RegistrationPage(driver);
    }

    @AfterClass
    public void tearDown() {
        WebDriverSingleton.closeDriver();
    }

    @AfterMethod
    public void clearFields(){
        registrationPage.clearEmailField();
        registrationPage.clearPasswordField();
        registrationPage.clearRepeatPasswordField();
    }

    @Test(description = "Registration - Negative - Validation empty email field.")
    public void userRegistrationEmptyEmailFieldValidationNegativeCase() {

        registrationPage.goFromLoginToRegisterPageSkipPopup();

        registrationPage.setFocusOnEmailFieldRegistrationForm();
        registrationPage.setFocusOnPasswordFieldRegistrationForm();

        String emptyEmailFieldNotification = registrationPage.getemptyEmailFieldNotification();
        Assert.assertEquals(emptyEmailFieldNotification, "Please provide an email address.", "Email field does not have validation for empty value");
    }



    @Test(description = "Registration - Negative - Typing invalid data to email field.")
    public void userRegistrationInvalidDataEmailFieldValidationNegativeCase() {

        registrationPage.goFromLoginToRegisterPageSkipPopup();

        registrationPage.typeDataToEmailField(customer.getEmail());
        registrationPage.setFocusOnPasswordFieldRegistrationForm();

        String invalidEmailNotification = registrationPage.getInvalidEmailFieldNotification();
        Assert.assertEquals(invalidEmailNotification, "Email address is not valid.", "Email field does not have validation for invalid value");

    }

    @Test(description = "Registration - Negative - Validation of the empty password field.")
    public void userRegistrationEmptyPasswordFieldValidationNegativeCase() {
        registrationPage.goFromLoginToRegisterPageSkipPopup();
        registrationPage.setFocusOnPasswordFieldRegistrationForm();
        registrationPage.setFocusOnEmailFieldRegistrationForm();
        String emptyPasswordFieldNotification = registrationPage.getEmptyPasswordFieldNotification();
        Assert.assertEquals(emptyPasswordFieldNotification, "Please provide a password.", "Password field does not have validation for empty value");
    }


    @Test(description = "Registration - Negative - Typing invalid data to password field -'1234'.")
    public void userRegistrationShortInvalidDataPasswordFieldValidationNegativeCase2() {
        registrationPage.goFromLoginToRegisterPageSkipPopup();
        registrationPage.typeDataToPasswordField(customer1.getPassword());

        registrationPage.setFocusOnEmailFieldRegistrationForm();

        String invalidPasswordNotification1 = registrationPage.getInvalidPasswordNotification();

        Assert.assertEquals(invalidPasswordNotification1, "Password must be 5-20 characters long.", "Password field does not have validation for invalid value");

    }




    @Test(description = "Registration - Negative - Typing invalid data to password field -'123456789012345678901'.")
    public void userRegistrationLongInvalidDataPasswordFieldValidationNegativeCase3() {
        registrationPage.goFromLoginToRegisterPageSkipPopup();
        registrationPage.typeDataToPasswordField(customer2.getPassword());

        String invalidPasswordNotification2 = registrationPage.getInvalidPasswordNotification();

        Assert.assertEquals(invalidPasswordNotification2, "Password must be 5-20 characters long.", "Password field does not have validation for invalid value");
     //   registrationPage.clearPasswordField();

    }

    @Test(description = "Registration - Negative - Repeat Password field validation verification")
    public void userRegistrationWrongDataRepeatPasswordFieldValidationNegativeCase() {
        registrationPage.goFromLoginToRegisterPageSkipPopup();

        registrationPage.typeDataToPasswordField(customer3.getPassword());

        registrationPage.typeDataToRepeatPasswordField(customer1.getPassword());

        registrationPage.setFocusOnEmailFieldRegistrationForm();

        String passwordsDoNotMatch = registrationPage.getPasswordDoNotMatchNotification();
        Assert.assertEquals(passwordsDoNotMatch, "Passwords do not match", "Repeat Password field does not have validation for invalid value");
       // registrationPage.clearPasswordField();
      //  registrationPage.clearRepeatPasswordField();
    }


    @Test(description = "Registration - Negative - Validation of the empty Security Question field.")
    public void userRegistrationEmptySecurityQuestionFieldNegativeCase1() {
        registrationPage.goFromLoginToRegisterPageSkipPopup();

        registrationPage.clickSecurityQuestionField();

        registrationPage.setFocusOnEmailFieldRegistrationForm();

        String emptySecurityQuestionFieldNotification = registrationPage.getEmptySecurityQuestionFieldNotification();
        Assert.assertEquals(emptySecurityQuestionFieldNotification, "Please provide an answer to your security question.", "Security Question field does not have validation for empty value");
    }

}
