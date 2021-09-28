package seleniumTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TestPhpTravel {
    private static final String url = "https://www.phptravels.net/";
    private static final String link_text = "Signup";
    private static  final String name = "john555";
    private static final String last_name = "Johnson";
    private static final String phone = "+3816633361";
    private static final String email = "email333@email2.com";
    private static final String password = "pass123123";
    private static final String login_title = "Login - PHPTRAVEL";
    private static final String dashboard_title = "Dashboard - PHPTRAVEL";

    @Test
    void testSignUpAndLogIn() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-fullscreen");
        WebDriver webDriver = new ChromeDriver(options);
        WebDriverWait waitDriver = new WebDriverWait(webDriver,Duration.ofSeconds(10));
        try{
            webDriver.get(url);
            // click signup button
            webDriver.findElement(By.linkText(link_text)).click();
            // wait to load signup page
            WebElement name = waitDriver.until(presenceOfElementLocated(By.name("first_name")));
            // sending name
            name.sendKeys(TestPhpTravel.name);
            // sending last name
            webDriver.findElement(By.name("last_name")).sendKeys(last_name);
            // sending phone, email, password
            webDriver.findElement(By.name("phone")).sendKeys(phone);
            webDriver.findElement(By.name("email")).sendKeys(email);
            webDriver.findElement(By.name("password")).sendKeys(password);
            Thread.sleep(500);
            // click signup button
            webDriver.findElement(By.className("btn-lg")).click();
            // checking if signup is successful
            String source = webDriver.getPageSource();
            String success_msg = "Signup successful";
            assertTrue(source.contains(success_msg));
            // login, first wait
            waitDriver.until( ExpectedConditions.titleContains(login_title));
            // send email
            webDriver.findElement(By.name("email")).sendKeys(email);
            // send password
            webDriver.findElement(By.name("password")).sendKeys(password);
            Thread.sleep(500);
            // click login button
            webDriver.findElement(By.className("btn-lg")).click();
            // dashboard, wait first
            waitDriver.until( ExpectedConditions.titleContains(dashboard_title));
            // checking if login is successful
            source = webDriver.getPageSource();
            success_msg = TestPhpTravel.name;
            assertTrue(source.contains(success_msg));
            // click on logout
            webDriver.findElement(By.linkText("Logout")).click();
            Thread.sleep(3000);
        }
        catch (Exception e) {e.printStackTrace();}
        finally {
            webDriver.quit();
        }
    }
}
