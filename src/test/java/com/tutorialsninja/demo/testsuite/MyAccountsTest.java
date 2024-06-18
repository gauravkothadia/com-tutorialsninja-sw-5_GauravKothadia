package com.tutorialsninja.demo.testsuite;

import com.tutorialsninja.demo.customlisteners.CustomListeners;
import com.tutorialsninja.demo.pages.*;
import com.tutorialsninja.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomListeners.class)
public class MyAccountsTest extends BaseTest {

    TopMenuPage topMenuPage;
    HomePage homePage;
    CartPage cartPage;
    DesktopPage desktopPage;
    LaptopsAndNotebooksPage laptopsAndNotebooksPage;
    CheckoutPage checkoutPage;
    MyAccountsPage myAccountsPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        topMenuPage = new TopMenuPage();
        homePage = new HomePage();
        cartPage = new CartPage();
        desktopPage = new DesktopPage();
        laptopsAndNotebooksPage = new LaptopsAndNotebooksPage();
        checkoutPage = new CheckoutPage();
        myAccountsPage = new MyAccountsPage();
    }

    @Test(groups = {"sanity", "smoke", "regression"})
    public void verifyUserShouldNavigateToRegisterPageSuccessfully() {
        //1.1 Clickr on My Account Link.
        homePage.selectMyAccountOptions("My Account");
        //1.2 Call the method “selectMyAccountOptions” method and pass the parameter “Register”
        homePage.selectMyAccountOptions("Register");
        //1.3 Verify the text “Register Account”.
        Assert.assertEquals(myAccountsPage.getRegAccHeading(), "Register Account");
    }

    @Test(groups = {"smoke", "regression"})
    public void verifyUserShouldNavigateToLoginPageSuccessfully() {
        //2.1 Clickr on My Account Link.
        homePage.selectMyAccountOptions("My Account");
        //2.2 Call the method “selectMyAccountOptions” method and pass the parameter “Login”
        homePage.selectMyAccountOptions("Login");
        //2.3 Verify the text “Returning Customer”.
        Assert.assertEquals(myAccountsPage.getLoginHeading(), "Returning Customer");
    }

    @Test(groups = {"regression","abc"})
    public void verifyThatUserRegisterAccountSuccessfully() {
        //3.1 Clickr on My Account Link.
        homePage.selectMyAccountOptions("My Account");
        //3.2 Call the method “selectMyAccountOptions” method and pass the parameter
        // “Register”
        homePage.selectMyAccountOptions("Register");
        //3.3 Enter First Name
        //3.4 Enter Last Name
        //3.5 Enter Email
        //3.6 Enter Telephone
        //3.7 Enter Password
        //3.8 Enter Password Confirm
        //3.9 Select Subscribe Yes radio button
        //3.10 Click on Privacy Policy check box
        // 3.11 Click on Continue button
        myAccountsPage.fillRegistrationData("Prime", "Testing", "abc999@gmail.com", "07833662976", "Prime@123", "Prime@123");
        //3.12 Verify the message “Your Account Has Been Created!”
        Assert.assertEquals(myAccountsPage.getRegConfirmText(),"Your Account Has Been Created!");
        //3.13 Click on Continue button
        myAccountsPage.clickOnContAfterRegButton();
        // 3.14 Clickr on My Account Link.
        homePage.selectMyAccountOptions("My Account");
        //3.15 Call the method “selectMyAccountOptions” method and pass the parameter
        // “Logout”
        homePage.selectMyAccountOptions("Logout");
        //3.16 Verify the text “Account Logout”
        Assert.assertEquals(myAccountsPage.getLogoutConfirmMsg(), "Account Logout");
    }

    @Test(groups = {"regression","abc"})
    public void verifyThatUserShouldLoginAndLogoutSuccessfully() {
        //4.1 Click on My Account Link.
        homePage.selectMyAccountOptions("My Account");
        //4.2 Call the method “selectMyAccountOptions” method and pass the parameter
        // “Login”
        homePage.selectMyAccountOptions("Login");
        //4.3 Enter Email address
        //4.4 Enter Last Name
        //4.5 Enter Password
        //4.6 Click on Login button
        myAccountsPage.fillLoginData("abc999@gmail.com", "Prime@123");
        //4.7 Verify text “My Account”
        Assert.assertEquals(myAccountsPage.getAfterLoginHeading(), "My Account");
        // 4.8 Click on My Account Link.
        homePage.selectMyAccountOptions("My Account");
        //4.9 Call the method “selectMyAccountOptions” method and pass the parameter
        // “Logout”
        homePage.selectMyAccountOptions("Logout");
        //4.10 Verify the text “Account Logout”
        Assert.assertEquals(myAccountsPage.getLogoutConfirmMsg(), "Account Logout");
        //4.11 Click on Continue button
        myAccountsPage.clickOnContAfterRegButton();
    }
}
