package ohtu;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.Random;

public class Stepdefs {
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";

    @Given("^login is selected$")
    public void login_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
    }

    @Given("^user with username \"([^\"]*)\" and password \"([^\"]*)\" is tried to be created$")
    public void user_with_username_and_password_is_tried_to_be_created(String username, String password) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        command_new_user_is_selected();
        create_with(username, password);
        user_is_not_created_and_error_is_reported("password should have at least 8 characters");
        user_is_not_created_and_error_is_reported("username should have at least 3 characters");
    }


    @Given("^command new user is selected$")
    public void command_new_user_is_selected() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }

    @Given("^user with username \"([^\"]*)\" with password \"([^\"]*)\" is successfully created$")
    public void user_with_username_with_password_is_successfully_created(String username, String password) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        command_new_user_is_selected();
        create_with(username, password);
        a_new_user_is_created();
    }


    @When("^a valid username \"([^\"]*)\" and a too short password \"([^\"]*)\" and matching password confirmation are entered$")
    public void a_valid_username_and_a_too_short_password_and_matching_password_confirmation_are_entered(String username, String password) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        create_with(username, password);
    }

    @When("^incorrect username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void incorrect_username_and_incorrect_password_are_given(String username, String password) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        logInWith(username, password);
    }



    @When("^a valid username \"([^\"]*)\" and password \"([^\"]*)\" and matching password confirmation are entered$")
    public void a_valid_username_and_password_and_matching_password_confirmation_are_entered(String username, String password) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        create_with(username, password);
    }


    @When("^username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_and_password_are_given(String username, String password) throws Throwable {
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();
    }

    @When("^a too short username \"([^\"]*)\" and a valid password \"([^\"]*)\" and matching password confirmation are entered$")
    public void a_too_short_username_and_a_valid_password_and_matching_password_confirmation_are_entered(String username, String password) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        create_with(username, password);
    }

    @When("^a valid username \"([^\"]*)\" and a valid password \"([^\"]*)\" and not matching password confirmation \"([^\"]*)\" are entered$")
    public void a_valid_username_and_a_valid_password_and_not_matching_password_confirmation_are_entered(String username, String password, String passwordConfirmation) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        pageHasContent("learn english");
//        Random r = new Random();
//        char[] kirjaimia = new char[5];
//        for (int i = 0; i < 5; i++) {
//            int n = r.nextInt(26) + 97;
//            kirjaimia[i] = (char) n;
//        }

        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);// + String.copyValueOf(kirjaimia)
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(passwordConfirmation);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }


    @Then("^a new user is created$")
    public void a_new_user_is_created() throws Throwable {
        //System.out.println(driver.getPageSource());
        // Write code here that turns the phrase above into concrete actions
        pageHasContent("Welcome to Ohtu Application!");
        pageHasContent("info for newbie");
    }


    @Then("^system will respond \"([^\"]*)\"$")
    public void system_will_respond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }

    @When("^correct username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_correct_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^correct username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void username_and_incorrect_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^nonexistent username \"([^\"]*)\" and random password \"([^\"]*)\" are given$")
    public void nonexistent_username_and_random_password_are_given(String arg1, String arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        logInWith(arg1, arg2);
    }


    @Then("^user is logged in$")
    public void user_is_logged_in() throws Throwable {
        pageHasContent("Ohtu Application main page");
    }

    @Then("^user is not logged in and error message is given$")
    public void user_is_not_logged_in_and_error_message_is_given() throws Throwable {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }

    @Then("^user is not created and error \"([^\"]*)\" is reported$")
    public void user_is_not_created_and_error_is_reported(String error) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        pageHasContent(error);
    }


    @After
    public void tearDown() {
        driver.quit();
    }

    /* helper methods */

    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }

    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();
    }

    private void create_with(String username, String password) {
        pageHasContent("learn english");
//        Random r = new Random();
//        char[] kirjaimia = new char[monta];
//        for (int i = 0; i < monta; i++) {
//            int n = r.nextInt(26) + 97;
//            kirjaimia[i] = (char) n;
//        }

        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);// + String.copyValueOf(kirjaimia)
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(password);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }
}
