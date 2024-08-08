package com.sakilaTests.StepDefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import static com.sakilaTests.StepDefs.CommonStepDefs.driver;
import static com.sakilaTests.StepDefs.CommonStepDefs.wait;

public class AllActorsStepDefs {


    @When("the user clicks on the actor link with ID {string}")
    public void theUserClicksOnTheActorLinkWithID(String actorId) {
        WebElement actorLink = driver.findElement(By.cssSelector("[data-testid='actor-link-" +actorId + "']"));
        String actorPageUrl = actorLink.getAttribute("href");
        actorLink.click();
        wait.until(ExpectedConditions.urlContains(actorId));

    }

    @Then("all actors should be listed on the page")
    public void allActorsShouldBeListedOnThePage() {
        List<WebElement> actorElements = driver.findElements(By.className("filmActorBlockLink"));
        Assert.assertTrue(actorElements.size() > 0, "There should be at least one actor displayed.");

    }


    @Then("the user should be redirected to the correct actor page with ID {string}")
    public void theUserShouldBeRedirectedToTheCorrectActorPageWithID(String actorId) {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/actor/" + actorId),
                "Url should contain the actor ID " + actorId + ".");
    }

}
