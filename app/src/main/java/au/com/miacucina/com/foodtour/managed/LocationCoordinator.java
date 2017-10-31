package au.com.miacucina.com.foodtour.managed;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;

import au.com.miacucina.com.foodtour.Model.ItemDisplay;
import au.com.miacucina.com.foodtour.adapters.ItemAdapter;

import static com.paypal.android.sdk.cu.i;
import static com.paypal.android.sdk.cu.t;

public class LocationCoordinator implements RequestCoordnation {

    private List<ItemDisplay> mList;
    private Context _context;
    private RequestQueue queue;
    private JSONObject jsonResponse;
    private ItemAdapter mItemAdapter;

    public LocationCoordinator(Context applicationContext, ItemAdapter adapter, List<ItemDisplay> itemList) {
        _context = applicationContext;
        mItemAdapter = adapter;
        mList = itemList;
    }

    @Override
    public void createRequest(String url) throws InterruptedException {
        getLocation(url);
    }

    public void getLocation(String url) throws InterruptedException {

        queue = Volley.newRequestQueue(this._context);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response.trim());

                            if (jsonArray != null) {

                                mList.clear();

                                //for (int i = 0; i < jsonArray.length(); i++) {
                                for (int i = 0; i < 20; i++) {

                                    JSONObject ob = jsonArray.getJSONObject(i);

                                    String title = ob.getString("title");
                                    String id = ob.getString("id");
                                    String url = ob.getString("url");
                                    String thumbnailUrl = ob.getString("thumbnailUrl");

                                    ItemDisplay itemDisplay = new ItemDisplay(title, id, url, thumbnailUrl);
                                    mList.add(itemDisplay);
                                }

                                if (mList.size() > 0)
                                {
                                    mItemAdapter.notifyDataSetChanged();
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);
    }
}
