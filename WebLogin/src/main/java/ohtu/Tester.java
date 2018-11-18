package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.Random;

public class Tester {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        //login();

        //wrongPassword();

        //noUser();

        //newUser();

        newUserWithLogout();
    }

    public static void login() {
        WebDriver driver = new HtmlUnitDriver();

        driver.get("http://localhost:4567");

        sleep(2);

        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));

        sleep(2);
        element.submit();

        sleep(3);

        driver.quit();

        sleep(1);
    }

    public static void wrongPassword() {
        WebDriver driver = new HtmlUnitDriver();

        driver.get("http://localhost:4567");
        sleep(1);

        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
        sleep(1);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("vaara");
        element = driver.findElement(By.name("login"));
        sleep(1);
        element.submit();
        sleep(1);
        driver.quit();
    }

    public static void noUser() {
        WebDriver driver = new HtmlUnitDriver();

        driver.get("http://localhost:4567");
        sleep(1);

        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
        sleep(1);

        element = driver.findElement(By.name("username"));
        element.sendKeys("imaghost");
        element = driver.findElement(By.name("password"));
        element.sendKeys("vaara");
        element = driver.findElement(By.name("login"));
        sleep(1);
        element.submit();
        sleep(1);
        driver.quit();
    }

    public static void newUser() {
        WebDriver driver = new HtmlUnitDriver();

        driver.get("http://localhost:4567");
        sleep(1);

        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
        sleep(1);

        Random r = new Random();

        element = driver.findElement(By.name("username"));
        element.sendKeys("saadodemon" + r.nextInt(9999));
        element = driver.findElement(By.name("password"));
        element.sendKeys("neinneinneinnein");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("neinneinneinnein");
        element = driver.findElement(By.name("signup"));
        sleep(1);
        element.submit();
        sleep(1);
        driver.quit();
    }

    public static void newUserWithLogout() {
        WebDriver driver = new HtmlUnitDriver();

        driver.get("http://localhost:4567");
        sleep(1);

        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
        sleep(1);

        Random r = new Random();

        element = driver.findElement(By.name("username"));
        element.sendKeys("saadodemon" + r.nextInt(9999));
        element = driver.findElement(By.name("password"));
        element.sendKeys("neinneinneinnein");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("neinneinneinnein");
        element = driver.findElement(By.name("signup"));
        sleep(1);
        element.submit();
        sleep(1);

        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        sleep(1);

        element = driver.findElement(By.linkText("logout"));
        element.click();
        sleep(1);

        driver.quit();
    }

    private static void sleep(int n) {
        try {
            Thread.sleep(n * 1000);
        } catch (Exception e) {
        }
    }
}
