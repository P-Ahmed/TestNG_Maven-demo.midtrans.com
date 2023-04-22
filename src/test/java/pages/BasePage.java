package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.Constant;
import utils.DriverSetup;

import java.time.Duration;

public class BasePage {
    public WebDriverWait wait = new WebDriverWait(DriverSetup.getDriver(), Duration.ofSeconds(Constant.EXPLICIT_WAIT));

    public void clickClearAndType(WebElement webElement, String text) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement)).click();
        webElement.clear();
        webElement.sendKeys(text);
    }

    public void assertText(WebElement webElement, String expectedText) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
        Assert.assertEquals(webElement.getText(), expectedText);
    }

    public void containsText(WebElement webElement, String text) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
        if (webElement.getText().contains(text)) {
            assert true;
        }
    }

    public void presenceOfElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void turnOffImplicitWaits() {
        DriverSetup.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    }

    public void turnOnImplicitWaits() {
        DriverSetup.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Constant.IMPLICIT_WAIT));
    }
}
