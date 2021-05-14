package testRunner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features"},
        monochrome = true,
        plugin = {"html:reports/site/cucumber-pretty"},
        glue = {"stepDefinitions"}

)

public class TestRunner {
}
