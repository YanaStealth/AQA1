package io.ctdev.Lesson6_PageObject.registrationTests;

import io.ctdev.framework.model.Customer;
import io.ctdev.framework.pages.RegistrationPage.RegistrationPage;
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

import java.util.Random;

import static io.ctdev.tests.framework.driver.WebDriverSingleton.getDriver;

public class RegisterUserJuiceShopTestPositivePO {

    WebDriver driver = getDriver(); //explicit wait
    WebDriverWait wait; //explicit wait
    private Customer customer;
    private RegistrationPage registrationPage;

   // private String password = "qQ2$4";
   // private String answer = "Coopert";


    @BeforeClass
    public void setUp() {
        getDriver().get(TestConfig.cfg.baseUrl());
        wait = new WebDriverWait(driver, 500); //explicit wait
        registrationPage = new RegistrationPage(driver);
    }

    @AfterClass
    public void tearDown() {
        WebDriverSingleton.closeDriver();
    }

    @Test(description = "Registration verification - Positive")
    public void userRegistrationPositiveCase()  {
        String randomEmail = registrationPage.createRandomEmail();
        customer = Customer.newBuilder().withName(randomEmail).withPassword("qQ2$4").withSecurityAnswer("Coopert").build();
        registrationPage.goFromLoginToRegisterPageSkipPopup();

        registrationPage.typeDataToEmailField(customer.getEmail());
        registrationPage.typeDataToPasswordField(customer.getPassword());
        registrationPage.typeDataToRepeatPasswordField(customer.getPassword());
        registrationPage.clickSecurityQuestionField();

        registrationPage.selectMothersMaidenNameSequrityQuestion();

        registrationPage.typeSecurityAnswer(customer.getSecurityAnswer());

        registrationPage.clickRegisterButoon();

        registrationPage.waitLoginPageLoading();

        registrationPage.enterUserEmail(customer.getEmail());
        registrationPage.enterUserPassword(customer.getPassword());
        registrationPage.submitLoginForUser();
        String actualUserName = registrationPage.getCurrenLoggedInUserName();
        Assert.assertEquals(actualUserName, randomEmail, "User name does not match");
    }




}
