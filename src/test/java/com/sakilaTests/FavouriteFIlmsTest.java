package com.sakilaTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class FavouriteFIlmsTest extends SetUpTests{

    private WebDriverWait wait;

    @BeforeMethod
    public void pageSetUp() {
        driver.get("http://localhost:5173/all-films");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("navbarContainer")));
    }

    @Test
    public void testFavouriteFilmsPresence() {

        //Favourite two films from All-Films page
        WebElement firstFilmLikeButton = driver.findElement(By.xpath("(//button[contains(@class, 'favouriteButton')])[1]"));
        firstFilmLikeButton.click();
        WebElement secondFilmLikeButton = driver.findElement(By.xpath("(//button[contains(@class, 'favouriteButton')])[2]"));
        secondFilmLikeButton.click();

        //Navigate to Favourite films page from navbar
        WebElement favouriteFilmLink = driver.findElement(By.linkText("Favourite Films"));
        favouriteFilmLink.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("pageContainer")));

        List<WebElement> favouriteFilms = driver.findElements(By.className("filmActorBlock"));
        Assert.assertTrue(favouriteFilms.size() > 0, "Favorite films should be displayed after liking.");
    }


}
