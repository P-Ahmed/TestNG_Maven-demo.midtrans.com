package testRunner;

import org.testng.annotations.*;
import utils.Constant;
import utils.DriverSetup;

public class BaseE2ETest {

    //if you wish to run browser from testng.xml, comment/uncomment the following lines
    @BeforeTest(alwaysRun = true)
    @Parameters(value = "browser")
    public void browserSetupUsingParameters(String browserName) {
        DriverSetup.setThreadDriverName(browserName);
    }

    //if you wish to run browser from config.properties file, comment/uncomment the following lines
//    @BeforeClass(alwaysRun = true)
//    public void browserSetupUsingConfigProperties() {
//        DriverSetup.setThreadDriverName(Constant.BROWSER);
//    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        DriverSetup.quitDriver();
    }
}
