package setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

import static config.ConfigurationManager.configuration;

public class SetupDriver {
    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeClass(alwaysRun = true)
    public void browserSetup() {
        if (configuration().browser().equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (configuration().browser().equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (configuration().browser().equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (configuration().browser().equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        } else {
            System.out.println("Browser name is incorrect");
            System.exit(1);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterClass(alwaysRun = true)
    public void afterTest() {
        driver.quit();
    }
}
