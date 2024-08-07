package com.sakilaTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class SetUpTests {
    protected static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/resources/TestSuites/chromeDriver/chromedriver.exe");
        driver = new ChromeDriver();
    }

//    @AfterClass
//    public void tearDown() {
//        System.out.println("*** tear down ***");
//        if (driver != null) {
//            driver.quit();
//        }
//    }
}
