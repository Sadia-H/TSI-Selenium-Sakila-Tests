package com.sakilaTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class SetUpTests {
    protected WebDriver driver;

    @BeforeSuite
    public void setup() {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        //driver.get("http://localhost:5173/all-actors");

        try {
            Thread.sleep(2000); //
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterSuite
    public void tearDown() {
        System.out.println("*** tear down ***");
        if (driver != null) {
            driver.quit();
        }
    }
}
