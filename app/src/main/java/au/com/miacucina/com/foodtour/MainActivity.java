package au.com.miacucina.com.foodtour;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
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
import java.util.ArrayList;
import java.util.List;

import au.com.miacucina.com.foodtour.Model.ItemDisplay;
import au.com.miacucina.com.foodtour.adapters.ItemAdapter;
import au.com.miacucina.com.foodtour.adapters.ItemClickSupport;
import au.com.miacucina.com.foodtour.adapters.ViewType;
import au.com.miacucina.com.foodtour.appDataResource.AppMenu;
import au.com.miacucina.com.foodtour.managed.AlbumViewLayoutHandler;

public class MainActivity extends AppCompatActivity {

    private ItemAdapter itemAdapter;
    private RecyclerView recyclerView;
    private List<ItemDisplay> itemList;
    private AlbumViewLayoutHandler coordinator;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        //SnapHelper snapHelperStart = new GravitySnapHelper(Gravity.START);
        //snapHelperStart.attachToRecyclerView(recyclerView);

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
}
