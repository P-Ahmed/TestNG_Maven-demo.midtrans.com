package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties", "classpath:config.properties"})
public interface Configuration extends Config {
    @Key("browser")
    String browser();

    @Key("headless")
    boolean headless();

    @Key("base.url")
    String baseUrl();

    @Key("base.test.data.path")
    String baseTestDataPath();

    @Key("base.report.path")
    String baseReportPath();

    @Key("base.screenshot.path")
    String baseScreenshotPath();

    @Key("product.amount")
    String productAmount();

    @Key("card.details.page.title")
    String cardDetailsPageTitle();

    @Key("card.number")
    String cardNumber();

    @Key("card.expiry.date")
    String cardExpiryDate();

    @Key("card.cvv")
    String cardCVV();

    @Key("otp")
    String otp();

    @Key("invalid.otp")
    String invalidOTP();

    @Key("merchant.name")
    String merchantName();

    @Key("final.amount")
    String finalAmount();

    @Key("payment.card.number")
    String paymentCardNumber();

    @Key("transaction.name")
    String transactionName();

    @Key("name")
    String name();

    @Key("email")
    String email();

    @Key("phone")
    String phone();

    @Key("city")
    String city();

    @Key("address")
    String address();

    @Key("postcode")
    String postcode();

    @Key("product.name")
    String productName();

    @Key("product.price")
    String productPrice();
}
