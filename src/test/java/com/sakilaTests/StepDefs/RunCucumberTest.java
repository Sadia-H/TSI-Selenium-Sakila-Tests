package com.sakilaTests.StepDefs;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/java/resources/TestSuites/features"},
        plugin = {"pretty"}
)

public class RunCucumberTest extends AbstractTestNGCucumberTests {

}
