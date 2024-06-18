package com.tutorialsninja.demo.testsuite;

import com.tutorialsninja.demo.customlisteners.CustomListeners;
import com.tutorialsninja.demo.pages.CartPage;
import com.tutorialsninja.demo.pages.DesktopPage;
import com.tutorialsninja.demo.pages.HomePage;
import com.tutorialsninja.demo.pages.TopMenuPage;
import com.tutorialsninja.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import resources.testdata.TestData;

import java.util.List;
@Listeners(CustomListeners.class)
public class DesktopsTest extends BaseTest {
    DesktopPage desktopPage;
    TopMenuPage topMenuPage;
    HomePage homePage;
    CartPage cartPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        desktopPage = new DesktopPage();
        topMenuPage = new TopMenuPage();
        homePage = new HomePage();
        cartPage = new CartPage();
    }


    @Test(groups = {"sanity", "smoke", "regression"})
    public void verifyProductArrangeInAlphaBaticalOrder() throws InterruptedException {
        //1.1 Mouse hover on Desktops Tab. and click
        topMenuPage.mouseHoverOnDesktopAndClick();
        //1.2 Click on “Show All Desktops”
        topMenuPage.selectMenu("Show AllDesktops");
        List<String> beforeSorting = desktopPage.getListOfProducts();
        //1.3 Select Sort By position "Name: Z to A"
        desktopPage.selectFromSortByDropdown("Name (Z - A)");
        Thread.sleep(2000);
        //1.4 Verify the Product will arrange in Descending order
        List<String> afterSorting = desktopPage.getListOfProductsAfterFilter();
        Assert.assertEquals(afterSorting, beforeSorting);

    }


    @Test(groups = {"smoke", "regression"}, dataProvider = "desktopData", dataProviderClass = TestData.class)
    public void verifyProductAddedToShoppingCartSuccessFully(String product, String qty,
                                                             String successMessage, String productName, String model, String total) throws InterruptedException {
        //2.1 Mouse hover on Currency Dropdown and click
        homePage.clickOnCurrency();
        // 2.2 Mouse hover on £Pound Sterling and click
//        desktopPage.selectPoundCurrency();
        // 2.3 Mouse hover on Desktops Tab.
        topMenuPage.mouseHoverOnDesktopAndClick();
        //2.4 Click on “Show All Desktops”
        topMenuPage.selectMenu("Show AllDesktops");
        //2.5 Select Sort By position "Name: A to Z"
        desktopPage.selectFromSortByDropdown("Name (A - Z)");
        //2.6 Select product <product>
        desktopPage.clickOnProduct(product);
        //2.7 Enter Qty <qty> using Select class.
        desktopPage.enterQuantity(qty);
        //2.8 Click on “Add to Cart” button
        desktopPage.clickOnAddToCartButton();
        Thread.sleep(2000);
        //2.9 Verify the Message <successMessage>
        String expString = desktopPage.getSuccessAlertMsg();
        String expString2 = expString.split("!")[0];
        Assert.assertEquals(expString, "Success: You have added " + product + " to your shopping cart!\n" + "×");
        // 2.10 Click on link “shopping cart” display into success message
        desktopPage.clickOnCartLinkInMsgButton();
        //2.11 Verify the text "Shopping Cart"
        Assert.assertEquals(cartPage.getShoppingCartHeading().substring(0, 13), "Shopping Cart");
        //2.12 Verify the Product name <productName>
        Assert.assertEquals(cartPage.getProductName(), product);
        //2.13 Verify the Model <model>
        Assert.assertEquals(cartPage.getModelName(), model);
        //2.14 Verify the Total <total>
        Assert.assertEquals(cartPage.getTotalPrice(), total);
    }

}
