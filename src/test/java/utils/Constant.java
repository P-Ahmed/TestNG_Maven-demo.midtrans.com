package utils;

public class Constant {
    public static final int EXPLICIT_WAIT = 60;
    public static final int IMPLICIT_WAIT = 30;
    public static final String BROWSER = Utility.readProperties("browser");
    public static final String BASE_URL = Utility.readProperties("base.url");
    public static final String PRODUCT_AMOUNT_IN_SHOPPING_CART = Utility.readProperties("product.amount.in.shopping.cart");
    public static final String RANDOM_USER_NAME = Utility.readProperties("random.user.name");
    public static final String RANDOM_USER_EMAIL = Utility.readProperties("random.user.email");
    public static final String RANDOM_USER_PHONE = Utility.readProperties("random.user.phone");
    public static final String RANDOM_USER_CITY = Utility.readProperties("random.user.city");
    public static final String RANDOM_USER_ADDRESS = Utility.readProperties("random.user.address");
    public static final String RANDOM_USER_POSTCODE = Utility.readProperties("random.user.postcode");
    public static final String ORDER_SUMMARY_PRODUCT_NAME = Utility.readProperties("order.summary.product.name");
    public static final String ORDER_SUMMARY_PRODUCT_PRICE = Utility.readProperties("order.summary.product.price");
    public static final String CARD_DETAILS_PAGE_TITLE = Utility.readProperties("card.details.page.title");
    public static final String CARD_NUMBER = Utility.readProperties("card.number");
    public static final String CARD_EXPIRY_DATE = Utility.readProperties("card.expiry.date");
    public static final String CARD_CVV = Utility.readProperties("card.cvv");
    public static final String CARD_VALID_OTP = Utility.readProperties("card.valid.otp");
    public static final String CARD_INVALID_OTP = Utility.readProperties("card.invalid.otp");
    public static final String PAYMENT_MERCHANT_NAME = Utility.readProperties("payment.merchant.name");
    public static final String PAYMENT_FINAL_AMOUNT = Utility.readProperties("payment.final.amount");
    public static final String PAYMENT_CARD_NUMBER = Utility.readProperties("payment.card.number");
    public static final String PAYMENT_TRANSACTION_NAME = Utility.readProperties("payment.transaction.name");
}
