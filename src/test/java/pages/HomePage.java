package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import setup.SetupDriver;

import java.time.Duration;
import java.util.List;

import static config.ConfigurationManager.configuration;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;
    @FindBy(css = "a[data-reactid='.0.0.0.2.0.0.5']")
    public WebElement buyNowButton;
    @FindBy(css = "td[class='amount']")
    public WebElement amountLabel;
    @FindBy(css = ".cart-content.buying")
    public WebElement checkoutPopup;
    @FindBy(css = ".cancel-btn")
    public WebElement checkOutPopupWindowCancel;
    @FindBy(css = "input[value='Budi']")
    public WebElement nameField;
    @FindBy(css = "input[value='budi@utomo.com']")
    public WebElement emailField;
    @FindBy(css = "input[value='081808466410']")
    public WebElement phoneNoField;
    @FindBy(css = "input[value='Jakarta']")
    public WebElement cityField;
    @FindBy(tagName = "textarea")
    public WebElement addressField;
    @FindBy(css = "input[value='10220']")
    public WebElement postCodeField;
    @FindBy(id = "snap-midtrans")
    public WebElement iFrame;
    @FindBy(className = "cart-checkout")
    public WebElement checkoutButton;
    @FindBy(className = "header-order-id")
    public WebElement orderId;
    @FindBy(xpath = "//span[contains(text(),'Midtrans Pillow')]")
    public WebElement orderProductName;
    @FindBy(css = ".order-summary-content.float-right")
    public WebElement orderProductPrice;
    @FindBy(css = ".page-container.scroll")
    public WebElement paymentSection;
    @FindBy(css = ".list-title.text-actionable-bold")
    public List<WebElement> paymentOptions;
    @FindBy(css = ".title-text.text-actionable-bold")
    public WebElement cardDetailsScreen;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebDriverWait explicitWait() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait;
    }

    public void goTo() {
        driver.get(configuration().baseUrl());
    }

    public void buyNow() {
        buyNowButton.click();
    }

    public void amountInShoppingCart() {
        Assert.assertEquals(amountLabel.getText(), configuration().productAmount());

    }

    public void checkoutPopupWindowOpen() {
        explicitWait().until(ExpectedConditions.visibilityOf(checkoutPopup));

    }

    public void shoppingCartFieldsAreVisible() {
        explicitWait().until(ExpectedConditions.visibilityOf(nameField));
        explicitWait().until(ExpectedConditions.visibilityOf(emailField));
        explicitWait().until(ExpectedConditions.visibilityOf(phoneNoField));
        explicitWait().until(ExpectedConditions.visibilityOf(cityField));
        explicitWait().until(ExpectedConditions.visibilityOf(addressField));
        explicitWait().until(ExpectedConditions.visibilityOf(postCodeField));

    }

    public void shoppingCartFieldsAreEditable() {
        clickClearAndType(nameField, configuration().name());
        clickClearAndType(emailField, configuration().email());
        clickClearAndType(phoneNoField, configuration().phone());
        clickClearAndType(cityField, configuration().city());
        clickClearAndType(addressField, configuration().address());
        clickClearAndType(postCodeField, configuration().postcode());

    }

    public void checkOut() throws InterruptedException {
        Thread.sleep(1000);
//        explicitWait().until(ExpectedConditions.visibilityOf(checkoutButton));
        checkoutButton.click();

    }

    public void orderSummaryPopup() {
        driver.switchTo().frame(iFrame);
        explicitWait().until(ExpectedConditions.visibilityOf(orderId));

    }

    public void orderSummary() {
        driver.switchTo().frame(iFrame);
        orderId.click();
        assertText(orderProductName, configuration().productName());
        assertText(orderProductPrice, configuration().productPrice());

    }

    public void paymentSection() {
        driver.switchTo().frame(iFrame);
        explicitWait().until(ExpectedConditions.visibilityOf(paymentSection));

    }

    public void allThePaymentOptions() {
        driver.switchTo().frame(iFrame);
        String[] expected = {"Credit/debit card", "Bank transfer", "GoPay/other e-Wallets", "ShopeePay/other e-Wallets", "KlikBCA", "BCA KlikPay", "OCTO Clicks", "Danamon Online Banking", "BRImo", "Indomaret", "Alfa Group", "Kredivo", "Akulaku PayLater", "UOB EZ Pay"};
        // assert that the number of found <option> elements matches the expectations
        Assert.assertEquals(expected.length, paymentOptions.size());
        // assert that the value of every <option> element equals the expected value
        for (int i = 0; i < paymentOptions.size(); i++) {
            if (expected[i].contains(paymentOptions.get(i).getText())) {
                assert true;
            }
        }
    }

    public void redirectToPaymentPage() {
        driver.switchTo().frame(iFrame);
        paymentOptions.get(0).click();
        explicitWait().until(ExpectedConditions.visibilityOf(cardDetailsScreen));
        assertText(cardDetailsScreen, configuration().cardDetailsPageTitle());

    }

    public void clickClearAndType(WebElement webElement, String text) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement)).click();
        webElement.clear();
        webElement.sendKeys(text);
    }

    public void assertText(WebElement webElement, String expectedText) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
        Assert.assertEquals(webElement.getText(), expectedText);
    }
}
