package com.sakilaTests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class AllActorsTests extends SetUpTests {

    private WebDriverWait wait;

    @BeforeMethod
    public void pageSetUp() {
        driver.get("http://localhost:5173/all-actors");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("navbarContainer")));
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

    @Test
    public void testActorLinkNavigation() {
        WebElement firstActorBlock = driver.findElement(By.cssSelector(".actorContainer .actorBlock"));
        WebElement actorLink = firstActorBlock.findElement(By.xpath(".."));
        String actorPageUrl = actorLink.getAttribute("href");
        actorLink.click();

        //url should contain 1 for first actor
        wait.until(ExpectedConditions.urlContains(actorPageUrl.substring(actorPageUrl.lastIndexOf("/") + 1)));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains(actorPageUrl.substring(actorPageUrl.lastIndexOf("/") + 1)),
                "URL should contain the actor ID.");

    }





}
