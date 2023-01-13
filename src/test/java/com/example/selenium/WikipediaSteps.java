package com.example.selenium;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.assertj.core.api.Assertions;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.HashMap;
import java.util.Map;
public class WikipediaSteps {
    /*
    Öffne Wikipedia
    Suche nach Deutschland
    Klicke auf Suche
    Dann soll der Titel Deutschland sein
Dann wechsle Sprache
Dann soll der Titel Germany sein
     */
    WebDriver driver;

    @Given("^Open the Chrome and launch the application$")
    public void open_the_chrome_and_launch_the_application() throws Throwable {
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        ChromeOptions chromeOptions = new ChromeOptions();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        chromeOptions.setExperimentalOption("prefs", prefs);
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("start-maximized");
        System.setProperty("webdriver.chrome.driver","F:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.wikipedia.org/");
    }

    @Given("^Verify Welcome Page$")
    public void verify_welcome_page() throws Throwable {
    }

    @Given("^I Search$")
    public void i_search() throws Throwable {
        WebElement search = driver.findElement(By.id("searchInput"));
        search.sendKeys("Deutschland");
        WebElement button = driver.findElement(By.xpath("/html/body/div[3]/form/fieldset/button"));
        button.click();
        WebElement firstHeading = driver.findElement(By.id("firstHeading"));
        Assertions.assertThat(firstHeading.getText()).isEqualTo("Deutschland");
    }

    @Given("^Change Language$")
    public void change_language() throws Throwable {
        WebElement search = driver.findElement(By.linkText("English"));
        search.click();
    }
}