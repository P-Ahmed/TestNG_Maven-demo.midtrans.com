package testRunner;

import org.testng.annotations.Test;
import pages.HomePage;
import setup.SetupDriver;

public class HomeE2ETest extends SetupDriver {
    HomePage homePage;

    @Test(testName = "TC-1", priority = 1, groups = {"regression"})
    public void verifyItemIsAddedAndAmountAvailability() {
        homePage = new HomePage(driver);
        homePage.goTo();
        homePage.buyNow();
        homePage.amountInShoppingCart();
    }

    @Test(testName = "TC-2", priority = 2, groups = {"smoke", "regression"})
    public void verifyCheckoutPopupWindowOpens() {
        homePage = new HomePage(driver);
        homePage.goTo();
        homePage.buyNow();
        homePage.checkoutPopupWindowOpen();
    }

    @Test(testName = "TC-3", priority = 3, groups = {"regression"})
    public void verifyVariousFieldsAreAvailableInCheckoutPopupWindow() {
        homePage.goTo();
        homePage.buyNow();
        homePage.shoppingCartFieldsAreVisible();
    }

    @Test(testName = "TC-4", priority = 4, groups = {"regression"})
    public void verifyVariousFieldsAreEditableInCheckoutPopupWindow() {
        homePage.goTo();
        homePage.buyNow();
        homePage.shoppingCartFieldsAreEditable();
    }

    @Test(testName = "TC-5", priority = 5, groups = {"smoke", "regression"})
    public void verifyClickingOnCheckoutRedirectsToOrderSummary() throws InterruptedException {
        homePage.goTo();
        homePage.buyNow();
        homePage.checkOut();
        homePage.orderSummaryPopup();
    }

    @Test(testName = "TC-6", priority = 6, groups = {"regression"})
    public void verifyPriceAndProductNameOnOrderSummaryPopup() throws InterruptedException {
        homePage.goTo();
        homePage.buyNow();
        homePage.checkOut();
        homePage.orderSummary();
    }

    @Test(testName = "TC-7", priority = 7, groups = {"smoke", "regression"})
    public void verifyPaymentPageFromOrderSummary() throws InterruptedException {
        homePage.goTo();
        homePage.buyNow();
        homePage.checkOut();
        homePage.paymentSection();
    }

    @Test(testName = "TC-8", priority = 8, groups = {"regression"})
    public void verifyAllPaymentOptionsAreListedOnThePage() throws InterruptedException {
        homePage.goTo();
        homePage.buyNow();
        homePage.checkOut();
        homePage.allThePaymentOptions();
    }

    @Test(testName = "TC-9", priority = 9, groups = {"smoke", "regression"})
    public void verifyClickingOnDebitOrCreditCardPaymentRedirectsToCardDetailsScreen() throws InterruptedException {
        homePage.goTo();
        homePage.buyNow();
        homePage.checkOut();
        homePage.redirectToPaymentPage();
    }
}
