//AI generated code saved in demo/generated-tests/OrangeHRMLoginTest.java

package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class OrangeHRMLoginTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private final String baseUrl = "https://opensource-demo.orangehrmlive.com/";
    private final String expectedLoginPageTitle = "OrangeHRM";
    private final String expectedDashboardTitle = "OrangeHRM";

    // Valid credentials for demo site
    private final String validUsername = "Admin";
    private final String validPassword = "admin123";

    @BeforeClass
    public void setUp() {
        // Setup ChromeDriver (assumes chromedriver is in system PATH)
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);

        // Explicit wait for up to 10 seconds
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(description = "Verify registered user can log in and access dashboard")
    public void testSuccessfulLogin() {
        // Navigate to OrangeHRM demo site
        driver.get(baseUrl);

        // Verify the login page title
        String actualLoginPageTitle = driver.getTitle();
        Assert.assertEquals(actualLoginPageTitle, expectedLoginPageTitle,
                "Login page title should be '" + expectedLoginPageTitle + "'");

        // Locate username and password fields and enter valid credentials
        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        WebElement passwordInput = driver.findElement(By.name("password"));

        usernameInput.clear();
        usernameInput.sendKeys(validUsername);

        passwordInput.clear();
        passwordInput.sendKeys(validPassword);

        // Click the login button
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();

        // Wait for dashboard page to load by checking visibility of dashboard header
        // Dashboard page URL remains https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index
        wait.until(ExpectedConditions.urlContains("/dashboard"));

        // Option 1: Verify page title after login (should still be "OrangeHRM")
        String actualDashboardTitle = driver.getTitle();
        Assert.assertEquals(actualDashboardTitle, expectedDashboardTitle,
                "Dashboard page title should be '" + expectedDashboardTitle + "' after successful login");

        // Option 2: Verify dashboard header is displayed
        /*
         * The dashboard header element on this OrangeHRM demo site has a tag:
         * <h6 class="oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module">
         *     Dashboard
         * </h6>
         */
        WebElement dashboardHeader = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h6[text()='Dashboard']"))
        );
        Assert.assertTrue(dashboardHeader.isDisplayed(), "Dashboard header should be visible after login");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
