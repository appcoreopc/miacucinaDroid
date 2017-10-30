package au.com.miacucina.com.foodtour.managed;
import android.content.Context;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;
import java.util.ArrayList;

import au.com.miacucina.com.foodtour.Model.ItemDisplay;
import au.com.miacucina.com.foodtour.REST.TourLocation;

public class LocationCoordinator implements RequestCoordnation {

    private TourLocation mRest;
    private ArrayList<ItemDisplay> mList;
    private Context _context;
    private RequestQueue queue;
    private JSONObject jsonResponse;

    public LocationCoordinator(Context context, ArrayList<ItemDisplay> list) {
        _context = context;
        mList = list;
    }

    @Override
    public void createRequest(String url) {

    }

    public void getLocation(String url) throws InterruptedException {

        queue = Volley.newRequestQueue(this._context);

        // Request a string response from the provided URL.
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        jsonResponse = response;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(_context, "ops there is a problem with your request.", Toast.LENGTH_SHORT);
            }
        });

        queue.add(stringRequest);
    }
}
