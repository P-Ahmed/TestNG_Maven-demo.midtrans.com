package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.Constant;
import utils.DriverSetup;

import java.util.List;

public class PaymentPage extends BasePage {
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

    public PaymentPage() {
        PageFactory.initElements(DriverSetup.getDriver(), this);
    }

    public void amountUpdateAfterApplyingCoupon() {
        String previousAmount = orderAmountBeforeAddingCart.getText();
        //System.out.println("Previous amount: "+previousAmount);
        clickClearAndType(cardNumberInputField.get(0), Constant.CARD_NUMBER);
        String updatedAmount = orderAmountBeforeAddingCart.getText();
        //System.out.println("Updated amount: "+updatedAmount);
        Assert.assertNotEquals(previousAmount, updatedAmount);
    }

    public void addingCardDetails() {
        clickClearAndType(cardNumberInputField.get(0), Constant.CARD_NUMBER);
        clickClearAndType(expirationDateInputField, Constant.CARD_EXPIRY_DATE);
        clickClearAndType(cvvInputField, Constant.CARD_CVV);
        payNowButton.click();
    }

    public void redirectingToBankPaymentScreen() throws InterruptedException {
        Thread.sleep(5000);
        DriverSetup.getDriver().switchTo().frame(iFrame);

        containsText(transactionName, Constant.PAYMENT_TRANSACTION_NAME);
        assertText(merchantName, Constant.PAYMENT_MERCHANT_NAME);
        assertText(amount, Constant.PAYMENT_FINAL_AMOUNT);
        assertText(cardNumber, Constant.PAYMENT_CARD_NUMBER);
    }

    public void passingValidOTP() throws InterruptedException {
        clickClearAndType(otpField, Constant.CARD_VALID_OTP);
        okButton.click();
        Thread.sleep(5000);
        presenceOfElement(paymentSuccessful);
    }

    public void passingInvalidOTP() {
        clickClearAndType(otpField, Constant.CARD_INVALID_OTP);
        okButton.click();
        DriverSetup.getDriver().switchTo().parentFrame();
        assertText(paymentDeclined, "Card declined by bank");
    }

    public void cancellingPayment() {
        cancelButton.click();
        DriverSetup.getDriver().switchTo().parentFrame();
        assertText(paymentDeclined, "Card declined by bank");
    }
}
