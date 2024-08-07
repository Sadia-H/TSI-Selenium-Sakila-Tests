package com.sakilaTests;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class AddActorStepdefs {

    private WebDriver driver;
    private WebDriverWait wait;
    private String actorId;
    private String firstName;
    private String lastName;


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
//            driver.close();
            driver.quit();
        }
    }

    @Given("the page {string} is loaded")
    public void thePageIsLoaded(String url) {
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("navbarContainer")));
    }

    @When("the user types the first name {string}")
    public void theUserTypesTheFirstName(String firstName) {
        this.firstName = firstName;
        WebElement firstNameInput = driver.findElement(By.id("firstName"));
        firstNameInput.sendKeys(firstName);
    }

    @When("(the user )types the last name {string}")
    public void typesTheLastName(String lastName) {
        this.lastName = lastName;
        WebElement lastNameInput = driver.findElement(By.id("lastName"));
        lastNameInput.sendKeys(lastName);
    }

    @When("(the user )clicks the Add Actor Button")
    public void clicksTheAddActorButton() {
        WebElement addActorButton = driver.findElement(By.id("addActorButton"));
        addActorButton.click();
    }

    @Then("the user should see a {string} message")
    public void theUserShouldSeeAMessage(String successMessage) {
        WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("successMessage")));
        Assert.assertEquals(messageElement.getText(), successMessage,
                "The success message should be displayed.");
    }

    @Then("(the user )should be redirected to the actor page")
    public void shouldBeRedirectedToTheActorPage() {
        //wait for URL to redirect to the actor by id page
        wait.until(ExpectedConditions.urlMatches("http://localhost:5173/actor/\\d+"));
        String currentUrl = driver.getCurrentUrl();

        //extract id from URL
        actorId = currentUrl.substring(currentUrl.lastIndexOf('/') + 1);

        Assert.assertTrue(currentUrl.contains("/actor/" + actorId),
                "The redirection URL should contain the new actor ID.");
    }

    @Then("(the user )should see the actor {string} {string} on the actor's page")
    public void shouldSeeTheActorOnTheActorSPage(String firstName, String lastName) {
        String expectedFullName = firstName + " " + lastName;

        // Retrieve the full name element
        WebElement actorFullNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("actorTitle")));
        String actualFullName = actorFullNameElement.getText();

        // Assert that the full name matches the expected full name
        Assert.assertEquals(actualFullName, expectedFullName,
                "The actor's full name should match the actor just added.");
    }


    @Then("(the user)should see the actor {string} in the actors list")
    public void shouldSeeTheActorInTheActorsList(String actorName) {
        driver.get("http://localhost:5173/all-actors");
        WebElement actorsList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("actorContainer")));
        Assert.assertTrue(actorsList.getText().contains(actorName), "New actor should be in the list of all actors. ");
    }



}
