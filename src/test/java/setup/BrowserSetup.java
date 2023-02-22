package setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BrowserSetup {
    WebDriver driver;

    @BeforeTest
    public void browserInitiate() {
        driver = new EdgeDriver();
    }

    @Test
    public void gotoGoogle() {
        driver.get("https://www.google.com/");
    }


}
