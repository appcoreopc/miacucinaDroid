package au.com.miacucina.com.foodtour;

import au.com.miacucina.com.foodtour.payment.Settings;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import au.com.miacucina.com.foodtour.model.Transaction;

import com.braintreepayments.api.models.CardNonce;
import com.braintreepayments.api.models.PaymentMethodNonce;

import au.com.miacucina.com.foodtour.util.PaymentApp;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.braintreepayments.api.models.CardNonce;
import com.braintreepayments.api.models.PaymentMethodNonce;


import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CreateTransactionActivity extends AppCompatActivity {

    public static final String EXTRA_PAYMENT_METHOD_NONCE = "nonce";
    private ProgressBar mLoadingSpinner;
    private Context mcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.create_transaction_activity);
        //mLoadingSpinner = findViewById(R.id.loading_spinner);
        //setTitle(R.string.processing_transaction);
        mcontext = getApplicationContext();

        sendNonceToServer((PaymentMethodNonce) getIntent().getParcelableExtra(EXTRA_PAYMENT_METHOD_NONCE));
    }

    private void sendNonceToServer(PaymentMethodNonce nonce) {

        Callback<Transaction> callback = new Callback<Transaction>() {
            @Override
            public void success(Transaction transaction, Response response) {
                if (transaction.getMessage() != null &&
                        transaction.getMessage().startsWith("created")) {
                    //setStatus(R.string.transaction_complete);
                    String msg = transaction.getMessage();
                    Toast.makeText(mcontext, msg, Toast.LENGTH_SHORT).show();
                    setMessage(msg);
                } else {
                    //setStatus(R.string.transaction_failed);
                    if (TextUtils.isEmpty(transaction.getMessage())) {
                        String msg = "Server response was empty or malformed";
                        setMessage(msg);
                        Toast.makeText(mcontext, msg, Toast.LENGTH_SHORT).show();
                    } else {
                        String msg = transaction.getMessage();
                        Toast.makeText(mcontext, msg, Toast.LENGTH_SHORT).show();
                        Log.i("APP", msg);
                        setMessage(msg);
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                //setStatus(R.string.transaction_failed);
                setMessage("Unable to create a transaction. Response Code: " +
                        error.getResponse().getStatus() + " Response body: " +
                        error.getResponse().getBody());
            }
        };

        if (Settings.isThreeDSecureEnabled(this) && Settings.isThreeDSecureRequired(this)) {
            PaymentApp.getApiClient(this).createTransaction(nonce.getNonce(),
                    Settings.getThreeDSecureMerchantAccountId(this), true, callback);
        } else if (Settings.isThreeDSecureEnabled(this)) {
            PaymentApp.getApiClient(this).createTransaction(nonce.getNonce(),
                    Settings.getThreeDSecureMerchantAccountId(this), callback);
        } else if (nonce instanceof CardNonce && ((CardNonce) nonce).getCardType().equals("UnionPay")) {
            PaymentApp.getApiClient(this).createTransaction(nonce.getNonce(),
                    Settings.getUnionPayMerchantAccountId(this), callback);
        } else {
            PaymentApp.getApiClient(this).createTransaction(nonce.getNonce(), Settings.getMerchantAccountId(this),
                    callback);
        }
    }

    private void setStatus(int message) {

        mLoadingSpinner.setVisibility(View.GONE);
        setTitle(message);
        //TextView status = findViewById(R.id.transaction_status);
        //status.setText(message);
        //status.setVisibility(View.VISIBLE);
    }


    private void setMessage(String message) {

        //mLoadingSpinner.setVisibility(View.GONE);
        //TextView textView = findViewById(R.id.transaction_message);
        //textView.setText(message);
        //textView.setVisibility(View.VISIBLE);
    }
}
