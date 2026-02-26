package cucumber.options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features/mapValidation.feature",
plugin= {"html:target/htmlReports/cucumber-reports.html","json:target/jsonReports/cucumber-report.json"
		,"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
glue= {"stepDefination"})
public class TestRunner {

}
