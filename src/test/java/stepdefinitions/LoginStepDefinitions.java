package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.junit.Assert;

public class LoginStepDefinitions {
    private WebDriver driver;
    private String baseUrl = "https://example.com"; // Replace with your application's URL

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        driver = new ChromeDriver(); // Default to Chrome, can be parameterized later
        driver.get(baseUrl + "/login");
    }

    @When("I enter my {word} and password")
    public void i_enter_my_credentials_and_password(String credentialType) {
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        
        if (credentialType.equals("email")) {
            usernameField.sendKeys("user@example.com");
        } else if (credentialType.equals("username")) {
            usernameField.sendKeys("testuser");
        }
        passwordField.sendKeys("password123");
    }

    @When("I click the login button")
    public void i_click_the_login_button() {
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
    }

    @Then("I should see my account dashboard")
    public void i_should_see_my_account_dashboard() {
        WebElement dashboard = driver.findElement(By.id("dashboard"));
        Assert.assertTrue("Dashboard is not displayed", dashboard.isDisplayed());
    }

    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully() {
        WebElement welcomeMessage = driver.findElement(By.id("welcome-message"));
        Assert.assertTrue("Welcome message is not displayed", welcomeMessage.isDisplayed());
        Assert.assertTrue("Incorrect welcome message", welcomeMessage.getText().contains("Welcome"));
    }

    @When("I enter my email and password using {word} browser")
    public void i_enter_my_email_and_password_using_browser(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        }
        driver.get(baseUrl + "/login");
        i_enter_my_credentials_and_password("email");
    }

    @When("I enter my email and password using {word} device")
    public void i_enter_my_email_and_password_using_device(String device) {
        // In a real scenario, you would set up device emulation here
        // For this example, we'll just log the device type
        System.out.println("Testing on " + device + " device");
        i_enter_my_credentials_and_password("email");
    }

    @When("I enter my previously invalid email and password")
    public void i_enter_my_previously_invalid_email_and_password() {
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        usernameField.sendKeys("invalid@example.com");
        passwordField.sendKeys("invalidpassword");
    }

    @Then("I should see the login failed message")
    public void i_should_see_the_login_failed_message() {
        WebElement errorMessage = driver.findElement(By.id("error-message"));
        Assert.assertTrue("Error message is not displayed", errorMessage.isDisplayed());
        Assert.assertEquals("Incorrect error message", "Login failed. Please try again.", errorMessage.getText());
    }

    @Then("I should not be logged in successfully")
    public void i_should_not_be_logged_in_successfully() {
        WebElement loginForm = driver.findElement(By.id("login-form"));
        Assert.assertTrue("Login form is not displayed", loginForm.isDisplayed());
    }

    @When("I enter empty email and password")
    public void i_enter_empty_email_and_password() {
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        usernameField.clear();
        passwordField.clear();
    }

    @Then("I should see the HTTPS certificate")
    public void i_should_see_the_https_certificate() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue("URL is not HTTPS", currentUrl.startsWith("https://"));
    }
}