package testRunner;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(

        features = "Feature/Department.feature",
        glue = "com/Actitime/stepDefinition",
        monochrome = true,  // for making the output readable.
        plugin = {"pretty"},
       tags = "@Department"

)
public class TestRunner {

}
