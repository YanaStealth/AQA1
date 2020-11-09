package io.ctdev.cucumber.stepdef;

import io.ctdev.cucumber.pages.LoginPage;
import io.ctdev.framework.driver.WebDriverSingleton;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginStepdefs {

    private LoginPage loginPage = new LoginPage(WebDriverSingleton.getDriver());

    @When("user opens login page")
    public void userOpensLoginPage() {
        loginPage.openPage();
    }

    @When("user enters login {string} and password {string}")
    public void userEntersLoginAndPassword(String login, String password) {
        loginPage.enterUserEmail(login);
        loginPage.enterUserPassword(password);
    }

    @When("user clicks on login button")
    public void userClicksOnLoginButton() {
        loginPage.submitLoginForUser();
    }

    @Then("user {string} should be logged to application")
    public void userShouldBeLoggedToApplication(String expectedUser) {
        String loggedUser = loginPage.getCurrenLoggedInUserName();
        Assert.assertEquals(loggedUser, expectedUser, "User is not logged to application ");
    }
}
