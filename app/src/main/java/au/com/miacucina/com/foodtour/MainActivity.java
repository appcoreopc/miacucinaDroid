package au.com.miacucina.com.foodtour;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import au.com.miacucina.com.foodtour.Model.ItemDisplay;
import au.com.miacucina.com.foodtour.adapters.ItemAdapter;
import au.com.miacucina.com.foodtour.adapters.ItemClickSupport;
import au.com.miacucina.com.foodtour.adapters.ViewType;
import au.com.miacucina.com.foodtour.appDataResource.AppMenu;
import au.com.miacucina.com.foodtour.managed.AlbumViewLayoutHandler;
import au.com.miacucina.com.foodtour.util.LocationService;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private ItemAdapter itemAdapter;
    private RecyclerView recyclerView;
    private List<ItemDisplay> itemList;
    private AlbumViewLayoutHandler coordinator;
    private RecyclerView.LayoutManager mLayoutManager;

    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, false);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);

        if (location != null) {
            System.out.println("Provider " + provider + " has been selected.");
            onLocationChanged(location);
        } else {
            //latituteField.setText("Location not available");
            //longitudeField.setText("Location not available");
        }

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        itemList = new ArrayList<>();
        itemList = (List<ItemDisplay>) AppMenu.populateData();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        itemAdapter = new ItemAdapter(itemList);
        itemAdapter.notifyDataSetChanged();

        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        itemAdapter.setViewType(ViewType.ALBUM);
        recyclerView.setAdapter(itemAdapter);

        // https://guides.codepath.com/android/using-the-recyclerview
        // https://jsonplaceholder.typicode.com/photos
        // SnapHelper snapHelperStart = new GravitySnapHelper(Gravity.START);
        // snapHelperStart.attachToRecyclerView(recyclerView);

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                //
            }
        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_explore:
                        try {
                            renderAlbumLayout("https://jsonplaceholder.typicode.com/photos");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    case R.id.action_favourites:
                        try {
                            renderAlbumLayout("https://jsonplaceholder.typicode.com/photos");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    case R.id.action_profile:
                        loadProfileLayout();

                }
                return true;
            }
        });
    }

    private void loadProfileLayout() {

        itemList = (List<ItemDisplay>) AppMenu.getMenu();
        itemAdapter.setItemList(itemList);
        itemAdapter.setViewType(ViewType.PROFILE);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(itemAdapter);
    }

    private void renderAlbumLayout(String urlRequest) throws InterruptedException {

        itemAdapter.setViewType(ViewType.ALBUM);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        // we need to pass all the resources ref / data list //
        coordinator = new AlbumViewLayoutHandler(this.getApplicationContext(), itemAdapter, itemList);
        recyclerView.setAdapter(itemAdapter);

        try {
            coordinator.renderLayout("https://jsonplaceholder.typicode.com/photos");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void startPaymentActivity() {
        //itemAdapter.setViewType(ViewType.ALBUM);
        //Intent myIntent = new Intent(MainActivity.this, PaymentlActivity.class);
        //startActivity(myIntent);
    }

    @Override
    public void onLocationChanged(Location location) {
        double lat = location.getLatitude();
        double lng = location.getLongitude();

        Geocoder geoCoder = new Geocoder(this, Locale.getDefault());
        StringBuilder builder = new StringBuilder();
        try {
            List<Address> address = geoCoder.getFromLocation(lat, lng, 1);
            int maxLines = address.get(0).getMaxAddressLineIndex();
            for (int i = 0; i < maxLines; i++) {
                String addressStr = address.get(0).getAddressLine(i);
                builder.append(addressStr);
                builder.append(" ");
            }

            String fnialAddress = builder.toString(); //This is the complete address.

           /* latituteField.setText(String.valueOf(lat));
            longitudeField.setText(String.valueOf(lng));
            addressField.setText(fnialAddress); //This will display the final address.
            */

        } catch (IOException e) {
            // Handle IOException
        } catch (NullPointerException e) {
            // Handle NullPointerException
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
