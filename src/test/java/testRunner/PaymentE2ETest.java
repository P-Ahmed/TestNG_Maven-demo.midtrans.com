package testRunner;

import org.testng.annotations.Test;
import pages.HomePage;
import pages.PaymentPage;

public class PaymentE2ETest extends BaseE2ETest {
    PaymentPage paymentPage = new PaymentPage();
    HomePage homePage = new HomePage();

    @Test(testName = "TC-10", priority = 1, groups = {"regression"})
    public void verifyOrderAmountAfterApplyingCoupon() throws InterruptedException {
        homePage.goTo();
        homePage.buyNow();
        homePage.checkOut();
        homePage.redirectToPaymentPage();
        paymentPage.amountUpdateAfterApplyingCoupon();
    }

    @Test(testName = "TC-11", priority = 2, groups = {"smoke", "regression"})
    public void enterValidCardDetailsAndClickingOnPayButton() throws InterruptedException {
        homePage.goTo();
        homePage.buyNow();
        homePage.checkOut();
        homePage.redirectToPaymentPage();
        paymentPage.addingCardDetails();
    }

    @Test(testName = "TC-12", priority = 3, groups = {"regression"})
    public void clickingOnPayButtonAndVerifyVariousDetails() throws InterruptedException {
        homePage.goTo();
        homePage.buyNow();
        homePage.checkOut();
        homePage.redirectToPaymentPage();
        paymentPage.addingCardDetails();
        paymentPage.redirectingToBankPaymentScreen();
    }

    @Test(testName = "TC-13", priority = 4, groups = {"smoke", "regression"})
    public void verifyClickingOkWithValidOTPRedirectsToOrderSuccessful() throws InterruptedException {
        homePage.goTo();
        homePage.buyNow();
        homePage.checkOut();
        homePage.redirectToPaymentPage();
        paymentPage.addingCardDetails();
        paymentPage.redirectingToBankPaymentScreen();
        paymentPage.passingValidOTP();
    }

    @Test(testName = "TC-14", priority = 5, groups = {"regression"})
    public void verifyClickingOkWithInvalidOTPRedirectsToOrderDeclined() throws InterruptedException {
        homePage.goTo();
        homePage.buyNow();
        homePage.checkOut();
        homePage.redirectToPaymentPage();
        paymentPage.addingCardDetails();
        paymentPage.redirectingToBankPaymentScreen();
        paymentPage.passingInvalidOTP();
    }
    @Test(testName = "TC-15", priority = 6, groups = {"regression"})
    public void verifyClickingCancelRedirectsToOrderDeclined() throws InterruptedException {
        homePage.goTo();
        homePage.buyNow();
        homePage.checkOut();
        homePage.redirectToPaymentPage();
        paymentPage.addingCardDetails();
        paymentPage.redirectingToBankPaymentScreen();
        paymentPage.cancellingPayment();
    }
}
