package com.sakilaTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ActorByIdTests extends SetUpTests {

    private WebDriverWait wait;
    private String id = "1";

    @BeforeMethod
    public void pageSetUp() {
        driver.get("http://localhost:5173/actor/" + id);

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("navbarContainer")));
    }

    @Test
    public void testActorTitlePresence() {
        WebElement actorTitle = driver.findElement(By.className("actorTitle"));
        Assert.assertNotNull(actorTitle, "Actor title (first + last name) should be present.");

        String actorName = actorTitle.findElement(By.tagName("h1")).getText();
        Assert.assertFalse(actorName.isEmpty(), "Actor name should be displayed.");
    }

    @Test
    public void testFilmLinkNavigation() {
        WebElement firstFilmBlock = driver.findElement(By.cssSelector(".filmActorContainer .filmActorBlock"));

        //get first film (in the link element) and store as filmLink
        WebElement filmLink = firstFilmBlock.findElement(By.xpath(".."));
        String filmPageUrl = filmLink.getAttribute("href");

        //navigate to the linked page
        filmLink.click();

        wait.until(ExpectedConditions.urlContains(filmPageUrl.substring(filmPageUrl.lastIndexOf("/") + 1)));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains(filmPageUrl.substring(filmPageUrl.lastIndexOf("/") + 1)),
                "URL should contain the film ID selected");
    }


}
