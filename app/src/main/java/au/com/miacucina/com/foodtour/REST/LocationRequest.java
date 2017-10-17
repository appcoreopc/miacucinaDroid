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

import java.util.List;

import au.com.miacucina.com.foodtour.Model.TourLocationModel;

public class LocationRequest implements TourLocation {

    private Context _context;
    private RequestQueue queue;

    public LocationRequest(Context context) {
        _context = context;
    }

    @Override
    public List<TourLocationModel> getLocation(String locationCode) {

        queue = Volley.newRequestQueue(this._context);
        String url = "http://hmkcode.appspot.com/rest/controller/get.json";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Toast.makeText(_context, "Downloaded.", Toast.LENGTH_SHORT);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(_context, "ops there is a problem with your request.", Toast.LENGTH_SHORT);
            }
        });

        queue.add(stringRequest);
        return null;
    }

    @Nullable
    private boolean isConnected() {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }


}
