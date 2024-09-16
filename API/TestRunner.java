@RunWith(Cucumber.class)
@CucumberOptions(
features = "src/test/resources/features",
glue = "stepdefinitions",
plugin = {"pretty", "html:target/cucumber-reports", "json:target/cucumber.json"},
monochrome = true,
strict = true
)
public class TestRunner {}