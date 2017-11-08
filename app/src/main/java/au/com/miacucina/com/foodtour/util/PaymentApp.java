package au.com.miacucina.com.foodtour.util;

import android.content.Context;

import au.com.miacucina.com.foodtour.payment.Settings;
import retrofit.RestAdapter;

/**
 * Created by Jeremy on 11/8/2017.
 */

public class PaymentApp {

    private static ApiClient sApiClient;

    public static ApiClient getApiClient(Context context) {

        if (sApiClient == null) {
            sApiClient = new RestAdapter.Builder()
                    .setEndpoint(Settings.getEnvironmentUrl(context))
                    .setRequestInterceptor(new ApiClientRequestInterceptor())
                    .build()
                    .create(ApiClient.class);
        }

        return sApiClient;
    }


}
