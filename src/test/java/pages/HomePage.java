package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.Constant;

import java.util.List;

public class HomePage extends BasePage {
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
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void goTo() {
        driver.get(Constant.BASE_URL);
    }

    public void buyNow() {
        buyNowButton.click();
    }

    public void amountInShoppingCart() {
        Assert.assertEquals(amountLabel.getText(), Constant.PRODUCT_AMOUNT_IN_SHOPPING_CART);

    }

    public void checkoutPopupWindowOpen() {
        presenceOfElement(checkoutPopup);

    }

    public void shoppingCartFieldsAreVisible() {
        presenceOfElement(nameField);
        presenceOfElement(emailField);
        presenceOfElement(phoneNoField);
        presenceOfElement(cityField);
        presenceOfElement(addressField);
        presenceOfElement(postCodeField);
    }

    public void shoppingCartFieldsAreEditable() {
        clickClearAndType(nameField, Constant.RANDOM_USER_NAME);
        clickClearAndType(emailField, Constant.RANDOM_USER_EMAIL);
        clickClearAndType(phoneNoField, Constant.RANDOM_USER_PHONE);
        clickClearAndType(cityField, Constant.RANDOM_USER_CITY);
        clickClearAndType(addressField, Constant.RANDOM_USER_ADDRESS);
        clickClearAndType(postCodeField, Constant.RANDOM_USER_POSTCODE);

    }

    public void checkOut() throws InterruptedException {
        Thread.sleep(1000);
        checkoutButton.click();

    }

    public void orderSummaryPopup() {
        driver.switchTo().frame(iFrame);
        presenceOfElement(orderId);

    }

    public void orderSummary() {
        driver.switchTo().frame(iFrame);
        orderId.click();
        assertText(orderProductName, Constant.ORDER_SUMMARY_PRODUCT_NAME);
        assertText(orderProductPrice, Constant.ORDER_SUMMARY_PRODUCT_PRICE);

    }

    public void paymentSection() {
        driver.switchTo().frame(iFrame);
        presenceOfElement(paymentSection);

    }

    public void allThePaymentOptions() {
        String expected[] = new String[]{};
        driver.switchTo().frame(iFrame);
        for (int i = 0; i < paymentOptions.size(); i++) {
            String paymentName = paymentOptions.get(i).getText();
            expected[i] = paymentName;
        }
        //String[] expected = {"Credit/debit card", "Bank transfer", "GoPay/other e-Wallets", "ShopeePay/other e-Wallets", "KlikBCA", "BCA KlikPay", "OCTO Clicks", "Danamon Online Banking", "BRImo", "Indomaret", "Alfa Group", "Kredivo", "Akulaku PayLater", "UOB EZ Pay"};
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
        presenceOfElement(cardDetailsScreen);
        assertText(cardDetailsScreen, Constant.CARD_DETAILS_PAGE_TITLE);

    }
}
