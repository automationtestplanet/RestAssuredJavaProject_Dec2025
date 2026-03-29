package runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/Features",
        glue = "reqres.bdd",
        plugin = "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        tags = "@UserService",
        dryRun = false

)
public class TestRunner extends AbstractTestNGCucumberTests {
}
