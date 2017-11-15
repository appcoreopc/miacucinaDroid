package au.com.miacucina.com.foodtour.util;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

import au.com.miacucina.com.foodtour.payment.Settings;
import retrofit.RestAdapter;

/**
 * Created by Jeremy on 11/8/2017.
 */

public class PaymentApp  extends Application implements Thread.UncaughtExceptionHandler {

    private static ApiClient sApiClient;

    private Thread.UncaughtExceptionHandler mDefaultExceptionHandler;

    @Override
    public void onCreate() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectCustomSlowCalls()
                    .detectNetwork()
                    .penaltyLog()
                    .penaltyDeath()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectActivityLeaks()
                    .detectLeakedClosableObjects()
                    .detectLeakedRegistrationObjects()
                    .detectLeakedSqlLiteObjects()
                    .penaltyLog()
                    .penaltyDeath()
                    .build());
        }

        super.onCreate();

        if (Settings.getVersion(this) != BuildConfig.VERSION_CODE) {
            Settings.setVersion(this);
            //MailableLog.clearLog(this);
        }
        //MailableLog.init(this, BuildConfig.DEBUG);

        //DeveloperTools.setup(this);

        mDefaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        //Logger logger = LoggerFactory.getLogger("Exception");

        Log.i("APP", " thread.toString(): " + thread.toString());
        Log.i("APP", "Exception: " + ex.toString());
        Log.i("APP", "Exception stacktrace:");

        for (StackTraceElement trace : ex.getStackTrace()) {
            Log.i("APP", trace.toString());
        }


        Log.i("APP", "cause.toString(): " + ex.getCause().toString());
        Log.i("APP", "Cause: " + ex.getCause().toString());
        Log.i("APP", "Cause stacktrace:");
        for (StackTraceElement trace : ex.getCause().getStackTrace()) {
            Log.i("APP", trace.toString());
        }

        mDefaultExceptionHandler.uncaughtException(thread, ex);
    }

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

    static void resetApiClient() {
        sApiClient = null;
    }
}
