package com.sakilaTests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class AllActorsTests extends SetUpTests {

    @BeforeMethod
    public void pageSetUp() {
        driver.get("http://localhost:5173/all-actors");
        try {
            Thread.sleep(2000); // Wait for 2 seconds to ensure the page has loaded
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNavbarPresence() {
        WebElement navbar = driver.findElement(By.className("navbarContainer"));
        Assert.assertNotNull(navbar, "Navbar should be present on the page.");
        Assert.assertTrue(navbar.isDisplayed(), "Navbar should be visible on the page.");
    }

    @Test
    public void testActorContainerPresence() {
        WebElement actorContainer = driver.findElement(By.className("actorContainer"));
        Assert.assertTrue(actorContainer.isDisplayed(), "Actor container should be visible.");

        int actorBlockCount = actorContainer.findElements(By.className("actorBlock")).size();
        Assert.assertTrue(actorBlockCount > 0, "There should be at least one actor block inside the actor container.");
    }





}
