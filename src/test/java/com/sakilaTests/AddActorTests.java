package com.sakilaTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddActorTests extends SetUpTests {

    private WebDriverWait wait;

    @BeforeMethod
    public void pageSetUp() {
        driver.get("http://localhost:5173/add-actor");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("navbarContainer")));
    }

    @Test
    public void testAddActorSuccess() {
        WebElement firstNameInput = driver.findElement(By.id("firstName"));
        WebElement lastNameInput = driver.findElement(By.id("lastName"));
        WebElement submitButton = driver.findElement(By.id("addActorButton"));

        firstNameInput.sendKeys("Sherlock");
        lastNameInput.sendKeys("Holmes");
        submitButton.click();

        //success message after form submission
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("successMessage")));
        Assert.assertTrue(successMessage.isDisplayed(), "Actor added message should be visible after the form has been submitted.");

        //redirection after success message
        wait.until(ExpectedConditions.urlMatches("http://localhost:5173/actor/\\d+"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.matches("http://localhost:5173/actor/\\d+"),
                "Page should redirect to the actor's details page after success message.");
    }


    @Test
    //submitting empty forms to test for validation error messages
    public void testAddActorErrorMessages() {
        WebElement submitButton = driver.findElement(By.id("addActorButton"));
        submitButton.click();

        WebElement firstNameError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("errorMessage")));
        WebElement lastNameError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("errorMessage")));

        Assert.assertTrue(firstNameError.isDisplayed(), "First name error message should appear.");
        Assert.assertTrue(lastNameError.isDisplayed(), "Last name error message should appear.");
    }


}
