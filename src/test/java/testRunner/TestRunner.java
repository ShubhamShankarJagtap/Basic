package testRunner;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(

        features = "Feature/sauceDemologin.feature",
        glue = "stepDefinition",
        dryRun = false,
        monochrome = true,  // for making the output readable.
        plugin = {"pretty"}
//        tags = " @GetById"

)
public class TestRunner {

}
