package com.tutorialsninja.demo.testsuite;

import com.tutorialsninja.demo.testbase.BaseTest;
import com.tutorialsninja.demo.customlisteners.CustomListeners;
import com.tutorialsninja.demo.pages.TopMenuPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomListeners.class)
public class TopMenuTest extends BaseTest {
    TopMenuPage topMenuPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        topMenuPage = new TopMenuPage();

    }

    @Test(groups = {"sanity", "smoke", "regression"})
    public void verifyUserShouldNavigateToDesktopsPageSuccessfully() throws InterruptedException {
        //1.1 Mouse hover on “Desktops” Tab and click
        topMenuPage.mouseHoverOnDesktopAndClick();
        Thread.sleep(2000);
        //1.2 call selectMenu method and pass the menu = “Show All Desktops”
        topMenuPage.selectMenu("Show AllDesktops");
        //1.3 Verify the text ‘Desktops
        Assert.assertEquals(topMenuPage.getDesktopHeading(), "Desktops");
    }

    @Test(groups = {"smoke", "regression"})
    public void verifyUserShouldNavigateToLaptopsAndNotebooksPageSuccessfully() {
        //2.1 Mouse hover on “Laptops & Notebooks” Tab and click
        topMenuPage.mouseHoverOnLaptopsAndNotebookAndClick();
        //2.2 call selectMenu method and pass the menu = “Show All Laptops & Notebooks”
        topMenuPage.selectMenu("Show AllLaptops & Notebooks");
        //2.3 Verify the text ‘Laptops & Notebooks’
        Assert.assertEquals(topMenuPage.getLaptopANdNotebookHeading(), "Laptops & Notebooks");
    }

    @Test(groups = {"regression"})
    public void verifyUserShouldNavigateToComponentsPageSuccessfully() {
        //3.1 Mouse hover on “Components” Tab and click
        topMenuPage.mouseHoverOnComponentsAndClick();
        //3.2 call selectMenu method and pass the menu = “Show All Components”
        topMenuPage.selectMenu("Show AllComponents");
        //3.3 Verify the text ‘Components’
        Assert.assertEquals(topMenuPage.getComponentsHeading(), "Components");
    }
}
