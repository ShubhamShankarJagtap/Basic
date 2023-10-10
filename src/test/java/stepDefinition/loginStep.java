package stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class loginStep {
    WebDriver driver;
    WebElement userName;
    WebElement passWord;
    WebElement loginButton;
    WebDriverWait wait;

    @Given("I launch the browser.")
    public void launchBrowser(){

        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().fullscreen();
    }

    @When("I navigate to the url {string}")
    public void iNavigateToTheUrl(String url) {

        driver.navigate().to(url);
        Assert.assertEquals("Swag Labs",driver.getTitle());
    }

    @And("I enter the  valid {string} and {string}")
    public void iEnterTheValidAnd(String username, String password) {

        wait=new WebDriverWait(driver, Duration.ofSeconds(100));

        userName=wait.until(ExpectedConditions.presenceOfElementLocated(By.id("user-name")));
        userName.sendKeys(username);

        passWord = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
        passWord.sendKeys(password);

    }

    @And("I enter the click button")
    public void iEnterTheClickButton() {

        loginButton=wait.until(ExpectedConditions.presenceOfElementLocated(By.id("passWord")));
        loginButton.click();
    }

    @Then("I navigated to homePage of website")
    public void iNavigatedToHomePageOfWebsite() {
        Assert.assertEquals("Swag Labs",driver.getTitle());
    }

    @And("I close the browser")
    public void iCloseTheBrowser() {
        driver.close();
    }
}
