package com.sakilaTests.StepDefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;


import static com.sakilaTests.StepDefs.CommonStepDefs.driver;
import static com.sakilaTests.StepDefs.CommonStepDefs.wait;

public class AddActorStepDefs {

    @When("the user types the first name {string}")
    public void theUserTypesTheFirstName(String firstName) {
        WebElement firstNameInput = driver.findElement(By.id("firstName"));
        firstNameInput.sendKeys(firstName);
    }

    @When("(the user )types the last name {string}")
    public void typesTheLastName(String lastName) {
        WebElement lastNameInput = driver.findElement(By.id("lastName"));
        lastNameInput.sendKeys(lastName);
    }

    @When("the first name is empty")
    public void theFirstNameIsEmpty() {
        WebElement firstNameInput = driver.findElement(By.id("firstName"));
        firstNameInput.clear();
    }

    @When("the second name is empty")
    public void theSecondNameIsEmpty() {
        WebElement lastNameInput = driver.findElement(By.id("lastName"));
        lastNameInput.clear();
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
        String actorId = currentUrl.substring(currentUrl.lastIndexOf('/') + 1);

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




    @Then("the user should see and error message for first name")
    public void theUserShouldSeeAndErrorMessageForFirstName() {
        WebElement firstNameErrorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='errorMessage' and contains(text(), 'Please enter the first name.')]")));
        Assert.assertEquals(firstNameErrorElement.getText(), "Please enter the first name.",
                "The error message for the first name should be displayed.");
    }

    @Then("should see error message for last name")
    public void shouldSeeErrorMessageForLastName() {
        WebElement lastNameErrorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='errorMessage' and contains(text(), 'Please enter the last name.')]")));
        Assert.assertEquals(lastNameErrorElement.getText(), "Please enter the last name.",
                "The error message for the last name should be displayed.");
    }
}
