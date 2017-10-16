package au.com.miacucina.com.foodtour.payment;

import com.paypal.android.sdk.payments.PayPalConfiguration;

public class PaypalConfiguration {

    public static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_NO_NETWORK)
            .clientId("<YOUR_CLIENT_ID>");
}
