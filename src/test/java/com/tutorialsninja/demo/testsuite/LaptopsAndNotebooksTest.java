package com.tutorialsninja.demo.testsuite;

import com.tutorialsninja.demo.customlisteners.CustomListeners;
import com.tutorialsninja.demo.pages.*;
import com.tutorialsninja.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

@Listeners(CustomListeners.class)
public class LaptopsAndNotebooksTest extends BaseTest {
    TopMenuPage topMenuPage;
    HomePage homePage;
    CartPage cartPage;
    DesktopPage desktopPage;
    LaptopsAndNotebooksPage laptopsAndNotebooksPage;
    CheckoutPage checkoutPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        topMenuPage = new TopMenuPage();
        homePage = new HomePage();
        cartPage = new CartPage();
        desktopPage = new DesktopPage();
        laptopsAndNotebooksPage = new LaptopsAndNotebooksPage();
        checkoutPage = new CheckoutPage();
    }

    @Test(groups = {"sanity", "smoke", "regression"})
    public void verifyProductsPriceDisplayHighToLowSuccessfully() throws InterruptedException {
        //1.1 Mouse hover on Laptops & Notebooks Tab.and click
        topMenuPage.mouseHoverOnLaptopsAndNotebookAndClick();
        //1.2 Click on “Show All Laptops & Notebooks”
        topMenuPage.selectMenu("Show AllLaptops & Notebooks");
        List<Double> beforeSorting = laptopsAndNotebooksPage.getPriceList();
        //1.3 Select Sort By "Price (High > Low)"
        desktopPage.selectFromSortByDropdown("Price (High > Low)");
        Thread.sleep(2000);
        //1.4 Verify the Product price will arrange in High to Low order.
        List<Double> afterSorting = laptopsAndNotebooksPage.afterFilterPrice();
        Assert.assertEquals(afterSorting, beforeSorting);
    }

    @Test(groups = {"smoke", "regression"})
    public void verifyThatUserPlaceOrderSuccessfully() throws InterruptedException {
        //2.1 Mouse hover on Laptops & Notebooks Tab and click
        topMenuPage.mouseHoverOnLaptopsAndNotebookAndClick();
        //2.2 Click on “Show All Laptops & Notebooks”
        topMenuPage.selectMenu("Show AllLaptops & Notebooks");
        //2.3 Select Sort By "Price (High > Low)"
        desktopPage.selectFromSortByDropdown("Price (High > Low)");
        //2.4 Select Product “MacBook”
        laptopsAndNotebooksPage.clickOnProduct("Sony VAIO");
        //2.5 Verify the text “MacBook”
        Assert.assertEquals(laptopsAndNotebooksPage.getSonyVAIOHeading(), "Sony VAIO");
        //2.6 Click on ‘Add To Cart’ button
        desktopPage.clickOnAddToCartButton();
        //2.7 Verify the message “Success: You have added MacBook to your shopping cart!”
        String expString = desktopPage.getSuccessAlertMsg();
        String expString2 = expString.split("!")[0];
        Assert.assertEquals(expString, "Success: You have added Sony VAIO to your shopping cart!\n" + "×");
        //2.8 Click on link “shopping cart” display into success message
        desktopPage.clickOnCartLinkInMsgButton();
        //2.9 Verify the text "Shopping Cart"
        Assert.assertEquals(cartPage.getShoppingCartHeading().substring(0, 13), "Shopping Cart");
        //2.10 Verify the Product name "MacBook"
        Assert.assertEquals(cartPage.getProductName(), "Sony VAIO");
        //2.11 Change Quantity "2"
        cartPage.sendQty("2");
        //2.12 Click on “Update” Tab
        cartPage.clickOnUpdateQty();
        //2.13 Verify the message “Success: You have modified your shopping cart!”
        Assert.assertEquals(cartPage.getSuccessCartUpdateMsg(), "Success: You have modified your shopping cart!\n" + "×");
        //2.14 Verify the Total £737.45
        Assert.assertEquals(cartPage.getTotalPrice(), "$2,404.00");
        //2.15 Click on “Checkout” button
        cartPage.clickOnCheckOut();
        Thread.sleep(2000);
        //2.16 Verify the text “Checkout”
        //2.17 Verify the Text “New Customer”
        Assert.assertEquals(checkoutPage.getNewCustHeading(), "New Customer");
        //2.18 Click on “Guest Checkout” radio button
        checkoutPage.clickOnGuestRadio();
        //2.19 Click on “Continue” tab
        checkoutPage.clickOnCheckoutOptContButton();
        //2.20 Fill the mandatory fields
        checkoutPage.fillBillingFormDetails("Prime", "Testing", "p123@gmail.com", "07833662976"
                , "44, Region street", "London", "B44JUH", "United Kingdom", "Kent");
        //2.21 Click on “Continue” Button
        checkoutPage.clickOnDelMethodContButton();
        //2.23 Check the Terms & Conditions check box
        checkoutPage.clickOnAgreeTermsCheckbox();
        checkoutPage.clickOnPaymentMethodCont();

    }
}
