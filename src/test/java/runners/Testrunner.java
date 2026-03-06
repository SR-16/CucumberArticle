package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions
(
		features = "src//test//resources//features//Article.feature",
glue = {"stepDefs"},
		dryRun=false,
		monochrome=true,
		plugin= {"pretty",
				"html:test-output/report/HTMLReport.html",
		}
				
)

public class Testrunner extends AbstractTestNGCucumberTests {

}

