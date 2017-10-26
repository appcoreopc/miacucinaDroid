package au.com.miacucina.com.foodtour.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import au.com.miacucina.com.foodtour.Model.ItemDisplay;
import au.com.miacucina.com.foodtour.R;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

    private List<ItemDisplay> itemList;

    public ItemAdapter(List<ItemDisplay> list) {
        itemList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_row, parent, true);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ItemDisplay item = itemList.get(position);

        holder.title.setText(item.getTitle());
        holder.titleInfo.setText(item.getTitleInfo());
        holder.description.setText(item.getDescription());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, titleInfo, description;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            description = (TextView) view.findViewById(R.id.titleInfo);
            titleInfo = (TextView) view.findViewById(R.id.description);
        }
    }

}
