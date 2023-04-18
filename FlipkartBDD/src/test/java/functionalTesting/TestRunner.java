package functionalTesting;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features= {"src/test/java/functionalTesting/Flipkart.feature"},
		glue="functionalTesting",
		tags="@Flipkart"
		)

public class TestRunner {

}
