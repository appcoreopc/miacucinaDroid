package au.com.miacucina.com.foodtour.managed;


import android.support.v7.widget.RecyclerView;
import au.com.miacucina.com.foodtour.adapters.ItemAdapter;

public class ProfileViewHandler implements LayoutViewHandler {

    RecyclerView mRecyclerView;
    ItemAdapter mAdapter;

     public ProfileViewHandler(RecyclerView recyclerView, ItemAdapter adapter)
     {
         mRecyclerView = recyclerView;
         mAdapter = adapter;
     }

     public void setLayout(){

         mRecyclerView.setAdapter(mAdapter);
     }
}
