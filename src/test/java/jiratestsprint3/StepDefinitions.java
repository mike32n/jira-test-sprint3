package jiratestsprint3;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StepDefinitions {

    private static final WebDriver driver = new ChromeDriver();

    public StepDefinitions() {
    }

    @Given("^I open the Chrome$")
    public void i_open_the_Chrome() {
        // Write code here that turns the phrase above into concrete actions
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @Given("^I am on the Jira login page$")
    public void i_am_on_the_Jira_login_page() {
        // Write code here that turns the phrase above into concrete actions
        driver.get("https://jira.codecool.codecanvas.hu/");
    }

    @When("^I fill in Username$")
    public void i_fill_in_Username() {
        // Write code here that turns the phrase above into concrete actions
        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(By.id("login-form-username")));
        driver.findElement(By.id("login-form-username")).sendKeys("user6");

    }

    @When("^I fill in Password$")
    public void i_fill_in_Password() {
        // Write code here that turns the phrase above into concrete actions
        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(By.id("login-form-password")));
        driver.findElement(By.id("login-form-password")).sendKeys("CoolCanvas19.");
    }

    @When("^I press Login$")
    public void i_press_Login() {
        // Write code here that turns the phrase above into concrete actions
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(By.id("login")));
        driver.findElement(By.id("login")).click();
    }

    @Then("^I should be logged in$")
    public void i_should_be_logged_in() {
        // Write code here that turns the phrase above into concrete actions
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='header-details-user-fullname']/span/span/img")));
        driver.get("https://jira.codecool.codecanvas.hu/secure/ViewProfile.jspa");
        Assert.assertEquals(driver.findElement(By.id("up-d-username")).getText(), "user6");
        driver.findElement(By.xpath("//a[@id='header-details-user-fullname']/span/span/img")).click();
        driver.findElement(By.id("log_out")).click();
    }
}
