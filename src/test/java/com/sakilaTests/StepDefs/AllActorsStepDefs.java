package com.sakilaTests.StepDefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class AllActorsStepDefs {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/resources/TestSuites/chromeDriver/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        System.out.println("*** tear down ***");
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

//    @Given("the page {string} is loaded")
//    public void thePageIsLoaded(String url) {
//        driver.get(url);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("navbarContainer")));
//    }

    @Then("all actors should be listed on the page")
    public void allActorsShouldBeListedOnThePage() {
        List<WebElement> actorElements = driver.findElements(By.className("filmActorBlockLink"));
        Assert.assertTrue(actorElements.size() > 0, "There should be at least one actor displayed.");

    }
}
