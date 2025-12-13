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
                "json:target/generated-reports/cucumber.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)
public class Runner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @BeforeSuite
    @Parameters({"environment", "browser", "headless", "lighthouse"})
    public void setupEnv(String environment, String browser, String headless, String lighthouse) {
        System.setProperty("env", environment);
        System.setProperty("browser", browser);
        System.setProperty("headless", headless);
        System.setProperty("lighthouse", lighthouse);
    }
}