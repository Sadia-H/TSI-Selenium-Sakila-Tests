package com.sakilaTests.StepDefs;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonStepDefs {

    public static WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/resources/TestSuites/chromeDriver/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("the page {string} is loaded")
    public void thePageIsLoaded(String url) {
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("navbarContainer")));
    }

}
