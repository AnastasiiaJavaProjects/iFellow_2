package tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features={"classpath:features"},
        glue={"classpath:steps", "classpath:hooks"},
        plugin = {"pretty"}
)
public class TestClass {
}