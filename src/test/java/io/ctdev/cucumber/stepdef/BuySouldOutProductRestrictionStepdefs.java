package io.ctdev.cucumber.stepdef;

import io.ctdev.cucumber.pages.LoginPage;
import io.ctdev.cucumber.pages.ShopPage;
import io.ctdev.framework.driver.WebDriverSingleton;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class BuySouldOutProductRestrictionStepdefs {
    private LoginPage loginPage = new LoginPage(WebDriverSingleton.getDriver());
    private ShopPage shopPage = new ShopPage(WebDriverSingleton.getDriver());

    @When("user opens main page")
    public void userOpensMainPage() {
loginPage.openPage();
    }

    @When("user closes Dialogue Popup")
    public void userClosesDialoguePopup() {
        loginPage.closeDialoguePopup();
    }

    @When("clicks On Account Button")
    public void clicksOnAccountButton() {
        loginPage.clickOnAccountButton();
    }

    @When("clicks On Login Button")
    public void clicksOnLoginButton() {
        loginPage.clickOnLoginButton();
    }

    @When("user enters login {string} and password {string}")
    public void userEntersLoginAndPassword(String email, String password) {
loginPage.enterUserEmail(email);
        loginPage.enterUserPassword(password);
    }

    @When("user clicks on sbmit button")
    public void userClicksOnSbmitButton() {
        loginPage.submitLoginForUser();
    }

    @When("user {string} should be logged to application")
    public void userShouldBeLoggedToApplication(String login) {
        String loggedUser = loginPage.getCurrenLoggedInUserName();
        Assert.assertEquals(loggedUser, login, "User is not logged to application ");

    }

    @When("user refreshes current page")
    public void userRefreshesCurrentPage() {
        shopPage.refreshCurrentPage();
    }

    @When("user clicks on Proceed To Next Shop Page button")
    public void userClicksOnProceedToNextShopPageButton() {
        shopPage.clickProceedToNextShopPageButton();
    }

    @When("user put sold out product to basket")
    public void userPutSoldOutProductToBasket() {
        shopPage.putSoldOutProductToBasket();
    }

    @When("opens the basket")
    public void opensTheBasket() {
        shopPage.openBasket();
    }

    @Then("total amount of the basket should be {String}")
    public void totalAmountOfTheBasketShouldBe(int cartTotalAmount) {
        String actualCartAmount = shopPage.getTotalMoneyAmountOfBasket();
        Assert.assertEquals(actualCartAmount, cartTotalAmount, "The Sold Out Product is in the Basket");
    }
}
