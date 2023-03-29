package testRunner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import utils.Constant;

import java.time.Duration;

public class BaseE2ETest {
    public WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void browserSetup() {
//        if (Constant.BROWSER.equalsIgnoreCase("chrome")) {
//            driver = new ChromeDriver();
//        } else if (Constant.BROWSER.equalsIgnoreCase("edge")) {
//            driver = new EdgeDriver();
//        } else if (Constant.BROWSER.equalsIgnoreCase("firefox")) {
//            driver = new FirefoxDriver();
//        } else if (Constant.BROWSER.equalsIgnoreCase("safari")) {
//            driver = new SafariDriver();
//        } else {
//            System.out.println("Browser name is incorrect");
//            System.exit(1);
//        }

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headed");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--disable-software-rasterizer");
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--no-sandbox");

        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constant.IMPLICIT_WAIT));
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
