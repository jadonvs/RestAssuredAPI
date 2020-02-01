package cucumberOptions;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features",glue= {"stepDefinations"},tags= {"@AddPlace"},
plugin="json:target/jsonReports/cucumber-reports.json")
public class TestRunner {
	

}
