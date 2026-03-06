package stepDefs;

import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import base.TestBase;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;

public class Hooks {

    // Runs once before all scenarios start
    @BeforeAll
    public static void setupDriver() throws IOException {
        TestBase.initDriver();
    }

    // Runs after every scenario
    // Takes screenshot if scenario fails
    @After
    public void attachScreen(Scenario scenario) {

        if (scenario.isFailed()) {

            TakesScreenshot screen = (TakesScreenshot) TestBase.getdriver();
            byte[] img = screen.getScreenshotAs(OutputType.BYTES);

            scenario.attach(img, "image/png", "FailedScenarioImage");
        }
    }

    // Runs once after all scenarios finish
    @AfterAll
    public static void tearDown() {
        TestBase.tearDown();
    }
}