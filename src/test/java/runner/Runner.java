package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"steps", "hooks"},
        tags = "@wip",
        plugin = {
                "html:target/generated-reports/cucumber.html",
                "json:target/generated-reports/cucumber.json"
        }
)
public class Runner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @BeforeSuite
    @Parameters({"environment", "browser", "headless"})
    public void setupEnv(String environment, String browser, String headless) {
        System.setProperty("env", environment);
        System.setProperty("browser", browser);
        System.setProperty("headless", headless);
    }
}