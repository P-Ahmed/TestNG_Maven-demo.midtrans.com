package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

import static config.ConfigurationManager.configuration;

public class PaymentPage {
    WebDriver driver;
    WebDriverWait wait;
    @FindBy(className = "header-amount")
    public WebElement orderAmountBeforeAddingCart;
    @FindBy(className = "valid-input-value")
    public List<WebElement> cardNumberInputField;
    @FindBy(id = "card-expiry")
    public WebElement expirationDateInputField;
    @FindBy(id = "card-cvv")
    public WebElement cvvInputField;
    @FindBy(className = "card-pay-button-part")
    public WebElement payNowButton;
    @FindBy(css = "iframe[title='3ds-iframe']")
    public WebElement iFrame;
    @FindBy(className = "help-block")
    public WebElement transactionName;
    @FindBy(id = "merchant_name")
    public WebElement merchantName;
    @FindBy(id = "txn_amount")
    public WebElement amount;
    @FindBy(id = "txn_time")
    public WebElement transactionTime;
    @FindBy(id = "card_number")
    public WebElement cardNumber;
    @FindBy(id = "otp")
    public WebElement otpField;
    @FindBy(name = "ok")
    public WebElement okButton;
    @FindBy(name = "cancel")
    public WebElement cancelButton;
    @FindBy(xpath = "//span[contains(text(),'Thank you for your purchase.')]")
    public WebElement paymentSuccessful;
    @FindBy(className = "cancel-modal-title")
    public WebElement paymentDeclined;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebDriverWait explicitWait() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait;
    }

    public void amountUpdateAfterApplyingCoupon() {
        String previousAmount = orderAmountBeforeAddingCart.getText();
        //System.out.println("Previous amount: "+previousAmount);
        clickClearAndType(cardNumberInputField.get(0), configuration().cardNumber());
        String updatedAmount = orderAmountBeforeAddingCart.getText();
        //System.out.println("Updated amount: "+updatedAmount);
        Assert.assertNotEquals(previousAmount, updatedAmount);

    }

    public void addingCardDetails() {
        clickClearAndType(cardNumberInputField.get(0), configuration().cardNumber());
        clickClearAndType(expirationDateInputField, configuration().cardExpiryDate());
        clickClearAndType(cvvInputField, configuration().cardCVV());
        payNowButton.click();

    }

    public void redirectingToBankPaymentScreen() throws InterruptedException {
        Thread.sleep(5000);
        driver.switchTo().frame(iFrame);

        containsText(transactionName, configuration().transactionName());
        assertText(merchantName, configuration().merchantName());
        assertText(amount, configuration().finalAmount());
        assertText(cardNumber, configuration().paymentCardNumber());

    }

    public void passingValidOTP() throws InterruptedException {
        clickClearAndType(otpField, configuration().otp());
        okButton.click();
        Thread.sleep(5000);
        explicitWait().until(ExpectedConditions.visibilityOf(paymentSuccessful));

    }

    public void passingInvalidOTP() {
        clickClearAndType(otpField, configuration().invalidOTP());
        okButton.click();
        driver.switchTo().parentFrame();
        assertText(paymentDeclined, "Card declined by bank");

    }

    public void cancellingPayment() {
        cancelButton.click();
        driver.switchTo().parentFrame();
        assertText(paymentDeclined, "Card declined by bank");

    }

    public void clickClearAndType(WebElement webElement, String text) {
        explicitWait().until(ExpectedConditions.elementToBeClickable(webElement)).click();
        webElement.clear();
        webElement.sendKeys(text);
    }

    public void containsText(WebElement webElement, String text) {
        explicitWait().until(ExpectedConditions.visibilityOf(webElement));
        if (webElement.getText().contains(text)) {
            assert true;
        }
    }

    public void assertText(WebElement webElement, String expectedText) {
        explicitWait().until(ExpectedConditions.visibilityOf(webElement));
        Assert.assertEquals(webElement.getText(), expectedText);
    }

}
