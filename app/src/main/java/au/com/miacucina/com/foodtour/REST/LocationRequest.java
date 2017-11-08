package au.com.miacucina.com.foodtour.REST;
import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.Nullable;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class LocationRequest implements TourLocation {

    private Context _context;
    private RequestQueue queue;
    String jsonResponse = "";

    public LocationRequest(Context context) {
        _context = context;
    }

    @Override
    public String getLocation(String url) throws InterruptedException {

        queue = Volley.newRequestQueue(this._context);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        jsonResponse = response;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(_context, "ops there is a problem with your request.", Toast.LENGTH_SHORT);
            }
        });

        queue.add(stringRequest);
        return jsonResponse;
    }

    @Nullable
    private boolean isConnected() {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }


}
